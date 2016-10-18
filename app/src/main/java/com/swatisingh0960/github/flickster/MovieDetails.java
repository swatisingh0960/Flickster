package com.swatisingh0960.github.flickster;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.swatisingh0960.github.flickster.models.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


/**
 * Created by Swati on 10/16/2016.
 */

public class MovieDetails extends AppCompatActivity {
    @BindView(R.id.ivDetailsPoster) ImageView ivPoster;
    @BindView(R.id.tvDetailsTitle) TextView tvTitle;
    @BindView(R.id.rbRating) RatingBar rbRating;
    @BindView(R.id.tvDetailsReleaseDate) TextView tvReleaseDate;
    @BindView(R.id.tvPopularity) TextView tvPopularity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        Movie movie = (Movie) getIntent().getParcelableExtra("details");

        TextView tvTitle = (TextView) findViewById(R.id.tvDetailsTitle);
        tvTitle.setText(movie.getOriginalTitle());

        RatingBar rbRating = (RatingBar) findViewById(R.id.rbRating);
        rbRating.setNumStars(5);
        rbRating.setRating(movie.getRating()/(float) 2.0);

        TextView tvReleaseDate = (TextView) findViewById(R.id.tvDetailsReleaseDate);
        tvReleaseDate.setText(movie.getReleaseDate());

        TextView tvDetailsDescription = (TextView) findViewById(R.id.tvDetailsDescription);
        tvDetailsDescription.setText(movie.getOverview());

        ImageView ivDetailsPoster = (ImageView) findViewById(R.id.ivDetailsPoster);
        Picasso.with(this).load(movie.getPosterPath())
                .transform(new RoundedCornersTransformation(20, 20))
                .into(ivDetailsPoster);

        TextView tvPopularity = (TextView) findViewById(R.id.tvPopularity);
        tvPopularity.setText(movie.getPopularity());
    }
}
