package com.moheeeetgupta.ladybuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    CardView siren, location, Settings, currentlocation, community;

    RecyclerView recycleview;
    Timer timer;
    TimerTask timerTask;
    int position;
    LinearLayoutManager layoutManager;


    ViewGroup progressView;
    protected boolean isProgressShowing = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Intent backgroundService = new Intent( getApplicationContext(), ScreenOnOffBackgroundService.class );
        this.startService( backgroundService );
        Log.d( ScreenOnOffReceiver.SCREEN_TOGGLE_TAG, "Activity onCreate" );

        siren = findViewById( R.id.Siren );
        location = findViewById( R.id.send_Location );
        Settings = findViewById( R.id.Settings );
        currentlocation = findViewById( R.id.Currentlocation );
        // community=findViewById (R.id.community);
        siren.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getApplicationContext(), Flashing.class ) );
            }
        } );
        location.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getApplicationContext(), Instructions.class ) );
            }
        } );
        Settings.setOnClickListener( v -> startActivity( new Intent( getApplicationContext(), SmsActivity.class ) ) );
        currentlocation.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getApplicationContext(), CurrentLocation.class ) );
            }
        } );
        /*
        community.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( getApplicationContext(), webactivity.class ) );
            }
        } );

         */
       // showProgressingView();
        getSuperHeroes();
      //  hideProgressingView();
    }

    private void getSuperHeroes() {
        showProgressingView();

        RetrofitClient.getApi().getsuperHeroes( "4662f45afce949fea09fbbbe7183eca6",
                10,
                "women"

        ).enqueue( new Callback<NewsResults>() {
            @Override
            public void onResponse(Call<NewsResults> call, Response<NewsResults> response) {
                if (response.isSuccessful()) {
                    ArrayList<News> myitemsList = (ArrayList<News>) response.body().getArticles();


                    recycleview = findViewById( R.id.reclcle_list );

                    layoutManager = new LinearLayoutManager( MainActivity.this, LinearLayoutManager.HORIZONTAL, false );
                    recycleview.setLayoutManager( layoutManager );

                    NewsAdapter bannerAdapter = new NewsAdapter( MainActivity.this, myitemsList );
                    recycleview.setAdapter( bannerAdapter );

                    hideProgressingView();
                    if (myitemsList != null) {
                        position = Integer.MAX_VALUE / 2;
                        recycleview.scrollToPosition( position );
                    }

                    SnapHelper snapHelper = new LinearSnapHelper();
                    snapHelper.attachToRecyclerView( recycleview);
                    recycleview.smoothScrollBy( 5, 0 );

                 //   hideProgressingView();
                    recycleview.addOnScrollListener( new RecyclerView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                            super.onScrollStateChanged( recyclerView, newState );

                            if (newState == 1) {
                                stopAutoScrollBanner();
                            } else if (newState == 0) {
                                position = layoutManager.findFirstCompletelyVisibleItemPosition();
                                runAutoScrollBanner();
                            }
                        }
                    } );
                }


            }

            @Override
            public void onFailure(Call<NewsResults> call, Throwable t) {
                Toast.makeText( MainActivity.this, "Unsuccessful ", Toast.LENGTH_SHORT ).show();
            }
        });
    }






    @Override
    protected void onResume() {
        super.onResume();
        runAutoScrollBanner();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAutoScrollBanner();
    }

    private void stopAutoScrollBanner() {
        if (timer != null && timerTask != null) {
            timerTask.cancel();
            timer.cancel();
            timer = null;
            timerTask = null;
            position = layoutManager.findFirstCompletelyVisibleItemPosition();
        }
    }

    private void runAutoScrollBanner() {
        if (timer == null && timerTask == null) {
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (position == Integer.MAX_VALUE) {
                        position = Integer.MAX_VALUE / 2;
                        recycleview.scrollToPosition(position);
                        recycleview.smoothScrollBy(5, 0);
                    } else {
                        position++;
                        recycleview.smoothScrollToPosition(position);
                    }
                }
            };
            timer.schedule(timerTask, 4000, 4000);
        }
    }


    public void showProgressingView() {

        // if isProgressShowing = false , means progressbar is not showing .
        if (!isProgressShowing) {
            isProgressShowing = true;

            // inflating progressbar_layout layout which has progressbar to show.
            progressView = (ViewGroup) getLayoutInflater().inflate( R.layout.progressbar_layout, null );

            // "android.R.id.content" gives you the root element of a view of current activity, without having to know its actual name/type/ID but
            //Actually just findViewById(android.R.id.content) is giving the root view.
            // If that is not true in some cases you can get root view from the findViewById(android.R.id.content).getRootView().
            View v = this.findViewById( android.R.id.content ).getRootView();
           // View v = this.findViewById(R.id.view );
            ViewGroup viewGroup = (ViewGroup) v; // converting obtained root View object to viewGroup
            viewGroup.addView( progressView ); // now adding progressbar to the logingactivity ViewGroup which is root view obtained from findViewById(android.R.id.content).getRootView().
        }
    }

    // function used to hide progressbar when login process is completed
    public void hideProgressingView() {
        View v = this.findViewById( android.R.id.content ).getRootView();
        // View v = this.findViewById(R.id.view );
        ViewGroup viewGroup = (ViewGroup) v;
        viewGroup.removeView( progressView ); // removing progressbar from login layout
        isProgressShowing = false;
    }



}