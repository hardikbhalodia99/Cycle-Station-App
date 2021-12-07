package com.example.loginpwd.Admin.Station;

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

import com.example.loginpwd.Admin.Cycle.Cycle;
import com.example.loginpwd.Admin.Station.Station;
import com.example.loginpwd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminStationUpdate extends AppCompatActivity
{
    EditText SpStationUpdateName,SpStationUpdateConducted,SpStationUpdateLati,SpStationUpdateLongi,SpStationUpdateOpen,SpStationUpdateClose,SpStationUpdatenoofcycles,SpStationUpdatecyclesAvail,SpStationUpdateDesc;
    Spinner SpStationUpdateID;
    Button btScancel,btSupdate;
    DatabaseReference dbref,dbupd,dblist;
    List<String> listsid=new ArrayList<String>();
    List<Station> listStation =new ArrayList<Station>();




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_station_update);


        SpStationUpdateClose=findViewById(R.id.SpStationUpdateClose);
        SpStationUpdateDesc=findViewById(R.id.SpStationUpdateDesc);
        SpStationUpdateConducted=findViewById(R.id.SpStationUpdateConducted);
        SpStationUpdatecyclesAvail=findViewById(R.id.SpStationUpdatecyclesAvail);
        SpStationUpdateLati=findViewById(R.id.SpStationUpdateLati);
        SpStationUpdateLongi=findViewById(R.id.SpStationUpdateLongi);
        SpStationUpdateName=findViewById(R.id.SpStationUpdateName);
        SpStationUpdateOpen=findViewById(R.id.SpStationUpdateOpen);
        SpStationUpdatenoofcycles=findViewById(R.id.SpStationUpdatenoofcycles);
        

        SpStationUpdateID=findViewById(R.id.SpStationUpdateID);
        btScancel=findViewById(R.id.btScancel);
        btSupdate=findViewById(R.id.btSupdate);


        dblist= FirebaseDatabase.getInstance().getReference("Station");

        dblist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Station w1=shot.getValue(Station.class);
                    listsid.add(w1.sid);
                    listStation.add(w1);

                }

                ArrayAdapter<String > ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,listsid);
                SpStationUpdateID.setAdapter(ad);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SpStationUpdateID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SpStationUpdateClose.setText(listStation.get(i).getClosingTime());
                SpStationUpdateDesc.setText(listStation.get(i).getDescription());
                SpStationUpdateConducted.setText(listStation.get(i).getConductedBy());
                SpStationUpdateOpen.setText(listStation.get(i).getOpeningTime());
                SpStationUpdatenoofcycles.setText(""+listStation.get(i).getNoOfCycles());
                SpStationUpdateClose.setText(listStation.get(i).getClosingTime());
                SpStationUpdatecyclesAvail.setText(""+listStation.get(i).getAvailableCycles());
                SpStationUpdateLati.setText(""+listStation.get(i).getLatitude());
                SpStationUpdateLongi.setText(""+listStation.get(i).getLongitude());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btSupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String id=SpStationUpdateID.getSelectedItem().toString();
                final String name=SpStationUpdateName.getText().toString();
                final String conducted=SpStationUpdateConducted.getText().toString();
                final long noofcycles=Long.parseLong(SpStationUpdatenoofcycles.getText().toString());
                final long avail=Long.parseLong(SpStationUpdatecyclesAvail.getText().toString());
                final String desc=SpStationUpdateDesc.getText().toString();
                final String open=SpStationUpdateOpen.getText().toString();
                final String close=SpStationUpdateClose.getText().toString();
                double lati=Double.parseDouble(SpStationUpdateLati.getText().toString());
                double longi=Double.parseDouble(SpStationUpdateLongi.getText().toString());


                dbref=FirebaseDatabase.getInstance().getReference("Station");

                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot shot : dataSnapshot.getChildren())
                        {
                            Station u2=shot.getValue(Station.class);

                            if(u2.sid.equals(id))
                            {
                                dbupd=FirebaseDatabase.getInstance().getReference("Station").child(id);
                                Station us=new Station();
                                dbupd.setValue(us);
                                Toast.makeText(getApplicationContext(),"Station UPDATED SUCCESSFULLY....",Toast.LENGTH_LONG).show();
                                SpStationUpdateName.setText("");
                                SpStationUpdateDesc.setText("");
                                SpStationUpdateConducted.setText("");
                                SpStationUpdateOpen.setText("");
                                SpStationUpdatenoofcycles.setText("");
                                SpStationUpdateClose.setText("");
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
