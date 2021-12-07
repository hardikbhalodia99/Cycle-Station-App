package com.example.loginpwd.Admin.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginpwd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class BookCyclePayment extends AppCompatActivity {

    Button btPay,btCancel;
    TextView hours,Payment;
    String uid,sid,hrs,bookid;
    int finalpay,fhrs;
    DatabaseReference bookdbref,paydbref;
    private static String xxuid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_cycle_payment);

        btCancel=findViewById(R.id.btcancel);
        btPay=findViewById(R.id.btPay);
        hours=findViewById(R.id.tvHours);
        Payment=findViewById(R.id.tvTotalPay);

        Intent ii=getIntent();
        hrs=ii.getStringExtra("hours");
       uid= ii.getStringExtra("uid");
       sid= ii.getStringExtra("sid");

        fhrs=Integer.parseInt(hrs);
        finalpay=(fhrs*15)+50;

        hours.setText(hrs);
        Payment.setText(""+finalpay);


        btPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                bookdbref= FirebaseDatabase.getInstance().getReference("BookCycle");

                bookdbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (TextUtils.isEmpty(xxuid))
                        {
                            bookid = bookdbref.push().getKey();

                            BookCycle b1=new BookCycle(bookid,sid,uid,""+Calendar.getInstance().getTime(),"hello",fhrs);
                            bookdbref.child(bookid).setValue(b1);
                            Toast.makeText(getApplicationContext(), "BOOKED SUCCESSFULLY TO DATABASE", Toast.LENGTH_LONG).show();

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                paydbref= FirebaseDatabase.getInstance().getReference("Payment");

                paydbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (TextUtils.isEmpty(xxuid))
                        {
                            String payid = bookdbref.push().getKey();

                            Payment p1=new Payment(payid,bookid,finalpay-50,finalpay);
                            paydbref.child(payid).setValue(p1);
                            Toast.makeText(getApplicationContext(), "PAYMENT SUCCESSFULLY TO DATABASE", Toast.LENGTH_LONG).show();

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent ii=new Intent(getApplicationContext(),UserScreen.class);
                ii.putExtra("hours",hrs);
                ii.putExtra("amtpaid",""+finalpay);
                ii.putExtra("uid",uid);
                ii.putExtra("sid",sid);
                startActivity(ii);

            }
        });




    }
}
