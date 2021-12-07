package com.example.loginpwd.Admin.Cycle;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.loginpwd.Admin.User.UserCustomListView;
import com.example.loginpwd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminCycleView extends Activity
{

    DatabaseReference dbref;
    ListView lvAdminCycleView;
  //  String data="";
    List<Cycle> list=new ArrayList<Cycle>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cycle_view);

        lvAdminCycleView=findViewById(R.id.lvAdminCycleView);
        dbref= FirebaseDatabase.getInstance().getReference("Cycle");




        dbref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {

                for(DataSnapshot shot : dataSnapshot.getChildren())
                {
                    Cycle c1=shot.getValue(Cycle.class);
                    //      data=data+u1.uid+" , "+u1.name+" , "+u1.emailId+"\n";
                    list.add(c1);
                }

               // Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
                //    ArrayAdapter ad=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,list);

                CycleCustomListView ucl=new CycleCustomListView(AdminCycleView.this,list);
                lvAdminCycleView.setAdapter(ucl);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });





    }
}
