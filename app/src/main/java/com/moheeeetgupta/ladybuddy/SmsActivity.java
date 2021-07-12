package com.moheeeetgupta.ladybuddy;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SmsActivity extends AppCompatActivity {

    TextInputEditText txt_pnumber1,txt_msg,txt_pnumber2,txt_pnumber3,txt_pnumber4;
    Button Save;


    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_sms);

        txt_msg = findViewById (R.id.txt_sms);

        txt_pnumber1 = findViewById (R.id.txt_phone_number1);
        txt_pnumber2 = findViewById (R.id.txt_phone_number2);
        txt_pnumber3 = findViewById (R.id.txt_phone_number3);
        txt_pnumber4= findViewById (R.id.txt_phone_number4);
        Save = findViewById (R.id.Save_btn);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient (this);


        //saving data code demo

        Save.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                String phone1=txt_pnumber1.getText ().toString ();
                String phone2=txt_pnumber2.getText ().toString ();
                String phone3=txt_pnumber3.getText ().toString ();
                String phone4=txt_pnumber4.getText ().toString ();
                String msg=txt_msg.getText ().toString ();

                SharedPreferences shrd=getSharedPreferences ("demo",MODE_PRIVATE);
                SharedPreferences.Editor editor=shrd.edit ();
                editor.putString ("phone1",phone1);
                editor.putString ("phone2",phone2);
                editor.putString ("phone3",phone3);
                editor.putString ("phone4",phone4);
                editor.putString ("msg",msg);
                editor.apply();
                txt_pnumber1.setText (phone1);
                txt_pnumber2.setText (phone2);
                txt_pnumber3.setText (phone3);
                txt_pnumber4.setText (phone4);
                txt_msg.setText (msg);
                if(!txt_pnumber1.getText ().toString ().equals ("") &&!txt_pnumber2.getText ().toString ().equals ("") && !txt_pnumber3.getText ().toString ().equals ("") && !txt_pnumber4.getText ().toString ().equals ("") ){
                    Toast.makeText (SmsActivity.this, "Saved...", Toast.LENGTH_SHORT).show ();

                }else if(!txt_pnumber1.getText ().toString ().equals ("")||!txt_pnumber2.getText ().toString ().equals ("")||!txt_pnumber3.getText ().toString ().equals ("")||!txt_pnumber4.getText ().toString ().equals ("")){
                    Toast.makeText (SmsActivity.this, "Saved but some fields are blank...", Toast.LENGTH_SHORT).show ();

                }else
                    Toast.makeText (SmsActivity.this, "Please enter the numbers first...", Toast.LENGTH_SHORT).show ();



            }
        });
        //Getting the value of shared preference back
        SharedPreferences getShared =getSharedPreferences("demo",MODE_PRIVATE);
        String Value1 =getShared.getString ("phone1","");
        txt_pnumber1.setText (Value1);
        String Value2 =getShared.getString ("phone2","");
        txt_pnumber2.setText (Value2);
        String Value3 =getShared.getString ("phone3","");
        txt_pnumber3.setText (Value3);
        String Value4 =getShared.getString ("phone4","");
        txt_pnumber4.setText (Value4);
        String Value =getShared.getString ("msg","I am in danger, please come fast...");
        txt_msg.setText (Value);




//saving data code demo ends here
    }

    private void requestionPermission(){
        ActivityCompat.requestPermissions (SmsActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);
    }


    public void btn_send(View view){
        int permissionCheck = ContextCompat.checkSelfPermission (this, Manifest.permission.SEND_SMS);
        if(permissionCheck== PackageManager.PERMISSION_GRANTED &&ActivityCompat.checkSelfPermission (SmsActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            SendLocationMessage();
        }else{
            ActivityCompat.requestPermissions (this,new String[]{Manifest.permission.SEND_SMS},0);
            ActivityCompat.requestPermissions (SmsActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);

        }

        //Calling function



                Intent intent = new Intent (Intent.ACTION_CALL);
                String phoneNumber = txt_pnumber1.getText ().toString ();
                if(phoneNumber.trim().isEmpty ()){
                    Toast.makeText (SmsActivity.this, "Please enter first number to make a call..", Toast.LENGTH_SHORT).show ();

                }else {

                    intent.setData (Uri.parse ("tel:" + phoneNumber));
                }
                if (ActivityCompat.checkSelfPermission (getApplicationContext (), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText (SmsActivity.this, "Please grant permission to make call...", Toast.LENGTH_SHORT).show ();
                    requestionPermission();
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }else {
                    startActivity (intent);
                }
                //calling function ends here
    }

    private void SendLocationMessage() {


        fusedLocationProviderClient.getLastLocation ().addOnCompleteListener (new OnCompleteListener<Location> () {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //Initialize Location
                Location location =task.getResult ();
                if(location!=null){

                    try {
                        //Initialize Geocoder
                        Geocoder geocoder =new Geocoder (SmsActivity.this, Locale.getDefault ());
                        //Initialize adress list
                        List<Address> addresses =geocoder.getFromLocation (
                                location.getLatitude (),location.getLongitude (),1
                        );
                        String phoneNumber1 =txt_pnumber1.getText ().toString ().trim () ;
                        String phoneNumber2 =txt_pnumber2.getText ().toString ().trim () ;
                        String phoneNumber3 =txt_pnumber3.getText ().toString ().trim () ;
                        String phoneNumber4 =txt_pnumber4.getText ().toString ().trim () ;
                        String Message =txt_msg.getText ().toString ().trim ();
                    if(!txt_pnumber1.getText ().toString ().equals ("")||!txt_pnumber2.getText ().toString ().equals ("")||!txt_pnumber3.getText ().toString ().equals ("")||!txt_pnumber4.getText ().toString ().equals ("")) {
                        if (!txt_pnumber1.getText ().toString ().equals ("")) {
                            SmsManager smsManager = SmsManager.getDefault ();
                            smsManager.sendTextMessage (phoneNumber1, null, Message + "I am at " + addresses.get (0).getLatitude () +
                                    "," + addresses.get (0).getLongitude () + ", " + addresses.get (0).getCountryName () +
                                    "," + addresses.get (0).getLocality () + ", " + addresses.get (0).getAddressLine (0), null, null);
                            Toast.makeText (SmsActivity.this, "Message sent...", Toast.LENGTH_SHORT).show ();
                        }

                        if (!txt_pnumber2.getText ().toString ().equals ("")) {
                            SmsManager smsManager = SmsManager.getDefault ();
                            smsManager.sendTextMessage (phoneNumber2, null, Message + "I am at " + addresses.get (0).getLatitude () +
                                    "," + addresses.get (0).getLongitude () + ", " + addresses.get (0).getCountryName () +
                                    "," + addresses.get (0).getLocality () + ", " + addresses.get (0).getAddressLine (0), null, null);
                            Toast.makeText (SmsActivity.this, "Message sent...", Toast.LENGTH_SHORT).show ();
                        }

                        if (!txt_pnumber3.getText ().toString ().equals ("")) {
                            SmsManager smsManager = SmsManager.getDefault ();
                            smsManager.sendTextMessage (phoneNumber3, null, Message + "I am at " + addresses.get (0).getLatitude () +
                                    "," + addresses.get (0).getLongitude () + ", " + addresses.get (0).getCountryName () +
                                    "," + addresses.get (0).getLocality () + ", " + addresses.get (0).getAddressLine (0), null, null);
                            Toast.makeText (SmsActivity.this, "Message sent...", Toast.LENGTH_SHORT).show ();
                        }
                        if (!txt_pnumber4.getText ().toString ().equals ("")) {
                            SmsManager smsManager = SmsManager.getDefault ();
                            smsManager.sendTextMessage (phoneNumber4, null, Message + "I am at " + addresses.get (0).getLatitude () +
                                    "," + addresses.get (0).getLongitude () + ", " + addresses.get (0).getCountryName () +
                                    "," + addresses.get (0).getLocality () + ", " + addresses.get (0).getAddressLine (0), null, null);
                            Toast.makeText (SmsActivity.this, "Message sent...", Toast.LENGTH_SHORT).show ();
                        }
                    }else{
                        Toast.makeText (SmsActivity.this, "Please give the phone number first...", Toast.LENGTH_SHORT).show ();
                    }

                    } catch (IOException e) {
                        e.printStackTrace ();
                    }

                }
            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult (requestCode, permissions, grantResults);
        switch (requestCode){
            case 0:

                if(grantResults.length>=0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){

                }
                else {
                    Toast.makeText (this, "You don't have required permissions", Toast.LENGTH_SHORT).show ();
                }

                break;
        }
    }


}
