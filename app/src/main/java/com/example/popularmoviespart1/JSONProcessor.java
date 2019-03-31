package com.example.popularmoviespart1;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JSONProcessor{
    final static String popularID="https://api.themoviedb.org/3/movie/popular?api_key=[Your_key]";
    final static String topRated="https://api.themoviedb.org/3/movie/top_rated?api_key=[Your_key]";
    public static List<Movie> jsonParsePopularMove(int sortType){
        Movie movie;
        ArrayList<Integer> ids=new ArrayList<Integer>();
        List<Movie> fetech=new ArrayList<Movie>();
        try {
            URL popID=new URL(popularID);
            URL topRate=new URL(topRated);
            String Result=null;
            if(sortType==1){
                Result=NetworkUtill.getResponseFromHttpUrl(popID);
            }else if(sortType==2){
                Result=NetworkUtill.getResponseFromHttpUrl(topRate);
            }
            JSONObject MoveList=new JSONObject(Result);
            JSONArray results=MoveList.getJSONArray("results");
            for(int i1=0;i1<results.length();i1++){
                JSONObject resultArr=results.getJSONObject(i1);
                long vote_count=resultArr.getInt("vote_count");
                int id=resultArr.getInt("id");
                boolean video=resultArr.getBoolean("video");
                Number vote_average=resultArr.getDouble("vote_average");
                String title=resultArr.getString("title");
                Number popularity=resultArr.getDouble("popularity");
                String poster_path=resultArr.getString("poster_path");
                String original_language=resultArr.getString("original_language");
                String original_title=resultArr.getString("original_title");
                JSONArray genre_ids=resultArr.getJSONArray("genre_ids");
                for(int i2=0;i2<genre_ids.length();i2++){
                    ids.add(genre_ids.getInt(i2));
                }
                String backdrop_path=resultArr.getString("backdrop_path");
                boolean adult=resultArr.getBoolean("adult");
                String overview=resultArr.getString("overview");
                String release_date=resultArr.getString("release_date");
                movie=new Movie(vote_count,id,video,vote_average,title,popularity,poster_path,original_language,original_title
                        ,ids,backdrop_path,adult,overview,release_date);
                fetech.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fetech;

    }



}
