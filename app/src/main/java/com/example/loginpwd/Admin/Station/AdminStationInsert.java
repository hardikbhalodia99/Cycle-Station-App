package com.example.loginpwd.Admin.Station;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginpwd.Admin.User.AdminUserHome;
import com.example.loginpwd.Admin.User.User;
import com.example.loginpwd.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminStationInsert extends Activity
{

    EditText etstid,etstname,etstlatitude,etstlongitude,etstdesc,etstopentime,etstclosetime,etstconducted,etnoCycles,etcyclesAvail;
    Button btinsert,btcancel;
    DatabaseReference dbref;
    private static String xxid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_station_insert);

        etstid=findViewById(R.id.etstid);
        etstname=findViewById(R.id.etstname);
        etstlatitude=findViewById(R.id.etstlatitude);
        etstlongitude=findViewById(R.id.etstlongitude);
        etstdesc=findViewById(R.id.etstdesc);
        etstopentime=findViewById(R.id.etstopentime);
        etstclosetime=findViewById(R.id.etstclosetime);
        etstconducted=findViewById(R.id.etstconducted);
        etnoCycles=findViewById(R.id.etnoCycles);
        etcyclesAvail=findViewById(R.id.etcyclesAvail);

        btinsert=findViewById(R.id.btinsert);
        btcancel=findViewById(R.id.btcancel);


        dbref= FirebaseDatabase.getInstance().getReference("Station"); //creating root


        btinsert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                String sid=etstid.getText().toString();
                String name=etstname.getText().toString();
                double lati=Double.parseDouble(etstlatitude.getText().toString());
                double longi=Double.parseDouble(etstlongitude.getText().toString());
                String description=etstdesc.getText().toString();
                String opentime=etstopentime.getText().toString();
                String closetime=etstclosetime.getText().toString();
                String conducted=etstconducted.getText().toString();
                int noOfCycles=Integer.parseInt(etnoCycles.getText().toString());
                int cyclesAvail=Integer.parseInt(etcyclesAvail.getText().toString());

                if(TextUtils.isEmpty(xxid))
                {
                    String id=dbref.push().getKey();
                    Station s1=new Station(sid,name,lati,longi,description,opentime,closetime,conducted,noOfCycles,cyclesAvail);
                    dbref.child(id).setValue(s1);
                    Toast.makeText(getApplicationContext(),"STATION SUCEESSFULLY ADDED.....",Toast.LENGTH_LONG).show();

                }

              //  etstid.setText("");
                //etstconducted.setText("");
                //etstclosetime.setText("");
             //   etstopentime.setText("");
           //     etstname.setText("");
             //   etstdesc.setText("");
             //   etnoCycles.setText("");
            //    etcyclesAvail.setText("");
                etstlongitude.setText("");
                etstlatitude.setText("");

            }
        });


        btcancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getApplicationContext(), AdminStationHome.class);
                startActivity(i);
            }
        });






    }
}
