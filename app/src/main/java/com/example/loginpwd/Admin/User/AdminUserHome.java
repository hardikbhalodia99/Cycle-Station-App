package com.example.loginpwd.Admin.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;

import com.example.loginpwd.R;

public class AdminUserHome extends Activity
{

    Button binsertuser,bdeleteuser,bupdateuser,bviewuser;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user_home);

        binsertuser=findViewById(R.id.binsertuser);
        bdeleteuser=findViewById(R.id.bdeleteuser);
        bupdateuser=findViewById(R.id.bupdateuser);
        bviewuser=findViewById(R.id.bviewuser);

        binsertuser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View vv)
            {
                Intent i1=new Intent(getApplicationContext(),Insert_new_user.class);
                startActivity(i1);


            }
        });

        bviewuser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(getApplicationContext(),AdminUserView.class);
                startActivity(i);


            }
        });


        bupdateuser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent ii=new Intent(getApplicationContext(),AdminUserUpdate.class);
                startActivity(ii);


            }
        });

        bdeleteuser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent ii=new Intent(getApplicationContext(),AdminUserDelete.class);
                startActivity(ii);


            }
        });
    }
}
