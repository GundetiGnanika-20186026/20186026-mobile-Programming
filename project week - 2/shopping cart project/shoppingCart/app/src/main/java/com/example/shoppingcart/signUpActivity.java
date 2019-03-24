package com.example.shoppingcart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class signUpActivity extends AppCompatActivity {
    private Button createAccount;
    private EditText inputusername;
    private EditText inputmobile;
    private EditText inputpassword;
    private ProgressDialog loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        FirebaseApp.initializeApp(signUpActivity.this);

        createAccount = (Button) findViewById(R.id.signUp_btn);
        inputusername = (EditText) findViewById(R.id.signUp_username);
        inputmobile = (EditText) findViewById(R.id.signUp_moblie);
        inputpassword = (EditText) findViewById(R.id.signUp_password);
        loadingbar = new ProgressDialog(this);

        createAccount.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                createAccount();
            }

        });
    }

    private void createAccount() {
        String name  = inputusername.getText().toString();
        String mobilenum = inputmobile.getText().toString();
        String pass =  inputpassword.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please Enter your Name !!",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(mobilenum)){

            Toast.makeText(this,"Please Enter your  mobile number !!",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please Enter your password !!",Toast.LENGTH_SHORT).show();
        }else{
            loadingbar.setTitle("Create Account");
            loadingbar.setMessage("please wait while checking the credentials");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();

            validatephoneNumber(name,mobilenum,pass);


        }
    }

    private void validatephoneNumber(final String name, final String mobilenum, final String pass) {
           final DatabaseReference Rootref;

           Rootref = FirebaseDatabase.getInstance().getReference();
           Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   if(!(dataSnapshot.child("Users").child(mobilenum).exists())){
                       HashMap<String, Object> userdata = new HashMap<>();
                       userdata.put("mobilenum",mobilenum);
                       userdata.put("name",name);
                       userdata.put("pass",pass);
                       Rootref.child("Users").child(mobilenum).updateChildren(userdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               if(task.isSuccessful()){
                                   Toast.makeText(signUpActivity.this,"Your account has been created",Toast.LENGTH_SHORT).show();
                                   loadingbar.dismiss();
                                   Intent intent = new Intent(signUpActivity.this, loginActivity.class);
                                   startActivity(intent);
                               }
                               else{
                                   loadingbar.dismiss();
                                   Toast.makeText(signUpActivity.this,"Network error please try again",Toast.LENGTH_SHORT).show();

                               }
                           }
                       });

                   }else{
                       Toast.makeText(signUpActivity.this, "This" + mobilenum+"already Exists please try with another mobile number",Toast.LENGTH_SHORT);
                       loadingbar.dismiss();
                       Intent intent = new Intent(signUpActivity.this, MainActivity.class);
                       startActivity(intent);
                   }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError databaseError) {


               }
           });
    }
}
