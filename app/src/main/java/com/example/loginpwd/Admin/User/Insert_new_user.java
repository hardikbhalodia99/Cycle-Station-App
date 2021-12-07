package com.example.loginpwd.Admin.User;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.loginpwd.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Insert_new_user extends Activity
{
    EditText etuser,etemail,etphone,etgender,etadd,etAdminUserInsertRole,etpwd;
    Button bcancel,bauser;
    DatabaseReference dbref;
    FirebaseAuth fauth;
    private static String xxuid;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_new_user);

        etpwd=findViewById(R.id.etpwd);
        etuser=findViewById(R.id.etuser);
        etemail=findViewById(R.id.etemail);
        etphone=findViewById(R.id.etphone);
        etgender=findViewById(R.id.etgender);
        etadd=findViewById(R.id.etadd);
        bcancel=findViewById(R.id.bcancel);
        bauser=findViewById(R.id.bauser);
        etAdminUserInsertRole=findViewById(R.id.etAdminUserInsertRole);
        fauth=FirebaseAuth.getInstance();
        dbref= FirebaseDatabase.getInstance().getReference("user");//cretaing root


        bauser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String pwd=etpwd.getText().toString();
                final String name=etuser.getText().toString();
                final String mail=etemail.getText().toString();
                final long phone=Long.parseLong(etphone.getText().toString());
                final String gen=etgender.getText().toString();
                final String add=etadd.getText().toString();
                String role=etAdminUserInsertRole.getText().toString();


                if(role.equalsIgnoreCase("user"))
                    role="User";
                else if(role.equalsIgnoreCase("admin"))
                    role="Admin";
                final String rle=role;

                fauth.createUserWithEmailAndPassword(mail,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {

                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(), "USER AUTHENTICATED SUCCESSFULLY", Toast.LENGTH_LONG).show();
                            dbref = FirebaseDatabase.getInstance().getReference("user");

                            if (TextUtils.isEmpty(xxuid))
                            {
                                String id = dbref.push().getKey();
                                User u1 = new User(id, name, mail, gen, add, rle, phone, pwd);
                                dbref.child(id).setValue(u1);
                                Toast.makeText(getApplicationContext(), "USER ADDED SUCCESSFULLY TO DATABASE", Toast.LENGTH_LONG).show();

                                etpwd.setText("");
                                etuser.setText("");
                                etemail.setText("");
                                etphone.setText("");
                                etgender.setText("");
                                etadd.setText("");
                                etAdminUserInsertRole.setText("");
                            }
                        }
                    }
                });


            }
        });


        bcancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getApplicationContext(),AdminUserHome.class);
                startActivity(i);
            }
        });










    }
}

