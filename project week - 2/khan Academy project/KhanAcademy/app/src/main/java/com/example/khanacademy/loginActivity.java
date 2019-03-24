package com.example.khanacademy;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class loginActivity extends AppCompatActivity {
    Button mlogin;
    EditText id,passkey,apikey,apisec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mlogin = (Button) findViewById(R.id.login_submit);
        id = (EditText) findViewById(R.id.login_moblie);
        passkey = (EditText) findViewById(R.id.login_password);
        apikey = (EditText) findViewById(R.id.login_apikey);
        apisec = (EditText) findViewById(R.id.login_apisecret);

        mlogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                final String userId = id.getText().toString();
                final String psswrd = passkey.getText().toString();
                final String api_key = apikey.getText().toString();
                final String api_sec = apisec.getText().toString();

                loginUser(userId,psswrd,api_key,api_sec);
            }

        });

    }

    public void loginUser(String userId, String psswrd, String api_key, String api_sec) {

        KhnData data_call = new KhnData(this);
        data_call.execute(userId,psswrd,api_key,api_sec);

    }

    public void go_to(Student[] stu) {

        if (stu != null) {

        }

        Intent intent = new Intent(loginActivity.this, Student_list.class);
        intent.putExtra("Student",stu);
        startActivity(intent);
    }


    public class KhnData extends AsyncTask<String, Void, Student[]> {


        String resp = "";
        loginActivity loginActivity;

        public KhnData(loginActivity loginActivity) {
            this.loginActivity = loginActivity;
        }

        @Override
        protected Student[] doInBackground(String... params) {

            if (params.length == 0) {
            }

            KhanAcademyAccess k_obj = new KhanAcademyAccess(params[0],params[1],params[2],params[3]);
            Student[] stu_array = {};

            try {
                String raw_response = k_obj.call();
                //Log.d("lolller",raw_response);
                 this.resp = k_obj.resp;
                 raw_response = "{\"results\":" + raw_response + "}";
                JSONParser parse = new JSONParser();
                JSONObject jobj = (JSONObject)parse.parse(raw_response);
                JSONArray jsonarr_1 = (JSONArray) jobj.get("results");
                stu_array = new Student[jsonarr_1.size()];
                for (int i = 0 ; i < jsonarr_1.size() ; i++) {
                    JSONObject jsonobj_1 = (JSONObject)jsonarr_1.get(i);
                    stu_array[i] = new Student(jsonobj_1.get("nickname").toString(),jsonobj_1.get("total_seconds_watched").toString(),jsonobj_1.get("points").toString());
                }


            } catch (Exception e) {
                Log.d("exc","exc block");
                e.printStackTrace();
            }


            return stu_array;
        }

        @Override
        protected void onPostExecute(Student[] stuData) {


            if (this.resp.equals("OK")) {
                this.loginActivity.go_to(stuData);
            }


        }
    }
}
