package com.mtsteta.homework.presentation.recyclerviews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mtsteta.homework.R
import com.mtsteta.homework.data.dto.MovieDto

class MoviesRecyclerAdapter(
    private val movies: List<MovieDto>,
    private val listener: (title: String) -> Unit
) :
    RecyclerView.Adapter<MoviesRecyclerAdapter.MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false)
        return MoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.movieCoverImageView.load(movies[position].imageUrl)
        holder.movieTitleTextView.text = movies[position].title
        holder.movieDescriptionTextView.text = movies[position].description
        holder.star1ImageView.setImageResource(if (movies[position].rateScore > 0) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        holder.star2ImageView.setImageResource(if (movies[position].rateScore > 1) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        holder.star3ImageView.setImageResource(if (movies[position].rateScore > 2) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        holder.star4ImageView.setImageResource(if (movies[position].rateScore > 3) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        holder.star5ImageView.setImageResource(if (movies[position].rateScore > 4) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        holder.movieAgeRatingTextView.text = movies[position].ageRestriction.toString()
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var movieCoverImageView: ImageView
        var movieTitleTextView: TextView
        var movieDescriptionTextView: TextView
        var star1ImageView: ImageView
        var star2ImageView: ImageView
        var star3ImageView: ImageView
        var star4ImageView: ImageView
        var star5ImageView: ImageView
        var movieAgeRatingTextView: TextView

        init {
            itemView.setOnClickListener(this)
            movieCoverImageView = itemView.findViewById(R.id.itemMovieCoverImageView)
            movieTitleTextView = itemView.findViewById(R.id.itemMovieTitleTextView)
            movieDescriptionTextView = itemView.findViewById(R.id.itemMovieDescriptionTextView)
            star1ImageView = itemView.findViewById(R.id.itemMovieStar1ImageView)
            star2ImageView = itemView.findViewById(R.id.itemMovieStar2ImageView)
            star3ImageView = itemView.findViewById(R.id.itemMovieStar3ImageView)
            star4ImageView = itemView.findViewById(R.id.itemMovieStar4ImageView)
            star5ImageView = itemView.findViewById(R.id.itemMovieStar5ImageView)
            movieAgeRatingTextView = itemView.findViewById(R.id.itemMovieAgeRestrictionTextView)
        }

        override fun onClick(v: View?) {
            listener(movies[adapterPosition].title)
        }
    }
}