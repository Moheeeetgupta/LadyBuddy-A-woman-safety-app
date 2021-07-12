package com.moheeeetgupta.ladybuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CurrentLocation extends AppCompatActivity {
        Button btLocation;
        TextView textView1,textView2,textView3,textView4,textView5;
        FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);

        textView1=findViewById (R.id.text_view1);
        textView2=findViewById (R.id.text_view2);
        textView3=findViewById (R.id.text_view3);
        textView4=findViewById (R.id.text_view4);
        textView5=findViewById (R.id.text_view5);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient (this);

                if(ActivityCompat.checkSelfPermission (CurrentLocation.this,
                        Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                    getLocation();
                }else{
                    ActivityCompat.requestPermissions (CurrentLocation.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
                }
            }



    private void getLocation() {
        fusedLocationProviderClient.getLastLocation ().addOnCompleteListener (new OnCompleteListener<Location> () {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //Initialize Location
                Location location =task.getResult ();
                if(location!=null){

                    try {
                        //Initialize Geocoder
                        Geocoder geocoder =new Geocoder (CurrentLocation.this, Locale.getDefault ());
                        //Initialize adress list
                        List<Address>addresses =geocoder.getFromLocation (
                                location.getLatitude (),location.getLongitude (),1
                        );
                        //SEt latitude on TextView
                        textView1.setText (Html.fromHtml ("<font color='#6200EE'><b>Latitude :</b><br></font>"
                        +addresses.get (0).getLatitude ()
                        ));
                        //SetLongitude
                        textView2.setText (Html.fromHtml ("<font color='#6200EE'><b>Longitude :</b><br></font>"
                                +addresses.get (0).getLongitude ()
                        ));
                        //SetCountryName
                        textView3.setText (Html.fromHtml ("<font color='#6200EE'><b>Country Name :</b><br></font>"
                                +addresses.get (0).getCountryName ()
                        ));
                        //SetLocality
                        textView4.setText (Html.fromHtml ("<font color='#6200EE'><b>Locality:</b><br></font>"
                                +addresses.get (0).getLocality ()
                        ));
                        //SetLocality
                        textView5.setText (Html.fromHtml ("<font color='#6200EE'><b>Exact Address:</b><br></font>"
                                +addresses.get (0).getAddressLine (0)
                        ));
                    } catch (IOException e) {
                        e.printStackTrace ();
                    }

                }
            }
        });
    }
}
