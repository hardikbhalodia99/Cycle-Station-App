package com.example.loginpwd.Admin.Cycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.loginpwd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminCycleUpdate extends AppCompatActivity
{
    EditText SpCycleStation,SpCycleStatus,SpCycleimgUrl,SpCycleRegno;
    Spinner SpCycleUpdateID;
    Button btcancel,btupdate;
    DatabaseReference dbref,dbupd,dblist;
    List<String> listcid=new ArrayList<String>();
    List<Cycle> listCycle =new ArrayList<Cycle>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cycle_update);

        SpCycleStation=findViewById(R.id.SpCycleStation);
        SpCycleStatus=findViewById(R.id.SpCycleStatus);
        SpCycleimgUrl=findViewById(R.id.SpCycleimgUrl);
        SpCycleRegno=findViewById(R.id.SpCycleRegno);
        SpCycleUpdateID=findViewById(R.id.SpCycleUpdateID);

        btcancel=findViewById(R.id.btCyclecancel);
        btupdate=findViewById(R.id.btCycleupdate);

        dblist= FirebaseDatabase.getInstance().getReference("Cycle");

        dblist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            { Toast.makeText(getApplicationContext(),"t2222222.",Toast.LENGTH_SHORT).show();
                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Cycle s1= shot.getValue(Cycle.class);
                    listcid.add(s1.uid);
                    listCycle.add(s1);
                }
                Toast.makeText(getApplicationContext(),"T1111",Toast.LENGTH_LONG).show();
                ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,listcid);
                SpCycleUpdateID.setAdapter(ad);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        SpCycleUpdateID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id)
            {
                SpCycleStation.setText(listCycle.get(i).getStation());
                SpCycleimgUrl.setText(listCycle.get(i).getImageUrl());
                SpCycleStatus.setText(listCycle.get(i).getStatus());
                SpCycleRegno.setText(""+listCycle.get(i).getCycleRegNo());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"t33333",Toast.LENGTH_LONG).show();
                final String id=SpCycleUpdateID.getSelectedItem().toString();
                final String station =SpCycleStation.getText().toString();
                final String status=SpCycleStatus.getText().toString();
                final int regno=Integer.parseInt(SpCycleRegno.getText().toString());
                final String imgurl=SpCycleimgUrl.getText().toString();


                dbref=FirebaseDatabase.getInstance().getReference("Cycle");

                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for(DataSnapshot shot : dataSnapshot.getChildren())
                        {
                            Cycle c2=shot.getValue(Cycle.class);

                            if(c2.uid.equals(id))
                            {
                                dbupd=FirebaseDatabase.getInstance().getReference("Cycle").child(id);
                                Cycle cc=new Cycle(id,station,status,imgurl,regno);
                                dbupd.setValue(cc);
                                Toast.makeText(getApplicationContext(),"Cycle Successfully Updated..",Toast.LENGTH_LONG).show();
                                SpCycleRegno.setText("");
                                SpCycleimgUrl.setText("");
                                SpCycleStation.setText("");
                                SpCycleStatus.setText("");
                                break;
                            }

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
