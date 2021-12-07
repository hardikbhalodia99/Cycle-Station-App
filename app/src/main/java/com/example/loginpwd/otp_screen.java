package com.example.loginpwd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginpwd.Admin.User.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class otp_screen extends Activity
{

    EditText etotp;
    Button bsend,bcancel;
    DatabaseReference dbref;
    FirebaseAuth fAuth;
    private static String xxuid="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_screen);

        fAuth=FirebaseAuth.getInstance();

        etotp=findViewById(R.id.etotp);
        bsend=findViewById(R.id.bsend);
        bcancel=findViewById(R.id.bcancel);

        Intent ii=getIntent();
        final String vericode=ii.getStringExtra("vericode");
        final String name=ii.getStringExtra("name");
        final String pass=ii.getStringExtra("pass");
        final String email=ii.getStringExtra("email");
        final long phone=Long.parseLong(ii.getStringExtra("phone"));
        final String gen=ii.getStringExtra("gender");
        final String address=ii.getStringExtra("address");



        bcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i2);
            }
        });

        bsend.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String otp=etotp.getText().toString();
                etotp.setText("");

                PhoneAuthCredential credential= PhoneAuthProvider.getCredential(vericode,otp);

                fAuth.signInWithCredential(credential)
                 .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                 {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task)
                     {
                         if (task.isSuccessful())
                         {
                             Toast.makeText(getApplicationContext(), "OTP REGISTERED.... ", Toast.LENGTH_LONG).show();

                             fAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                 @Override
                                 public void onComplete(@NonNull Task<AuthResult> task)
                                 {
                                     if (task.isSuccessful())
                                     {
                                         Toast.makeText(getApplicationContext(), "USER AUTHENTICATED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                                         dbref = FirebaseDatabase.getInstance().getReference("user");
                                         if (TextUtils.isEmpty(xxuid))
                                         {
                                             String id = dbref.push().getKey();
                                             User u1 = new User(id, name, email, gen, address, "User", phone, pass);
                                             dbref.child(id).setValue(u1);
                                             Toast.makeText(getApplicationContext(), "USER ADDED SUCCESSFULLY TO DATABASE", Toast.LENGTH_LONG).show();

                                             Intent i2=new Intent(getApplicationContext(),MainActivity.class);
                                             startActivity(i2);


                                         }
                                     }
                                     else
                                     {
                                         Toast.makeText(getApplicationContext(), "YOU ARE UNAUTHORISED!!!!", Toast.LENGTH_LONG).show();
                                         Intent i2=new Intent(getApplicationContext(),MainActivity.class);
                                         startActivity(i2);

                                     }


                                 }
                             });

                         }
                         else
                         {
                             Toast.makeText(getApplicationContext(), "INVALID OTP!!!!!! ", Toast.LENGTH_LONG).show();
                             Intent i2=new Intent(getApplicationContext(),MainActivity.class);
                             startActivity(i2);

                         }
                     }
                     });
                 }



            });
        }

    }

