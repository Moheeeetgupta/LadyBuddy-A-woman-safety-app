package com.teamDroiders.ladybuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.RequiresApi;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MagnoInst extends AppCompatActivity {
    private RadioButton bedroom, bathroom, changingroom, outside;
    private Button next;
    RadioGroup radioGroup;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_magno_inst );

        bedroom = findViewById( R.id.bedroom );
        bathroom = findViewById( R.id.baathroom );
        changingroom = findViewById( R.id.changingroom );
        outside = findViewById( R.id.outside );

        next = findViewById( R.id.next );
        radioGroup = findViewById( R.id.radiogroup );


        next.setOnClickListener( new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                int radioid = radioGroup.getCheckedRadioButtonId();
                if(findViewById( radioid ) == bedroom){
                    // Toast.makeText( MainActivity.this, "bedroom tapped", Toast.LENGTH_SHORT ).show();
                    startActivity( new Intent( getApplicationContext(), Bedroom.class ) );
                }else if(findViewById( radioid ) == bathroom){
                    startActivity( new Intent( getApplicationContext(), Bathroom.class ) );
                }else if(findViewById( radioid ) == changingroom){
                    startActivity( new Intent( getApplicationContext(), ChangingRoom.class ) );
                }else if(findViewById( radioid ) == outside){
                    startActivity( new Intent( getApplicationContext(), Outside.class ) );
                }

            }
        } );


    }
}