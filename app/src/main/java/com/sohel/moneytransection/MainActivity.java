package com.sohel.moneytransection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.accounts.AccountsException;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.sohel.moneytransection.ApiKey.ApiKey;
import com.sohel.moneytransection.Local.Database.SettingDb;
import com.sohel.moneytransection.Local.Database.UserDb;
import com.sohel.moneytransection.Model.AppSettingModel;
import com.sohel.moneytransection.Model.UserModel;
import com.sohel.moneytransection.Services.AccountServices;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView userIdTv,refferTv,phoneTv,accountStatusTv,activeTv;

    private CircleImageView profileImage;
    private TextView nameTv,balanceTv;
    private LinearLayout cashOutLinearLayout,sendMoneyLinearLayout;

    private UserDb userDb;
    private AccountServices accountServices;
    private UserModel user;
    private ProgressDialog progressDialog;

    private int balanceHandeler=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         init();


         activeTv.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 accountServices.activeAccount(user);
                 onStart();
             }
         });

         balanceTv.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 balanceHandeler++;
                 if(balanceHandeler%2==0){
                     balanceTv.setText("Tap to See Balance");
                 }else{
                    balanceTv.setText(""+user.getCashBalance()+" tk");
                 }
             }
         });
         sendMoneyLinearLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(MainActivity.this,SendMoneyActivity.class));
             }
         });


    }


    @Override
    protected void onStart() {
        super.onStart();


        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        loadAppSetting();
        ApiKey.getUserRef()
                .child(userDb.getUserPhone())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            progressDialog.dismiss();
                             user=snapshot.getValue(UserModel.class);
                            userIdTv.setText("User Id : "+user.getUserId());
                            phoneTv.setText("Phone : "+user.getPhone());
                            refferTv.setText("Refer Id : "+user.getMyRefer());
                            refferTv.setText("Refer Id : "+user.getMyRefer());
                            nameTv.setText(""+user.getName());

                            if(user.getAccontStatus().equals("inactive")){
                                activeTv.setVisibility(View.VISIBLE);
                                accountStatusTv.setText("Account Status : Inactive");
                            }else if(user.getAccontStatus().equals("active")){
                                activeTv.setVisibility(View.GONE);
                                accountStatusTv.setText("Account Status : Active");
                            }
                        }else{
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressDialog.dismiss();
                    }
                });




    }
    private void init() {
        progressDialog=new ProgressDialog(this);
        userDb=new UserDb(this);
        userIdTv=findViewById(R.id.userIdTv);
        accountServices=new AccountServices(this);

        profileImage=findViewById(R.id.profileImageView);
        nameTv=findViewById(R.id.userNameTv);
        balanceTv=findViewById(R.id.balanceTv);
        cashOutLinearLayout=findViewById(R.id.cashoutLinearLayout);
        sendMoneyLinearLayout=findViewById(R.id.sendMoneyLinearLayout);

        refferTv=findViewById(R.id.reffaridTv);
        phoneTv=findViewById(R.id.poneTv);
        accountStatusTv=findViewById(R.id.accountStatusTv);
        activeTv=findViewById(R.id.activeTv);
    }
    private void loadAppSetting() {
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ApiKey.getAppSetting()
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            try {
                                progressDialog.dismiss();
                                AppSettingModel appSetting=snapshot.getValue(AppSettingModel.class);
                                SettingDb settingDb=new SettingDb(MainActivity.this);
                                settingDb.setSetting(appSetting);

                            }catch(Exception e){
                                progressDialog.dismiss();
                                e.printStackTrace();
                            }
                        }else{
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.logout_menu){
            logout();
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        UserDb userDb=new UserDb(this);
        UserModel userModel=new UserModel("","","","","","","","","","",0,0);
        userDb.setUserData(userModel);
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }
}