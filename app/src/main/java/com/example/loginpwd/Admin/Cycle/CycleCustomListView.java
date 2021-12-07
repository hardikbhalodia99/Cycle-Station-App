package com.example.loginpwd.Admin.Cycle;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.loginpwd.Admin.User.User;
import com.example.loginpwd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class CycleCustomListView extends ArrayAdapter<String>
{
    Activity context;
    List<Cycle> list;
    Button bcdel;

    CycleCustomListView(Activity context, List list)
    {
        super(context, R.layout.activity_cycle_custom_list_view, list);
        this.context = context;
        this.list = list;
    }


    @Override
    public View getView(final int position, View view, ViewGroup parent)
    {

        LayoutInflater inflater=context.getLayoutInflater();

        View rowView=inflater.inflate(R.layout.activity_cycle_custom_list_view,null,false);

         bcdel=rowView.findViewById(R.id.bdel);

        TextView tvAdminCycleStation=rowView.findViewById(R.id.tvAdminCycleStation);
        TextView tvStatus=rowView.findViewById(R.id.tvAdminCycleStatus);
        TextView tvuid=rowView.findViewById(R.id.tvAdminCycleUid);
        TextView tvRegno=rowView.findViewById(R.id.tvAdminCycleRegno);


        tvAdminCycleStation.setText("Station : "+list.get(position).station);
        tvStatus.setText("Status : "+list.get(position).status);
        tvuid.setText("UID : "+list.get(position).uid);
        tvRegno.setText("Cycle Reg No : "+list.get(position).cycleRegNo);

        final String st=list.get(position).uid;

        bcdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                DatabaseReference dbdel= FirebaseDatabase.getInstance().getReference();

                Query delq=dbdel.child("Cycle").orderByChild("uid").equalTo(st);

                delq.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot shot : dataSnapshot.getChildren())
                        {
                            shot.getRef().removeValue();
                            list.remove(position);
                            Toast.makeText(context,"USER SUCESSFULLY DELETED...",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });




        return rowView;




    }


}
