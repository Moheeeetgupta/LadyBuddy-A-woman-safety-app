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



            /*magX.setText("x:"+event.values[0]);
            magY.setText("y:"+event.values[1]);
            magZ.setText("z:"+event.values[2]);

             */


            double x;

            x = sqrt (event.values[0]*event.values[0]+event.values[1]*event.values[1]+event.values[2]*event.values[2]);
            magR.setText(Double.toString(x));
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.beep);

            if(x>70.0) {
                mediaPlayer.start();
                mediaPlayer.setLooping(true);
                if (x>70.0 && x< 90.0) {
                    Toast.makeText(getApplicationContext(), "Potential electronic device detected", Toast.LENGTH_SHORT).show();
                }
                if(x>90.0){
                    Toast.makeText(getApplicationContext(), "Finally electronic device detected", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                mediaPlayer.setLooping(true);
                mediaPlayer.stop();
            }

        }



    }


    private void to_string(TextView magX) {
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}