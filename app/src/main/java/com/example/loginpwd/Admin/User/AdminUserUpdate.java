package com.example.loginpwd.Admin.User;

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

import com.example.loginpwd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminUserUpdate extends AppCompatActivity
{

    Button btupdate,btcancel;
    EditText SpUserUpdateName,SpUserUpdateEmail,SpUserUpdateRole,SpUserUpdateGender,SpUserUpdateAdd,SpUserUpdatePhone;
    Spinner SpUserUpdateID;
    DatabaseReference dblist,dbref,dbupd;
    List<String> listid=new ArrayList<String>();
    List<User> listUser=new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_update);

        SpUserUpdateAdd=findViewById(R.id.SpUserUpdateAdd);
        SpUserUpdateName=findViewById(R.id.SpUserUpdateName);
        SpUserUpdateEmail=findViewById(R.id.SpUserUpdateEmail);
        SpUserUpdateGender=findViewById(R.id.SpUserUpdateGender);
        SpUserUpdateRole=findViewById(R.id.SpUserUpdateRole);
        SpUserUpdatePhone=findViewById(R.id.SpUserUpdatePhone);

        SpUserUpdateID=findViewById(R.id.SpUserUpdateID);
        btcancel=findViewById(R.id.btUsercancel);
        btupdate=findViewById(R.id.btUserupdate);


        dblist= FirebaseDatabase.getInstance().getReference("user");

        dblist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    User u1=shot.getValue(User.class);
                    listid.add(u1.uid);
                    listUser.add(u1);

                }

                ArrayAdapter<String > ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,listid);
                SpUserUpdateID.setAdapter(ad);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SpUserUpdateID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                SpUserUpdateName.setText(listUser.get(i).getName());
                SpUserUpdateAdd.setText(listUser.get(i).getAddress());
                SpUserUpdateEmail.setText(listUser.get(i).getEmailId());
                SpUserUpdateGender.setText(listUser.get(i).getGender());
                SpUserUpdatePhone.setText(""+listUser.get(i).getPhone());
                SpUserUpdateRole.setText(listUser.get(i).getRole());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String id=SpUserUpdateID.getSelectedItem().toString();
                final String name=SpUserUpdateName.getText().toString();
                final String email=SpUserUpdateEmail.getText().toString();
                final long phone=Long.parseLong(SpUserUpdatePhone.getText().toString());
                final String addr=SpUserUpdateAdd.getText().toString();
                final String gen=SpUserUpdateGender.getText().toString();
                final String role=SpUserUpdateRole.getText().toString();

                dbref=FirebaseDatabase.getInstance().getReference("user");

                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot shot : dataSnapshot.getChildren())
                        {
                            User u2=shot.getValue(User.class);

                            if(u2.uid.equals(id))
                            {
                                dbupd=FirebaseDatabase.getInstance().getReference("user").child(id);
                                User us=new User(id,name,email,gen,addr,role,phone);
                                dbupd.setValue(us);
                                Toast.makeText(getApplicationContext(),"USER UPDATED SUCCESSFULLY....",Toast.LENGTH_LONG).show();
                                SpUserUpdateName.setText("");
                                SpUserUpdateAdd.setText("");
                                SpUserUpdateEmail.setText("");
                                SpUserUpdateGender.setText("");
                                SpUserUpdatePhone.setText("");
                                SpUserUpdateRole.setText("");
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
