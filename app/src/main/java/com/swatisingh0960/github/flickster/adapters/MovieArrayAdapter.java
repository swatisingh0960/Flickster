package com.swatisingh0960.github.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.swatisingh0960.github.flickster.R;
import com.swatisingh0960.github.flickster.models.Movie;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

import static com.swatisingh0960.github.flickster.R.id.tvOverview;
import static com.swatisingh0960.github.flickster.R.id.tvTitle;

/**
 * Created by Swati on 10/15/2016.
 */

//One Parameter - Context, the other would be List of Movies
public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    public MovieArrayAdapter(Context context, List<Movie> movies){
        super(context,android.R.layout.simple_list_item_1,movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the data item for the position
        Movie movie = getItem(position);
        MovieArrayAdapterViewHolder viewHolder;
        //check if the existing view is being reused

           if(convertView == null){
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new MovieArrayAdapterViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext()); //getContext is a method of the Array Adapter
            convertView = inflater.inflate(R.layout.item_movie,parent,false);
             viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
             viewHolder.tvTitle = (TextView) convertView.findViewById(tvTitle);
             viewHolder.tvOverview = (TextView) convertView.findViewById(tvOverview);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        }
 else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (MovieArrayAdapterViewHolder) convertView.getTag();
        }
        //find the Image View
//        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);

        //clear out the Image from convert view
        viewHolder.ivImage.setImageResource(0);
        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());
/*//        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
//        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

        //populate the data
//        tvTitle.setText(movie.getOriginalTitle());
//        tvOverview.setText(movie.getOverview());*/

        Log.d("poster %s", movie.getPosterPath());

        int orientation = getContext().getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Picasso.with(getContext()).load(movie.getBackdropPath())
                    .transform(new RoundedCornersTransformation(20, 20))
                    .into(viewHolder.ivImage);
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(movie.getPosterPath())
                    .transform(new RoundedCornersTransformation(20, 20))
                    .into(viewHolder.ivImage);
        }

//        Picasso.with(getContext()).load(movie.getPosterPath()).into(ivImage);
        //return the view
        return convertView;
//        return super.getView(position, convertView, parent);
    }
}
