package com.sohel.moneytransection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.sohel.moneytransection.ApiKey.ApiKey;
import com.sohel.moneytransection.Local.Database.UserDb;
import com.sohel.moneytransection.Model.UserModel;

public class LoginActivity extends AppCompatActivity {
    private TextView registerLink_Tv;
    private EditText phoneEt,passwordEt;
    private Button loginButton;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        registerLink_Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone=phoneEt.getText().toString().trim();
                String password=passwordEt.getText().toString().trim();
                if(phone.isEmpty()){
                    showError(phoneEt,"Please enter Your Phone Number with country Code.");
                }else if(password.isEmpty()){
                    showError(passwordEt,"Please Enter Password");
                }else{
                    checkUserExists(phone,password);
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        UserDb userDb=new UserDb(LoginActivity.this);
        String userName=userDb.getUserName();
        String userPhone=userDb.getUserPhone();
        if(!userName.isEmpty() || !userPhone.isEmpty()){
            sendUserToUserMainActivity();
        }
    }

    private void init() {
        progressBar=findViewById(R.id.progressBar);
        registerLink_Tv=findViewById(R.id.registration_link);
        phoneEt=findViewById(R.id.phoneEt);
        passwordEt=findViewById(R.id.passwordEt);
        loginButton=findViewById(R.id.loginButtonEt);
    }
    private void showError(EditText editText, String message){
        editText.setError(message);
        editText.requestFocus();
    }
    private void checkUserExists(String phone, String password) {
        progressBar.setVisibility(View.VISIBLE);

        ApiKey.getUserRef().child(phone)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            UserModel userModel=snapshot.getValue(UserModel.class);
                            if(userModel.getPhone().equals(phone) && userModel.getPassword().equals(password)){
                                progressBar.setVisibility(View.GONE);
                                UserDb userDb=new UserDb(LoginActivity.this);
                               userDb.setUserData(userModel);
                                sendUserToUserMainActivity();
                                Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                            }else{
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Phone or Password is Wrong", Toast.LENGTH_SHORT).show();
                            }

                        }else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "User Not Found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressBar.setVisibility(View.GONE);

                    }
                });
    }

    private void sendUserToUserMainActivity() {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

}