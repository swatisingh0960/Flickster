package com.swatisingh0960.github.flickster.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Swati on 10/15/2016.
 */

public class Movie implements Parcelable{
    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s",posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }
    public float getRating() { return rating; }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getPopularity() {
        return popularity;
    }

    String posterPath;
    String originalTitle;
    String overview;
    String releaseDate;
    float rating;
    String backdropPath;
    String popularity;

    // This is where we write the values we want to save to the `Parcel`.
    // The `Parcel` class has methods defined to help save all the values.
    // Note that there are only methods defined for simple values, lists, and other Parcelable objects.
    // You may need to make several classes Parcelable to send the data you want.
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterPath);
        dest.writeString(originalTitle);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeFloat(rating);
        dest.writeString(backdropPath);
        dest.writeString(popularity);
    }
    // Using the `in` variable, we can retrieve the values that
    // we originally wrote into the `Parcel`.  This constructor is usually
    // private so that only the `CREATOR` field can access.
        private Movie(Parcel in){
            posterPath=in.readString();
            originalTitle=in.readString();
            overview=in.readString();
            releaseDate=in.readString();
            rating=in.readFloat();
            backdropPath=in.readString();
            popularity=in.readString();
        }

    //Constructor that takes JSON Object, and track down the field for each property
    public Movie(JSONObject jsonObject) throws JSONException{
        this.posterPath=jsonObject.getString("poster_path");
        this.originalTitle=jsonObject.getString("original_title");
        this.overview=jsonObject.getString("overview");
        this.rating=jsonObject.getInt("vote_average");
        this.releaseDate = jsonObject.getString("release_date");
        this.backdropPath=jsonObject.getString("backdrop_path");
        this.popularity=jsonObject.getString("popularity");
    }


    @Override
    public int describeContents() {
        return 0;
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array){
        ArrayList<Movie> results = new ArrayList<>();
        //iterate each element in that array and calling other constructor
        for (int x=0;x<array.length();x++){
            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
