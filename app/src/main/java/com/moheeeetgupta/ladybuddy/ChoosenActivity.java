package com.moheeeetgupta.ladybuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoosenActivity extends AppCompatActivity {
    CardView instruction, testing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_choosen );

        instruction = findViewById( R.id.inst );
        testing = findViewById(R.id.mag);

        instruction.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getApplicationContext(), MagnoInst.class ) );
            }
        } );

        testing.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getApplicationContext(), Magnetometer.class ) );
            }
        } );


    }
}