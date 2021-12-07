package com.example.loginpwd.Admin.Station;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginpwd.Admin.User.User;
import com.example.loginpwd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class StationCustomListView extends ArrayAdapter
{

    Activity context;
    List<Station> list;
    Button bdel;


    StationCustomListView(Activity context, List list) {
        super(context, R.layout.activity_station_custom_list_view, list);
        this.context = context;
        this.list = list;
    }


    @Override
    public View getView(final int position, View view, ViewGroup parent){



        LayoutInflater inflater=context.getLayoutInflater();

        View rowView=inflater.inflate(R.layout.activity_station_custom_list_view,null,false);

        bdel=rowView.findViewById(R.id.bdel);

        TextView tvsid=rowView.findViewById(R.id.tvAdminStationId);
        TextView tvname=rowView.findViewById(R.id.tvAdminStationName);
        TextView tvOpen=rowView.findViewById(R.id.tvAdminStationOpen);
        TextView tvClose=rowView.findViewById(R.id.tvAdminStationClose);
        TextView tvnoOfCycles=rowView.findViewById(R.id.tvAdminStationNoOfCycles);
        TextView tvAvail=rowView.findViewById(R.id.tvAdminStationAvail);

        tvname.setText("Name : "+list.get(position).stationName);
        tvsid.setText("ID : "+list.get(position).sid);
        tvOpen.setText("Opening Time : "+list.get(position).openingTime);
        tvClose.setText("Closing Time : "+list.get(position).closingTime);
        tvnoOfCycles.setText("Total No Of Cycles : "+list.get(position).noOfCycles);
        tvAvail.setText("Available Cycles : "+list.get(position).availableCycles);


        final String st=list.get(position).sid;

        bdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DatabaseReference dbdel= FirebaseDatabase.getInstance().getReference();

                Query delq=dbdel.child("Station").orderByChild("sid").equalTo(st);

                delq.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot shot : dataSnapshot.getChildren())
                        {
                            shot.getRef().removeValue();
                            list.remove(position);
                            list.clear();
                            Toast.makeText(context,"STATION SUCCESSFULLY DELETED...",Toast.LENGTH_LONG).show();

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
