package com.example.loginpwd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.*;
import androidx.annotation.NonNull;

import com.example.loginpwd.Admin.AdminUserInsert;
import com.example.loginpwd.Admin.User.User;
import com.example.loginpwd.Admin.User.UserScreen;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends Activity  {

    Button blog,register,bclear;
    EditText info,pwd;
    FirebaseAuth fAuth;
DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register=findViewById(R.id.register);
        blog=findViewById(R.id.blog);
        info=findViewById(R.id.info);
        pwd=findViewById(R.id.pwd);
        bclear=findViewById(R.id.bclear);
        fAuth=FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Intent ii=new Intent(getApplicationContext(),register_new_user.class);
                startActivity(ii);
            }
        });

            info.setText("mehulpatel@gmail.com");
            pwd.setText("mehulpatel");


            blog.setOnClickListener( new View.OnClickListener()
            {

                @Override
                public void onClick(View v)
                {
                    final String mail=info.getText().toString();
                    final String pass=pwd.getText().toString();

                    dbref= FirebaseDatabase.getInstance().getReference("user");

                    info.setText("");
                    pwd.setText("");
                    if(mail.equals("a")&& pass.equals("1"))
                    {
                        Intent ii=new Intent(getApplicationContext(), AdminUserInsert.class);
                        startActivity(ii);
                    }
                    else
                    {
                        fAuth.signInWithEmailAndPassword(mail, pass)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                                {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task)
                                    {

                                        if (task.isSuccessful())
                                        {
                                            dbref.addValueEventListener(new ValueEventListener()
                                            {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                                                {
                                                    for(DataSnapshot shot : dataSnapshot.getChildren())
                                                    {

                                                        User u2=shot.getValue(User.class);
                                                        if(u2.role.equals("Admin")&&u2.emailId.equals(mail))
                                                        {
                                                            Intent ii=new Intent(getApplicationContext(), AdminUserInsert.class);
                                                            startActivity(ii);
                                                        }
                                                        else if(u2.role.equals("User")&& u2.emailId.equals(mail))
                                                        {
                                                            Intent ii=new Intent(getApplicationContext(), UserScreen.class);
                                                            ii.putExtra("Email",mail);
                                                            startActivity(ii);
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) { }
                                            });
                                        }
                                        else
                                        {
                                            Toast.makeText(getApplicationContext(), "YOU ARE UNAUTHORISED!!!! pls check your email id and password", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                }
            });

            bclear.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    info.setText("");
                    pwd.setText("");
                }
            });

    }
}



//**********************************************************************************************************************8
//
//EXTRA

   /*
                                         String mail=blog.getText().toString();
                                         String pass=pwd.getText().toString();

                                         blog.setText("");
                                         pwd.setText("");

                                         fAuth.createUserWithEmailAndPassword(mail,pass)
                                                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                                         {

                                             @Override
                                             public void onComplete(@NonNull Task<AuthResult> task)
                                             {
                                                if(task.isSuccessful())
                                                {
                                                    Toast.makeText(getApplicationContext(),"USER REGESTREDED SUCESSFULLY", Toast.LENGTH_LONG).show();
                                                }
                                                else
                                                {
                                                    Toast.makeText(getApplicationContext(),"USER REGESTREDED FAIL", Toast.LENGTH_LONG).show();

                                                }
                                             }
                                         });
                                     }
        });

        blog.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String mail=blog.getText().toString();
                String pass=pwd.getText().toString();

                blog.setText("");
                pwd.setText("");

                fAuth.signInWithEmailAndPassword(mail,pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {

                                if(task.isSuccessful())
                                {
                                    Toast.makeText(getApplicationContext(),"WELCOME AUTHORISED USER", Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"YOU ARE UNAUTHORISED!!!!", Toast.LENGTH_LONG).show();

                                }
                            }
                        });*/
//***************************************************************************************8
 /*
                    if(mail.equals("a")&& pass.equals("1"))
                    {
                        Intent ii=new Intent(getApplicationContext(), AdminUserInsert.class);
                        startActivity(ii);
                    }
                    else
                    {
                        fAuth.createUserWithEmailAndPassword(mail,pass)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                                {

                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task)
                                    {
                                        if(task.isSuccessful())
                                        {
                                            Toast.makeText(getApplicationContext(),"USER REGESTREDED SUCESSFULLY", Toast.LENGTH_LONG).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(getApplicationContext(),"USER REGESTREDED FAIL", Toast.LENGTH_LONG).show();

                                        }
                                    }
                                });
                    }

                    */