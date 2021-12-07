package com.example.loginpwd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class register_new_user extends AppCompatActivity
{

    EditText etuser,etemail,etpwd,etcpwd,etphone,etgender,etadd;
    Button bcancel,breg;

    String verificationCode;
    String name,email,pass,confpass,phone,gen,address;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_user);

        etuser=findViewById(R.id.etuser);
        etemail=findViewById(R.id.etemail);
        etpwd=findViewById(R.id.etpwd);
        etcpwd=findViewById(R.id.etcpwd);
        etphone=findViewById(R.id.etphone);
        etgender=findViewById(R.id.etgender);
        etadd=findViewById(R.id.etadd);
        bcancel=findViewById(R.id.bcancel);
        breg=findViewById(R.id.breg);

        bcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i2);
            }
        });


        breg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                name=etuser.getText().toString();
                email=etemail.getText().toString();
                pass=etpwd.getText().toString();
                confpass=etcpwd.getText().toString();
                phone="+91"+etphone.getText().toString();
                gen=etgender.getText().toString();
                address=etadd.getText().toString();

                //set empty all edit text

                PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

                mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
                    {
                        Toast.makeText(getApplicationContext(),"PHONE VERIFIED.....",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e)
                    {
                        Toast.makeText(getApplicationContext(),"PHONE VERIFICATION FAILED.....",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onCodeSent(String reqcode,PhoneAuthProvider.ForceResendingToken forceResendingToken)
                    {
                        super.onCodeSent(reqcode,forceResendingToken);
                        verificationCode=reqcode;
                        Toast.makeText(getApplicationContext(),"CODE : "+verificationCode,Toast.LENGTH_LONG).show();

                        if(pass.equals(confpass))
                        {
                            Intent ii=new Intent(getApplicationContext(),otp_screen.class);
                            ii.putExtra("vericode",verificationCode);
                            ii.putExtra("name",name);
                            ii.putExtra("pass",pass);
                            ii.putExtra("email",email);
                            ii.putExtra("gender",gen);
                            ii.putExtra("phone",phone);
                            ii.putExtra("address",address);

                            startActivity(ii);

                        }
                    }

                };


                PhoneAuthProvider.getInstance().verifyPhoneNumber(phone,60l, TimeUnit.SECONDS,register_new_user.this,mCallbacks);

            }
        });






    }
}
