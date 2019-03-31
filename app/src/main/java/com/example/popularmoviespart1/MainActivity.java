package com.example.popularmoviespart1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.service.notification.ConditionProviderService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements moveAdapter.moveToInfo{
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private moveAdapter moveAdapter;
    private List<Movie> movieLisst=new ArrayList<>();
    private int menuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        recyclerView=(RecyclerView)findViewById(R.id.rv);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        new moveSync().execute(1);
    }

    @Override
    public void moving(Movie movie, int Position) {
        Intent intent=new Intent(MainActivity.this,moveInfo.class);
        intent.putExtra("vote_count",movie.getVote_count());
        intent.putExtra("id",movie.getId());
        intent.putExtra("vote_average",movie.getVote_average());
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("popularity",movie.getPopularity());
        intent.putExtra("original_language",movie.getOriginal_language());
        intent.putExtra("original_title",movie.getOriginal_title());
        intent.putExtra("backdrop_path",movie.getBackdrop_path());
        intent.putExtra("overview",movie.getOverview());
        intent.putExtra("release_date",movie.getRelease_date());
        ArrayList<Integer> g=movie.getGenre_ids();
        intent.putIntegerArrayListExtra("genre_ids",g);
        intent.putExtra("adult",movie.isAdult());
        startActivity(intent);
    }

    public class moveSync extends AsyncTask<Integer,Void, List<Movie>>{
        @Override
        protected List<Movie> doInBackground(Integer... integers) {
            progressBar.setVisibility(View.VISIBLE);
            int type = integers[0];
            List<Movie> movieList1 = new ArrayList<>();
            try {
                movieList1 = JSONProcessor.jsonParsePopularMove(type);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return movieList1;
        }


        @Override
        protected void onPreExecute() {
            movieLisst.clear();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
            progressBar.setVisibility(View.VISIBLE);
            for(int i=0;i<movies.size();i++) {
            movieLisst.add(movies.get(i));
            }
            moveAdapter=new moveAdapter(getApplicationContext(),movieLisst,MainActivity.this);
            recyclerView.setAdapter(moveAdapter);
            progressBar.setVisibility(View.INVISIBLE);
        }    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.sorting,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selected=item.getItemId();
        switch (selected){
            case R.id.pop:
                new moveSync().execute(1);
                break;
            case R.id.rated:
                new moveSync().execute(2);
                break;
        }
        return true;
    }
}
