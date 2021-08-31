package com.teamDroiders.ladybuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;

public class Flashing extends AppCompatActivity {
    ImageView imageviewflashing;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_flashing);
        imageviewflashing=findViewById (R.id.imageactivity);
        startSiren ();
        startLights ();
    }
    public  void startSiren(){
        mediaPlayer =MediaPlayer.create (this,R.raw.police_siren);
        mediaPlayer.start ();
        mediaPlayer.setLooping (true);
    }
    // @SuppressLint :- android.annotation.SuppressLint. Indicates that Lint should ignore the specified warnings for the annotated element
    @SuppressLint("WrongConstant")
    public void startLights(){
        ObjectAnimator anim= ObjectAnimator.ofInt (imageviewflashing,"BackgroundColor", Color.RED,Color.BLUE);
        anim.setDuration (120);
        anim.setEvaluator (new ArgbEvaluator ()); // read by tapping on it
        anim.setRepeatMode (Animation.REVERSE);
        anim.setRepeatCount (Animation.INFINITE);
        anim.start ();
    }

    @Override
    public void onBackPressed() {
        mediaPlayer.stop ();
        super.onBackPressed ();
    }
}
