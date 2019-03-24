package com.example.shoppingcart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class finalpage extends AppCompatActivity {
    TextView placed;
    Button continue1;
    Button logout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalpage);

        placed = (TextView) findViewById(R.id.oredr);
        continue1 = (Button) findViewById(R.id.continue_shopping);
        logout1 = (Button) findViewById(R.id.logout);

        continue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(finalpage.this, "Enjoy shopping !!!!!!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(finalpage.this, bottom.class);
                startActivity(intent);
            }
        });


        logout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(finalpage.this, "Enjoy shopping !!!!!!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(finalpage.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}
