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

    MediaPlayer mediaPlayer1;
    MediaPlayer mediaPlayer2;


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
            magR.setText(Double.toString(x));


            mediaPlayer1 = MediaPlayer.create(getApplicationContext(), R.raw.beep);
            mediaPlayer2 = MediaPlayer.create(getApplicationContext(), R.raw.beepd);


            if(x>70.0 && x< 90.0) {
                if(mediaPlayer2.isPlaying()){
                    //mediaPlayer2.setLooping(false);
                    mediaPlayer2.pause();
                }
                mediaPlayer1.start();
                mediaPlayer1.setLooping(true);
                Toast.makeText(getApplicationContext(), "Potential electronic device detected", Toast.LENGTH_SHORT).show();

            }else if(x>90.0){

                mediaPlayer1.setLooping(false);
                mediaPlayer1.stop();
                mediaPlayer2.start();
                mediaPlayer2.setLooping(true);
                Toast.makeText(getApplicationContext(), "Finally electronic device detected", Toast.LENGTH_SHORT).show();

            }

            else{
                mediaPlayer1.setLooping(false);
                mediaPlayer1.stop();
                mediaPlayer2.setLooping(false);
                mediaPlayer2.stop();
            }

        }



    }


    private void to_string(TextView magX) {
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }



    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer1 != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer1.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer1 = null;


            if (mediaPlayer2 != null) {
                // Regardless of the current state of the media player, release its resources
                // because we no longer need it.
                mediaPlayer2.release();

                // Set the media player back to null. For our code, we've decided that
                // setting the media player to null is an easy way to tell that the media player
                // is not configured to play an audio file at the moment.
                mediaPlayer2 = null;


            }
        }


    }}