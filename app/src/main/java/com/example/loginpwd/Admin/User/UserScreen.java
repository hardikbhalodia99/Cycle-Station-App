package com.example.loginpwd.Admin.User;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.loginpwd.R;

public class UserScreen extends AppCompatActivity {

    Button btviewOrUpdate,btlocate,btcomplain;
    TextView tvamtpaid,tvtime;
    String email,sid,uid;
    int payment,hours;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);

        btviewOrUpdate=findViewById(R.id.btviewOrUpdate);
        btlocate=findViewById(R.id.btlocate);
        btcomplain=findViewById(R.id.btcomplain);
        tvamtpaid=findViewById(R.id.tvamtpaid);
        tvtime=findViewById(R.id.tvtime);

        Intent i=getIntent();
        if(i.hasExtra("Email"))
        {
            email=i.getStringExtra("Email");
        }
        else
        {
            hours=Integer.parseInt(i.getStringExtra("hours"));
            uid= i.getStringExtra("uid");
            sid= i.getStringExtra("sid");
            payment=Integer.parseInt(i.getStringExtra("amtpaid"));

            tvamtpaid.setText(""+payment);
            tvtime.setText(""+hours);
        }



        btviewOrUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ii=new Intent(getApplicationContext(),SingleUserUpdate.class);
                ii.putExtra("Email",email);
                startActivity(ii);
            }
        });


        btlocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(getApplicationContext(),StationMapsActivity.class);
                ii.putExtra("Email",email);
                startActivity(ii);
            }
        });

    }
}
