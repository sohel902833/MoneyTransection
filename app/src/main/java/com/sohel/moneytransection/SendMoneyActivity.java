package com.sohel.moneytransection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.sohel.moneytransection.ApiCall.FirebaseResponse;
import com.sohel.moneytransection.ApiCall.TransectionServices;
import com.sohel.moneytransection.ApiKey.ApiKey;
import com.sohel.moneytransection.Local.Database.SettingDb;
import com.sohel.moneytransection.Local.Database.UserDb;
import com.sohel.moneytransection.Model.TransectionModel;
import com.sohel.moneytransection.Model.UserModel;
import com.sohel.moneytransection.Services.Common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class SendMoneyActivity extends AppCompatActivity {

    private EditText userIdEt,amountEt;
    private TextView userNameTv;
    private Button searchButton,sendButton;
    private TransectionServices transectionServices;
    ProgressDialog progressDialog;
    List<UserModel> userModelList=new ArrayList<>();
    UserModel getUser,currentUser;
    private UserDb userDb;
    private SettingDb settingDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);
        init();


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId=userIdEt.getText().toString();
                if(userId.isEmpty()){
                    Common.showEditTextError(userIdEt,"Enter User Id Or Phone.");
                }else{
                    if(userId.equals(currentUser.getUserId()) || userId.equals(currentUser.getPhone())){
                        Toast.makeText(SendMoneyActivity.this, "Its Your Number", Toast.LENGTH_SHORT).show();
                    }else{
                        searchUser(userId);
                    }
                 }
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount=amountEt.getText().toString();
                if(amount.isEmpty()){
                    Common.showEditTextError(amountEt,"Enter Amount..");
                }else{
                    if(Integer.parseInt(amount)>currentUser.getCashBalance()){
                        Toast.makeText(SendMoneyActivity.this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
                    }else {
                        sendBalance(amount);
                    }
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();


        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiKey.getUserRef()
                .child(userDb.getUserPhone())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            progressDialog.dismiss();
                            currentUser=snapshot.getValue(UserModel.class);
                        }else{
                            progressDialog.dismiss();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressDialog.dismiss();
                    }
                });




    }
    private void searchUser(String userId) {
        ApiKey.getUserRef()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            userModelList.clear();
                            for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                                UserModel user = snapshot1.getValue(UserModel.class);
                                userModelList.add(user);
                            }
                             getUser=getUser(userId);
                            if(getUser!=null){
                                progressDialog.dismiss();
                                userNameTv.setVisibility(View.VISIBLE);
                                sendButton.setVisibility(View.VISIBLE);
                                amountEt.setVisibility(View.VISIBLE);
                                userNameTv.setText(getUser.getName());
                            }
                            
                        } else {
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressDialog.dismiss();
                    }
                });

    }
    private UserModel getUser(String userId) {
        UserModel findedUser=null;
        if(userModelList!=null) {
            for (int i = 0; i < userModelList.size(); i++) {
                UserModel currentUser=userModelList.get(i);
                if(currentUser.getUserId().equals(userId) || currentUser.getPhone().equals(userId)){
                    findedUser=currentUser;
                    break;
                }
            }
        }
        return  findedUser;
    }
    private void init() {
        Toolbar toolbar=findViewById(R.id.appBarId);
        setSupportActionBar(toolbar);
        this.setTitle("Send Money");

        settingDb=new SettingDb(this);
        userDb=new UserDb(this);
        progressDialog=new ProgressDialog(this);
        transectionServices=new TransectionServices(this);
        userIdEt=findViewById(R.id.userIdEt);
        amountEt=findViewById(R.id.amountEt);
        userNameTv=findViewById(R.id.userNameTv);
        searchButton=findViewById(R.id.search_Bt);
        sendButton=findViewById(R.id.send_Btn);
    }
    private void sendBalance(String amount){
        HashMap<String,Object> sendMap=new HashMap<>();
        sendMap.put("cashBalance",getUser.getCashBalance()+Integer.parseInt(amount));

         HashMap<String,Object> userMap=new HashMap<>();
         userMap.put("cashBalance",currentUser.getCashBalance()-(Integer.parseInt(amount)+Integer.parseInt(amount)*settingDb.getAppSetting().getSendMoney()/100));

        progressDialog.setMessage("Sending..");
        progressDialog.setCancelable(false);
        transectionServices.updateAmount(getUser.getPhone(), progressDialog, sendMap, new FirebaseResponse() {
            @Override
            public void onSuccess(String message, ProgressDialog progressDialog) {
                transectionServices.updateAmount(currentUser.getPhone(), progressDialog, userMap, new FirebaseResponse() {
                    @Override
                    public void onSuccess(String message, ProgressDialog progressDialog) {
                        Calendar calForDate=Calendar.getInstance();
                        SimpleDateFormat currentDate=new SimpleDateFormat("dd-MMMM-yyy");
                        String saveCurrentDate=currentDate.format(calForDate.getTime());

                        Calendar callForTime=Calendar.getInstance();
                        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm");
                        String saveCurrentTime=currentTime.format(callForTime.getTime());
                        String time=saveCurrentDate+" At "+saveCurrentTime;

                        TransectionModel history=new TransectionModel(String.valueOf(System.currentTimeMillis()),currentUser.getPhone(),currentUser.getName(),Integer.parseInt(amount),time);

                            transectionServices.addHistory(history, progressDialog, new FirebaseResponse() {
                                @Override
                                public void onSuccess(String message, ProgressDialog progressDialog) {
                                    onStart();
                                    progressDialog.dismiss();
                                    userNameTv.setVisibility(View.GONE);
                                    sendButton.setVisibility(View.GONE);
                                    amountEt.setVisibility(View.GONE);
                                    userIdEt.setText("");
                                    Toast.makeText(SendMoneyActivity.this, "Send Balance Successful.", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(String message, ProgressDialog progressDialog) {
                                    Toast.makeText(SendMoneyActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            });
                         }
                    @Override
                    public void onError(String message, ProgressDialog progressDialog) {
                        Toast.makeText(SendMoneyActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
            }

            @Override
            public void onError(String message, ProgressDialog progressDialog) {
                Toast.makeText(SendMoneyActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

}