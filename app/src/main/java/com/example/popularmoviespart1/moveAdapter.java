package com.example.popularmoviespart1;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class moveAdapter extends RecyclerView.Adapter<moveAdapter.moveHolder> {
    @NonNull
    private List<Movie> movieList;
    private Context context;
    private moveToInfo moveToInfo;
    public moveAdapter(@NonNull Context context, List<Movie> movieList,moveToInfo moveToInfo) {
        this.context=context;
        this.movieList = movieList;
        this.moveToInfo=moveToInfo;
    }
    public interface moveToInfo{
        public void moving(Movie movie,int Position);
    }
    @Override
    public moveAdapter.moveHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_move,viewGroup,false);
        return new moveHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull moveAdapter.moveHolder moveHolder, int i) {
        Picasso.with(context.getApplicationContext())
                .load("http://image.tmdb.org/t/p/original"+movieList.get(i).getPoster_path())
                .fit()
                .into(moveHolder.imageViews);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class moveHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
         private ImageView imageViews;
        public moveHolder(@NonNull View itemView) {
            super(itemView);
            imageViews=(ImageView)itemView.findViewById(R.id.poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int Position=getAdapterPosition();
            moveToInfo.moving(movieList.get(Position),Position);
        }
    }
}
