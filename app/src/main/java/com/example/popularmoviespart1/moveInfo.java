package com.example.popularmoviespart1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class moveInfo extends AppCompatActivity {
    private TextView id;
    private TextView vote_count;
    private TextView vote_average;
    private TextView title;
    private TextView popularity;
    private TextView original_language;
    private TextView original_title;
    private ImageView backdrop_path;
    private TextView overview;
    private TextView release_date;
    private TextView genre_ids;
    private TextView adult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_info);
        id=(TextView)findViewById(R.id.moveID);
        vote_count=(TextView)findViewById(R.id.voite_count);
        vote_average=(TextView)findViewById(R.id.voteAV);
        title=(TextView)findViewById(R.id.tv_title);
        popularity=(TextView)findViewById(R.id.populairty);
        original_language=(TextView)findViewById(R.id.ol);
        original_title=(TextView)findViewById(R.id.otitle);
        backdrop_path=(ImageView)findViewById(R.id.imageView);
        genre_ids=(TextView)findViewById(R.id.genre_ids);
        adult=(TextView)findViewById(R.id.adult);
        overview=(TextView)findViewById(R.id.overview);
        release_date=(TextView)findViewById(R.id.release_date);
        Intent intent=getIntent();
        if(intent!=null){
            if(intent.hasExtra("id")){
            id.setText(intent.getExtras().get("id").toString());}
            if(intent.hasExtra("vote_count")){
                vote_count.setText(intent.getExtras().get("vote_count").toString());}
            if(intent.hasExtra("vote_average")) {
            vote_average.setText(intent.getExtras().get("vote_average").toString());}
            if(intent.hasExtra("title")) {
            title.setText(intent.getExtras().get("title").toString());}
            if(intent.hasExtra("popularity")) {
            popularity.setText(intent.getExtras().get("popularity").toString());}
            if(intent.hasExtra("original_language")) {
            original_language.setText(intent.getExtras().get("original_language").toString());}
            if(intent.hasExtra("original_title")) {
            original_title.setText(intent.getExtras().get("original_title").toString());}
            if(intent.hasExtra("backdrop_path")) {
            String url=intent.getExtras().getString("backdrop_path").toString();
            Picasso.with(moveInfo.this)
                    .load("http://image.tmdb.org/t/p/original"+url)
                    .into(backdrop_path);}
            if(intent.hasExtra("overview")) {
            overview.setText(intent.getExtras().get("overview").toString());}
            if(intent.hasExtra("release_date")) {
            release_date.setText(intent.getExtras().get("release_date").toString());}
            if(intent.hasExtra("genre_ids")) {
                ArrayList<Integer> generc= (ArrayList<Integer>) intent.getExtras().get("genre_ids");
                for(int i=0;i<generc.size();i++) {
                    genre_ids.append(generc.get(i).toString()+",");
                }}
           if(intent.hasExtra("adult")) {
               adult.setText(intent.getExtras().get("adult").toString());}
           }

    }
}
