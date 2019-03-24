package com.example.shoppingcart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoppingcart.Model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class loginActivity extends AppCompatActivity {

    private Button login;
    private EditText inputmobile,inputpassword;
    private ProgressDialog loadingbar;
    private String parentdb = "Users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.login_submit);
        inputmobile = (EditText) findViewById(R.id.login_moblie);
       inputpassword = (EditText) findViewById(R.id.login_password);
        loadingbar = new ProgressDialog(this);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loginUser();
            }

        });



    }

    private void loginUser() {
        String mobilenum = inputmobile.getText().toString();
        String pass =  inputpassword.getText().toString();
        if(TextUtils.isEmpty(mobilenum)){
            Toast.makeText(this,"Please Enter your  mobile number !!",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please Enter your password !!",Toast.LENGTH_SHORT).show();
        }
        else{
            loadingbar.setTitle("Login Account");
            loadingbar.setMessage("please wait while checking the credentials");
            loadingbar.setCanceledOnTouchOutside(false);
            loadingbar.show();

            AllowacesstoAccount(mobilenum,pass);



        }
    }

    private void AllowacesstoAccount(final String mobilenum, final String pass) {

        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference().child("Users");


        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            private static final String TAG = "HIIIIIIIIIIII";

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG, "onDataChange:==================> "+dataSnapshot);

                //if(dataSnapshot.child(parentdb).child(mobilenum).exists())
                if(dataSnapshot.hasChild(mobilenum)){




                    Users userdata = dataSnapshot.child(mobilenum).getValue(Users.class);

                    if(userdata.getMobilenum().equals(mobilenum)){
                        System.out.println("entered");
                        if(userdata.getPass().equals(pass)){
                            Toast.makeText(loginActivity.this,"loged in successfully !!",Toast.LENGTH_SHORT).show();
                            loadingbar.dismiss();
                            fetchData process = new fetchData();
                            process.execute();
                            Intent intent = new Intent(loginActivity.this,bottom.class);
                            startActivity(intent);

                        }

                    }

                } else {
                    Toast.makeText(loginActivity.this,"please check your details",Toast.LENGTH_SHORT).show();
                    loadingbar.dismiss();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
