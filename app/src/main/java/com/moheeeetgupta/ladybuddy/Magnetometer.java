package com.moheeeetgupta.ladybuddy;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Math.sqrt;

public class Magnetometer extends AppCompatActivity implements SensorEventListener {


    private TextView magR ;

    MediaPlayer mediaPlayer;
    //MediaPlayer mediaPlayer2


    private double magD;

    private Sensor magnetometer ;

    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetometer);




        magR = (TextView) findViewById(R.id.textView4);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);



        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (magnetometer != null){
            sensorManager.registerListener(Magnetometer.this,magnetometer,SensorManager.SENSOR_DELAY_NORMAL);
        }else {
            magR.setText("Magnetometer not Supported");
        }


    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        Sensor sensor = event.sensor;


        if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            double x;
            x = sqrt (event.values[0]*event.values[0]+event.values[1]*event.values[1]+event.values[2]*event.values[2]);
            magR.setText(String.format("%.2f", x));
            double Pd=70d;
            double Fd=90d;
            if(Double.compare (x,Pd)>0 && Double.compare (x,Fd)<0) {
                mediaPlayer=null;
                mediaPlayer = MediaPlayer.create(this, R.raw.beep);
                mediaPlayer.start();
                Toast.makeText(this, "Potential electronic device detected", Toast.LENGTH_SHORT).show();

            }
            else if(Double.compare (x,Fd)>0){
                mediaPlayer=null;
                mediaPlayer = MediaPlayer.create(this, R.raw.beepd);
                mediaPlayer.start();
                Toast.makeText(this, "Finally electronic device detected", Toast.LENGTH_SHORT).show();

            }else {
                mediaPlayer=null;
            }

        }


    }


    private void to_string(TextView magX) {
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }}



