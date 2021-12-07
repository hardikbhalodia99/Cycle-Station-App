package com.example.loginpwd.Admin.User;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ScrollingTabContainerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginpwd.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SingleUserUpdate extends Activity
{
    Button btSingleUserUpdate,btSingleUserCancel;
    EditText SingleUserName,SingleUserGender,SingleUserEmail,SingleUserPhone,SingleUserAdd,SingleUserPwd;
    Spinner SingleUserID;
    String xemail,cid,sid;
    DatabaseReference dbref,dbupd,dbcref;
    List<String > listid=new ArrayList<String>();
    List<User > listuser=new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_user_update);

        btSingleUserCancel=findViewById(R.id.btSingleUserCancel);
        btSingleUserUpdate=findViewById(R.id.btSingleUserUpdate);

        SingleUserAdd=findViewById(R.id.SingleUserUpdateAdd);
        SingleUserEmail=findViewById(R.id.SingleUserUpdateEmail);
        SingleUserGender=findViewById(R.id.SingleUserUpdateGender);
        SingleUserID=findViewById(R.id.SingleUserUpdateID);
        SingleUserName=findViewById(R.id.SingleUserUpdateName);
        SingleUserPhone=findViewById(R.id.SingleUserUpdatePhone);
        SingleUserPwd=findViewById(R.id.SingleUserUpdatePwd);

        Intent ii=getIntent();
        xemail=ii.getStringExtra("Email");

        dbref=FirebaseDatabase.getInstance().getReference("user");

        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot :  dataSnapshot.getChildren())
                {
                    User u2=shot.getValue(User.class);
                    if(u2.emailId.equals(xemail))
                    {Toast.makeText(getApplicationContext(),"found mail and put id",Toast.LENGTH_LONG).show();
                        cid=u2.uid;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        dbcref=FirebaseDatabase.getInstance().getReference("user");

        dbcref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Toast.makeText(getApplicationContext(),"text set",Toast.LENGTH_LONG).show();

                    User u1=shot.getValue(User.class);
                    if(u1.emailId.equals(xemail))
                    {
                        listid.add(u1.uid);
                        listuser.add(u1);

                    }


                }

                ArrayAdapter<String > ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,listid);
                SingleUserID.setAdapter(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       SingleUserID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

                SingleUserName.setText(listuser.get(i).getName());
                SingleUserAdd.setText(listuser.get(i).getAddress());
                SingleUserEmail.setText(listuser.get(i).getEmailId());
                SingleUserGender.setText(listuser.get(i).getGender());
                SingleUserPhone.setText(""+listuser.get(i).getPhone());
                SingleUserPwd.setText(listuser.get(i).getPassword());


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




            btSingleUserUpdate.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                                final String id=SingleUserID.getSelectedItem().toString();
                                final String name=SingleUserName.getText().toString();
                                final String gender=SingleUserGender.getText().toString();
                                final String mail=SingleUserEmail.getText().toString();
                                final String add=SingleUserAdd.getText().toString();
                                final long phone=Long.parseLong(SingleUserPhone.getText().toString());
                                final String pass=SingleUserPwd.getText().toString();


                                dbref.addValueEventListener(new ValueEventListener()
                                {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                                    {
                                        for(DataSnapshot shot : dataSnapshot.getChildren())
                                        {
                                            User u2=shot.getValue(User.class);

                                            if(u2.emailId.equals(xemail))
                                            {
                                                dbupd=FirebaseDatabase.getInstance().getReference("user").child(cid);
                                                User u3=new User(id,name,mail,gender,add,"User",phone,pass);
                                                dbupd.setValue(u3);
                                                Toast.makeText(getApplicationContext(),"USER UPDATED SUCCESSFULLY....",Toast.LENGTH_LONG).show();

                                                break;

                                            }


                                        }
                                         Intent ii=new Intent(getApplicationContext(),UserScreen.class);
                                          ii.putExtra("Email",xemail);
                                         startActivity(ii);
                                         finish();


                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });















                }
            });


















    }
}
