package com.example.loginpwd.Admin.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginpwd.R;

public class UserOtp extends AppCompatActivity {

    Button bsend,bcancel;
    EditText etotp;
    int hrs;
    String genotp,recievedOTP;
    String uid,sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_otp);

        bcancel=findViewById(R.id.bcancel);
        bsend=findViewById(R.id.bsend);
        etotp=findViewById(R.id.etuserotp);

        Intent ii=getIntent();
        genotp=ii.getStringExtra("otp");
        sid=ii.getStringExtra("sid");
        uid=ii.getStringExtra("uid");
        hrs=Integer.parseInt(ii.getStringExtra("hours"));

        recievedOTP=etotp.getText().toString();

        bsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(true)
                {
                    Intent i1=new Intent(getApplicationContext(),BookCyclePayment.class);
                    i1.putExtra("hours",""+hrs);
                    i1.putExtra("uid",uid);
                    i1.putExtra("sid",sid);
                    startActivity(i1);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"WRONG OTP ",Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}
