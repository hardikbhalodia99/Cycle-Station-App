package com.example.loginpwd.Admin.Station;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loginpwd.R;

public class AdminStationHome extends Activity {

    Button binsertstation,bdeletestation,bupdatestation,bviewstation;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_station_home);

        binsertstation=findViewById(R.id.binsertstation);
        bdeletestation=findViewById(R.id.bdeletestation);
        bupdatestation=findViewById(R.id.bupdatestation);
        bviewstation=findViewById(R.id.bviewstation);


        binsertstation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i1=new Intent(getApplicationContext(),AdminStationInsert.class);
                startActivity(i1);
            }
        });

        bviewstation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(),AdminStationView.class);
                startActivity(i2);
            }
        });

        bupdatestation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i1=new Intent(getApplicationContext(),AdminStationUpdate.class);
                startActivity(i1);
            }
        });

    }
}
