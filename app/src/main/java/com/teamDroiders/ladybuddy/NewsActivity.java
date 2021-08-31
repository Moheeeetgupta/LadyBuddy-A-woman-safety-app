package com.teamDroiders.ladybuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {
    RecyclerView  recycleview;
    LinearLayoutManager layoutManager;


    ViewGroup progressView;
    protected boolean isProgressShowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_news );

        getSuperHeroes();
    }


    private void getSuperHeroes() {
        showProgressingView();

        RetrofitClient.getApi().getsuperHeroes( "4662f45afce949fea09fbbbe7183eca6",
                100,
                "women AND india"

        ).enqueue( new Callback<NewsResults>() {
            @Override
            public void onResponse(Call<NewsResults> call, Response<NewsResults> response) {
                if (response.isSuccessful()) {
                    ArrayList<News> myitemsList = (ArrayList<News>) response.body().getArticles();


                    recycleview = findViewById( R.id.reclcle_list );

                    layoutManager = new LinearLayoutManager( NewsActivity.this, LinearLayoutManager.VERTICAL, false );
                    recycleview.setLayoutManager( layoutManager );

                    NewsAdapter bannerAdapter = new NewsAdapter( NewsActivity.this, myitemsList );
                    recycleview.setAdapter( bannerAdapter );

                    hideProgressingView();

                    //   hideProgressingView();

                }


            }

            @Override
            public void onFailure(Call<NewsResults> call, Throwable t) {
                Toast.makeText( NewsActivity.this, "Unsuccessful ", Toast.LENGTH_SHORT ).show();
            }
        });
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