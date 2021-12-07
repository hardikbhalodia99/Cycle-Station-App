package com.example.loginpwd.Admin.User;

import androidx.annotation.NonNull;


import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginpwd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class UserCustomListView extends ArrayAdapter<String>
{
    Activity context;
    List<User> list;
    Button bdel;

    UserCustomListView(Activity context, List list) {
        super(context, R.layout.activity_user_custom_list_view, list);
        this.context = context;
        this.list = list;
    }


    @Override
    public View getView(final int position, View view, ViewGroup parent){



        LayoutInflater inflater=context.getLayoutInflater();

        View rowView=inflater.inflate(R.layout.activity_user_custom_list_view,null,false);

        bdel=rowView.findViewById(R.id.bdel);

        TextView tvphone=rowView.findViewById(R.id.tvAdminUserViewPhone);
        TextView tvname=rowView.findViewById(R.id.tvAdminUserViewName);
        TextView tvEmail=rowView.findViewById(R.id.tvAdminUserViewEmail);
        TextView tvRole=rowView.findViewById(R.id.tvAdminUserViewRole);


        tvname.setText("Name= "+list.get(position).name);
        tvEmail.setText("Email="+list.get(position).emailId);
        tvRole.setText("Role="+list.get(position).role);
        tvphone.setText("Phone="+list.get(position).phone);
        final String mail=""+list.get(position).emailId;


        bdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


                        DatabaseReference dbdel= FirebaseDatabase.getInstance().getReference();

                        Query delq=dbdel.child("user").orderByChild("emailId").equalTo(mail);

                        delq.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot shot : dataSnapshot.getChildren())
                                {
                                    shot.getRef().removeValue();
                                    list.remove(position);
                                    list.clear();
                                    Toast.makeText(context,"USER SUCCESSFULLY DELETED...",Toast.LENGTH_LONG).show();

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
