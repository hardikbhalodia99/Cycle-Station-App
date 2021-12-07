package com.example.loginpwd.Admin.Cycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginpwd.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminCycleInsert extends AppCompatActivity
{
    EditText etuid,etcyclestation,etcyclestatus,etimageurl,etcycleregno;
    Button btinsert,btcancel;
    DatabaseReference dbref;
    private static String uid;
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_cycle_insert);

            etuid=findViewById(R.id.etuid);
            etcycleregno=findViewById(R.id.etcycleregno);
            etcyclestation=findViewById(R.id.etcyclestation);
            etcyclestatus=findViewById(R.id.etcyclestatus);
            etimageurl=findViewById(R.id.etimageurl);
            btcancel=findViewById(R.id.btcancel);
            btinsert=findViewById(R.id.btinsert);


            dbref= FirebaseDatabase.getInstance().getReference("Cycle");//cretaing root


            btinsert.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    String cycleuid=etuid.getText().toString();
                    int cycleRegNo=Integer.parseInt(etcycleregno.getText().toString());
                    String cycleStation=etcyclestation.getText().toString();
                    String cycleStatus=etcyclestatus.getText().toString();
                    String imgUrl=etimageurl.getText().toString();

                    if(TextUtils.isEmpty(uid))
                    {
                        String id=dbref.push().getKey();
                        Cycle u1=new Cycle(cycleuid,cycleStation,cycleStatus,imgUrl,cycleRegNo);
                        dbref.child(id).setValue(u1);
                        Toast.makeText(getApplicationContext(),"CYCLE SUCEESSFULLY ADDED.....",Toast.LENGTH_LONG).show();

                    }

                    etuid.setText("");
                    etcyclestatus.setText("");
                    etcyclestation.setText("");
                    etcycleregno.setText("");
                    etimageurl.setText("");


                }
            });



            btcancel.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent i=new Intent(getApplicationContext(),AdminCycleHome.class);
                    startActivity(i);
                }
            });



        }
}



