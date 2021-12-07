package com.example.loginpwd.Admin.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.Random;

public class BookCycleCard extends Activity {

    Button btBookCycle,btCancel;
    TextView openTime,closeTime,availCycle,rent,deposit;
    Spinner cycleRegNo,hours;
    DatabaseReference stdbref,cydbref,dbref;
    List<Integer> listreg=new ArrayList<>();
    List<Integer> listhr=new ArrayList<>();
    List<Cycle> listcycle=new ArrayList<>();
    double lati,longi;
    String email,phone,sid,uid;
    int selectedhours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_cycle_card);

      btBookCycle=findViewById(R.id.BookCycCardBookCycle);
      btCancel=findViewById(R.id.BookCycCardCancel);
      openTime=findViewById(R.id.BookCycleCardOpentime);
      closeTime=findViewById(R.id.BookCycleCardClosetime);
      availCycle=findViewById(R.id.BookCycCardAvailCycles);
      rent=findViewById(R.id.BookCycCardRent);
      deposit=findViewById(R.id.BookCycCardDeposit);
      cycleRegNo=findViewById(R.id.BookCycCardSpinner);
        hours=findViewById(R.id.BookCycCardSpinnerHours);

        Intent ii=getIntent();
        lati = Double.parseDouble(ii.getStringExtra("Latitude"));
        longi = Double.parseDouble(ii.getStringExtra("Longitude"));
        email=ii.getStringExtra("Email");


        listhr.add(1);
        listhr.add(2);
        listhr.add(3);
        listhr.add(4);
        listhr.add(5);
        listhr.add(6);
        listhr.add(7);

        ArrayAdapter<Integer > ad=new ArrayAdapter<Integer>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,listhr);
        hours.setAdapter(ad);

        stdbref= FirebaseDatabase.getInstance().getReference("Station");
        stdbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    final Station s1=shot.getValue(Station.class);
                    if(s1.latitude==lati && s1.longitude==longi)
                    {
                        openTime.setText("OPEN TIME : "+s1.openingTime);
                        closeTime.setText("CLOSE TIME : "+s1.closingTime);
                        availCycle.setText("AVAIL CYCLE : "+s1.availableCycles);
                        rent.setText("RENT : 15 rs per hour");
                        deposit.setText("DEPOSIT : 100rs");
                        sid=s1.sid;
                        cydbref=FirebaseDatabase.getInstance().getReference("Cycle");
                        cydbref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for(DataSnapshot shot : dataSnapshot.getChildren())
                                {
                                    Cycle c1=shot.getValue(Cycle.class);
                                    if(c1.station.equals(s1.stationName))
                                    {
                                        listreg.add(c1.cycleRegNo);
                                        listcycle.add(c1);

                                    }

                                }

                                ArrayAdapter<Integer > ad2=new ArrayAdapter<Integer>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,listreg);
                                cycleRegNo.setAdapter(ad2);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        cycleRegNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dbref=FirebaseDatabase.getInstance().getReference("user");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    User u1=shot.getValue(User.class);
                    if(u1.emailId.equals(email))
                    {
                        phone=""+u1.phone;
                        uid=u1.uid;
                        break;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        hours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedhours=listhr.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btBookCycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random ra=new Random();

                int otp=ra.nextInt(8998)+1001;

                phone=phone.substring(2);

                Toast.makeText(getApplicationContext(),"OTP GENERATED IS : "+otp,Toast.LENGTH_LONG).show();

                SmsManager smsManager=SmsManager.getDefault();
                smsManager.sendTextMessage(phone,null,"YOUR OTP IS : "+otp,null,null);

                Intent ii=new Intent(getApplicationContext(),UserOtp.class);
                ii.putExtra("otp",otp);
                ii.putExtra("hours",""+selectedhours);
                ii.putExtra("uid",uid);
                ii.putExtra("sid",sid);
                startActivity(ii);
            }
        });




    }
}
