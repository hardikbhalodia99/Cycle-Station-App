package com.example.loginpwd.Admin.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.loginpwd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class AdminUserDelete extends AppCompatActivity {

    Button btUserDel;
    Spinner SpDelUser;
    DatabaseReference dblist;
    List<String > listid=new ArrayList<String>();
    List<User> userList=new ArrayList<User>();
    String sid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_delete);

        btUserDel=findViewById(R.id.btUserDel);
        SpDelUser=findViewById(R.id.SpDelUser);

        dblist= FirebaseDatabase.getInstance().getReference("user");

        dblist.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    User u1=shot.getValue(User.class);
                    listid.add(u1.uid);
                    userList.add(u1);

                }

                ArrayAdapter<String > ad=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,listid);
                SpDelUser.setAdapter(ad);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        SpDelUser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                 sid=SpDelUser.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btUserDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DatabaseReference dbdel=FirebaseDatabase.getInstance().getReference();

                Query delq=dbdel.child("user").orderByChild("uid").equalTo(sid);

                delq.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot shot : dataSnapshot.getChildren())
                        {
                            shot.getRef().removeValue();

                            Toast.makeText(getApplicationContext(),"USER SUCESSFULLY DELETED...",Toast.LENGTH_LONG).show();
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
