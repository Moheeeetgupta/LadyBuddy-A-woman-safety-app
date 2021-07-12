package com.moheeeetgupta.ladybuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
  CardView siren,location,Settings, currentlocation,community;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        siren=findViewById (R.id.Siren);
        location=findViewById (R.id.send_Location);
        Settings=findViewById (R.id.Settings);
        currentlocation=findViewById (R.id.Currentlocation);
        community=findViewById (R.id.community);
        siren.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity ( new Intent(getApplicationContext (),Flashing.class));
            }
        });
        location.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (getApplicationContext (),Instructions.class));
            }
        });
        Settings.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (getApplicationContext (),SmsActivity.class));
            }
        });
        currentlocation.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (getApplicationContext (),CurrentLocation.class));
            }
        });
        community.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                startActivity (new Intent (getApplicationContext (),webactivity.class));
            }
        });
    }
}
