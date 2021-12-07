package com.example.loginpwd.Admin;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loginpwd.Admin.Cycle.AdminCycleHome;
import com.example.loginpwd.Admin.Station.AdminStationHome;
import com.example.loginpwd.Admin.User.AdminUserHome;
import com.example.loginpwd.R;

public class AdminUserInsert extends Activity
{
    Button bcycle,bstation,buser,bcomplain,bfb,bpay;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_insert);

       bcycle=findViewById(R.id.bcycle);
       bstation=findViewById(R.id.bstation);
       buser=findViewById(R.id.buser);
       bcomplain=findViewById(R.id.bcomplain);
       bfb=findViewById(R.id.bfb);
       bpay=findViewById(R.id.bpay);

        buser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getApplicationContext(), AdminUserHome.class);
                startActivity(i);
            }
        });

        bstation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i2=new Intent(getApplicationContext(), AdminStationHome.class);
                startActivity(i2);
            }
        });

        bcycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getApplicationContext(), AdminCycleHome.class);
                startActivity(i3);
            }
        });


    }
}
