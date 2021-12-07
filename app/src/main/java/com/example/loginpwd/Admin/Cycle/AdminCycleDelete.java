package com.example.loginpwd.Admin.Cycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.loginpwd.Admin.User.User;
import com.example.loginpwd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminCycleDelete extends Activity {

    Button btCycledel;
    Spinner spCycledel;
    DatabaseReference dbref,dblist;
    List<String > listid=new ArrayList<String>();
    List<Cycle> cycleList=new ArrayList<Cycle>();
    String cid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cycle_delete);

        btCycledel=findViewById(R.id.btCycleDel);
        spCycledel=findViewById(R.id.SpDelCycle);

        dblist= FirebaseDatabase.getInstance().getReference("Cycle");

        dblist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Cycle c1=shot.getValue(Cycle.class);
                    listid.add(c1.uid);
                    cycleList.add(c1);

                }

                ArrayAdapter<String > ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,listid);
                spCycledel.setAdapter(ad);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        spCycledel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                cid=spCycledel.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btCycledel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dbdel=FirebaseDatabase.getInstance().getReference();

                Query delq=dbdel.child("user").orderByChild("uid").equalTo(cid);

                delq.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot shot : dataSnapshot.getChildren())
                        {
                            shot.getRef().removeValue();
                            Toast.makeText(getApplicationContext(),"CYCLE SUCESSFULLY DELETED...",Toast.LENGTH_LONG).show();
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
