package com.example.loginpwd.Admin.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.loginpwd.Admin.Cycle.AdminCycleHome;
import com.example.loginpwd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminUserView extends Activity
{

    DatabaseReference dbref;
    ListView lvAdminUserView;
    String data="";
    List<User> list=new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_view);

        lvAdminUserView=findViewById(R.id.lvAdminUserView);
        dbref= FirebaseDatabase.getInstance().getReference("user");



        dbref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    User u1=shot.getValue(User.class);
              //      data=data+u1.uid+" , "+u1.name+" , "+u1.emailId+"\n";
                    list.add(u1);
                }

             //   Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
            //    ArrayAdapter ad=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,list);

                    UserCustomListView ucl=new UserCustomListView(AdminUserView.this,list);
                lvAdminUserView.setAdapter(ucl);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });



    }
}
