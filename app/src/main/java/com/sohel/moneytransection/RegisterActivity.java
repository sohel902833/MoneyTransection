package com.sohel.moneytransection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.sohel.moneytransection.ApiKey.ApiKey;
import com.sohel.moneytransection.Local.Database.UserDb;
import com.sohel.moneytransection.Model.UserModel;

import java.util.Random;

public class RegisterActivity extends AppCompatActivity {

    private EditText nameEt,addressEt,referEt;
    private Spinner countrySpinner,citySpinner;
    private EditText phoneEt,passwordEt;
    private Button registerButton;
    private ProgressBar progressBar;
    private TextView loginLink;

    private   ArrayAdapter countrySpinnerAdapter;
    private   ArrayAdapter citySpinnerAdapter;


    String[] countrys={"Country","Bangladesh","India","Pakistan","Afganistan"};
    String[] citys={"City","Rangpur","Dhaka","Barishal","Sylhet","Mymansing"};


    String selectedCountry="Country";
    String selectedCity="City";
    String name,phone,password,address,refer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();

        countrySpinnerAdapter=new ArrayAdapter(RegisterActivity.this,R.layout.item_layout,R.id.spinnerHeaderTExt,countrys);
        countrySpinner.setAdapter(countrySpinnerAdapter);

        citySpinnerAdapter=new ArrayAdapter(RegisterActivity.this,R.layout.item_layout,R.id.spinnerHeaderTExt,citys);
        citySpinner.setAdapter(citySpinnerAdapter);




        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 name =nameEt.getText().toString();
                 phone=phoneEt.getText().toString().trim();
                 password=passwordEt.getText().toString().trim();
                 address=addressEt.getText().toString();
                 selectedCountry=countrySpinner.getSelectedItem().toString();
                 selectedCity=citySpinner.getSelectedItem().toString();
                 refer=referEt.getText().toString().trim();



                 if(name.isEmpty()){
                    showError(nameEt,"Write Your Name");
                }
                 else if(selectedCountry.equals("Country")){
                     Toast.makeText(RegisterActivity.this, "Select Your Country", Toast.LENGTH_SHORT).show();
                 }
                 else if(selectedCity.equals("City")){
                     Toast.makeText(RegisterActivity.this, "Select Your City", Toast.LENGTH_SHORT).show();
                 }
                 else if(refer.isEmpty()){
                     showError(referEt,"Enter Your Refer Id.");
                 }  else if(address.isEmpty()){
                     showError(addressEt,"Please Write Your Address");
                 }
                 else if(phone.isEmpty()){
                    showError(phoneEt,"Please enter Your Phone Number with country Code.");
                }else if(password.isEmpty()){
                    showError(passwordEt,"Please Enter Password");
                }else{
                    checkUserExists();
                }



            }
        });

    }
    private void init(){
        loginLink=findViewById(R.id.login_link);
        nameEt=findViewById(R.id.nameEt);
        referEt=findViewById(R.id.referEt);
        addressEt=findViewById(R.id.addressEt);
        phoneEt=findViewById(R.id.phoneEt);
        addressEt=findViewById(R.id.addressEt);
        countrySpinner=findViewById(R.id.country_Spinner);
        citySpinner=findViewById(R.id.city_Spinner);
        passwordEt=findViewById(R.id.passwordEt);
        registerButton=findViewById(R.id.register_Button);
        progressBar=findViewById(R.id.progressBar);
    }
    private void showError(EditText editText, String message){
        editText.setError(message);
        editText.requestFocus();
    }
    private void checkUserExists() {
        progressBar.setVisibility(View.VISIBLE);
        ApiKey.getUserRef().child(phone)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "This Phone Number Have Already An Account", Toast.LENGTH_SHORT).show();
                        }else{
                            registerUser();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressBar.setVisibility(View.GONE);

                    }
                });

    }
    private void registerUser() {
        String currentTime=String.valueOf(System.currentTimeMillis());
        String userId=currentTime.substring(currentTime.length()-7,currentTime.length());

        StringBuilder stringBuilder=new StringBuilder(userId);
        String myRefer=stringBuilder.reverse().toString()+String.valueOf(new Random().nextInt(100));


        UserModel userModel=new UserModel(
            name,address,selectedCountry,selectedCity,phone,password,userId,refer,myRefer,"inactive",0,0
         );
        ApiKey.getUserRef().child(phone)
                .setValue(userModel)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            UserDb userDb=new UserDb(RegisterActivity.this);
                           userDb.setUserData(userModel);
                            sendUserToUserMainActivity();
                        }else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void sendUserToUserMainActivity() {
        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
        finish();
    }
}