package com.teamDroiders.ladybuddy;


import android.Manifest;
import android.content.BroadcastReceiver;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;


public class ScreenOnOffReceiver extends BroadcastReceiver {
    MediaPlayer mediaPlayer;
    public final static String SCREEN_TOGGLE_TAG = "SCREEN_TOGGLE_TAG";
    private int powerBtnTapCount = 0;
    FusedLocationProviderClient fusedLocationProviderClient;

    String Value1, Value2, Value3, Value4, Value;
    int timer=0;
    // Time is in millisecond so 50sec = 50000 I have used
    // countdown Interveal is 1sec = 1000 I have used
    public CountDownTimer countDownTimer=new CountDownTimer (30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            timer++;

            Log.d (SCREEN_TOGGLE_TAG, "time"+timer);

        }

        @Override
        public void onFinish() {
            timer=0;
            powerBtnTapCount=0;
        }
    };
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction ();
        if((Intent.ACTION_SCREEN_OFF.equals (action)||Intent.ACTION_SCREEN_ON.equals (action))&&powerBtnTapCount==0){

            powerBtnTapCount++;
            countDownTimer.start ();
            Log.d (SCREEN_TOGGLE_TAG, "Power button tapped in timer "+timer+"  "+ powerBtnTapCount);


        }else
        if(powerBtnTapCount>0) {
           if(timer<30) {
               if (Intent.ACTION_SCREEN_OFF.equals (action) || Intent.ACTION_SCREEN_ON.equals (action)) {  // ACTION_SCREEN_OFF :- read by just tapping on it where this is used
                   powerBtnTapCount++;
                   Log.d (SCREEN_TOGGLE_TAG, "Power button tapped in timer "+timer+"  "+ powerBtnTapCount);
               }
           }else{
               powerBtnTapCount=0;

               if (Intent.ACTION_SCREEN_OFF.equals (action) || Intent.ACTION_SCREEN_ON.equals (action)) {  // ACTION_SCREEN_OFF :- read by just tapping on it where this is used
                   powerBtnTapCount++;
                   countDownTimer.start ();
                   Log.d (SCREEN_TOGGLE_TAG, "Power button tapped in timer "+timer+"  "+ powerBtnTapCount);

               }

           }

        }
        if(mediaPlayer==null) {
            mediaPlayer = MediaPlayer.create (context.getApplicationContext (), R.raw.police_siren);
        }
        if(powerBtnTapCount ==6){ //?
            mediaPlayer.start ();
            mediaPlayer.setLooping (true);
            powerBtnTapCount=0;
            countDownTimer.cancel ();
            timer=0;
        }
        if (powerBtnTapCount ==3) { // ?
            if(mediaPlayer.isPlaying ()){
                mediaPlayer.stop ();
                mediaPlayer.setLooping (false);
                mediaPlayer=null;
            }
            //Getting the value of shared preference back
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient (context.getApplicationContext ());

            SharedPreferences getShared = context.getSharedPreferences ("demo", MODE_PRIVATE); // demo?
            Value1 = getShared.getString ("phone1", "").trim ();

            Value2 = getShared.getString ("phone2", "").trim ();

            Value3 = getShared.getString ("phone3", "").trim ();

            Value4 = getShared.getString ("phone4", "").trim ();

            Value = getShared.getString ("msg", "I am in danger, please come fast...").trim ();

            tryIt (context);


        }

    }

    public void tryIt(Context context) {
        SendLocationMessage (context);

        if(!Value1.trim ().equals ("")) {
            /**
             * ContextCompat :- It is a class for replacing some work with base context.
             * For example if you used before something like getContext().getColor(R.color.black);
             * Now its deprecated since android 6.0 (API 22+) so you should use: getContext().getColor(R.color.black,theme);
             */
            if (ContextCompat.checkSelfPermission (context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                makeCall (context);
            }
        }


    }

    //Calling function
    private void makeCall(Context context) {
        //Calling function
        Intent intent = new Intent (Intent.ACTION_CALL); // ACTION_CALL :- read about it by tapping on where it is used
        String phoneNumber = Value1;
        intent.setData (Uri.parse ("tel:" + phoneNumber)); // intent.setData() and Uri.parse() :- read about it by tapping on where it is used


        // setFlags(), FLAG_ACTIVITY_NEW_TASK and FLAG_ACTIVITY_CLEAR_TASK :- read about these all by tapping on where it is used
        intent.addFlags(Intent. FLAG_ACTIVITY_NEW_TASK);


        context.startActivity (intent);
//        intent.setFlags (Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Log.d ("Called on  ", Value1 );


    }

    private void SendLocationMessage(final Context context) {

        // ACCESS_FINE_LOCATION, PERMISSION_GRANTED, PackageManager and ACCESS_COARSE_LOCATION :-   read about these all by tapping on where it is used
        if (ActivityCompat.checkSelfPermission (context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission (context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        fusedLocationProviderClient.getLastLocation ().addOnCompleteListener (new OnCompleteListener<Location> () {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //Initialize Location
                Location location = task.getResult ();
                String Message = Value;
                if (location != null) {

                    try {


                        // Locale.getDefault() :- read about these all by tapping on where it is used
                        //Initialize Geocoder
                        Geocoder geocoder = new Geocoder (context, Locale.getDefault ());
                        //Initialize adress list
                        List<Address> addresses = geocoder.getFromLocation ( // geocoder.getFromLocation, location.getLatitude() and location.getLongitude() :-  read about these all by tapping on where it is used
                                location.getLatitude (), location.getLongitude (), 1
                        );
                        Message=Message + "I am at " + addresses.get (0).getLatitude () +
                                "," + addresses.get (0).getLongitude () + ", " + addresses.get (0).getCountryName () +
                                "," + addresses.get (0).getLocality () + ", " + addresses.get (0).getAddressLine (0);


                    } catch (IOException e) {
                        e.printStackTrace ();
                    }

                }else{
                    Message =Message + "Software was not able to retrieve live location due to some internal errors..";

                }
                String phoneNumber1 = Value1;
                String phoneNumber2 =Value2;
                String phoneNumber3 = Value3;
                String phoneNumber4 = Value4;
                Log.d ("Message sent as  ", Value1 + " " + Value2 + " " + Value3 + " " + Value4 + " " + Value + " ");

                if (!Value1.equals ("") || !Value2.equals ("") || !Value3.equals ("") || !Value4.equals ("")) {
                    if (!Value1.equals ("")) {

                        // SmsManager, SmsManager.getDefault () and smsManager.sendTextMessage()  :- read about these all by tapping on where it is used
                        SmsManager smsManager = SmsManager.getDefault ();
                        smsManager.sendTextMessage (phoneNumber1, null,Message , null, null);
                    }

                    if (!Value2.equals ("")) {
                        SmsManager smsManager = SmsManager.getDefault ();
                        smsManager.sendTextMessage (phoneNumber2, null, Message, null, null);
                    }

                    if (!Value3.equals ("")) {
                        SmsManager smsManager = SmsManager.getDefault ();
                        smsManager.sendTextMessage (phoneNumber3, null, Message, null, null);
                    }
                    if (!Value4.equals ("")) {
                        SmsManager smsManager = SmsManager.getDefault ();
                        smsManager.sendTextMessage (phoneNumber4, null, Message, null, null);
                    }
                }
            }
        });
    }
}