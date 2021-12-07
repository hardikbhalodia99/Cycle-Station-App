package com.example.loginpwd.Admin.Cycle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loginpwd.Admin.Station.AdminStationHome;
import com.example.loginpwd.R;

public class AdminCycleHome extends Activity
{
    Button binsertcycle,bviewcycle,bupdatecycle,bdeletecycle;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cycle_home);

        binsertcycle=findViewById(R.id.binsertcycle);
        bviewcycle=findViewById(R.id.bviewcycle);
        bupdatecycle=findViewById(R.id.bupdatecycle);
        bdeletecycle=findViewById(R.id.bdeletecycle);

        binsertcycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), AdminCycleInsert.class);
                startActivity(i);
            }
        });

        bviewcycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(), AdminCycleView.class);
                startActivity(i2);
            }
        });

        bupdatecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(getApplicationContext(), AdminCycleUpdate.class);
                startActivity(i3);
            }
        });


    }
}
