package com.teamDroiders.ladybuddy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    List<News> news_list;
    Context context;
    public NewsAdapter(Context ct, ArrayList<News> news_list){
        this.news_list = news_list;
        context = ct;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_news_layout, parent, false);
        return  new MyViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Uri imgUri = Uri.parse(news_list.get( holder.getAdapterPosition() ).getUrlToImage());
        Glide.with(context).load(imgUri).into( holder.img );
        //Url imgUrl = new Url(news_list.get( holder.getAdapterPosition() ).getUrlToImage());
        holder.title.setText( news_list.get( holder.getAdapterPosition() ).getTitle() );
        holder.description.setText( news_list.get( holder.getAdapterPosition() ).getDescription() );


        holder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                News currentNews = news_list.get( holder.getAdapterPosition());

                // Convert the string URL into a URI object (to pass into the Intent constructor)
                Uri bookInfoUri = Uri.parse( currentNews.getUrl() );

                // Create a new intent to launch a new activity
                Intent websiteIntent = new Intent( Intent.ACTION_VIEW, bookInfoUri );

                // Send the intent to launch a new activity
                context.startActivity( websiteIntent );



            }
        } );



    }

    @Override
    public int getItemCount() {
        return news_list.size();
    }

    // LayoutInflater inflater = LayoutInflater.from(context);
    // inflater.inflate();





    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView title;
        TextView description;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            img = itemView.findViewById( R.id.img );
            title = itemView.findViewById( R.id.titlee );
            description = itemView.findViewById( R.id.description );
        }
    }
}
