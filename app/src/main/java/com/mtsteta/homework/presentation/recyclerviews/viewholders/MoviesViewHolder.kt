package com.mtsteta.homework.presentation.recyclerviews.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mtsteta.homework.R
import com.mtsteta.homework.data.dto.MovieDto

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val movieCoverImageView: ImageView =
        itemView.findViewById(R.id.itemMovieCoverImageView)
    private val movieTitleTextView: TextView =
        itemView.findViewById(R.id.itemMovieTitleTextView)
    private val movieDescriptionTextView: TextView =
        itemView.findViewById(R.id.itemMovieDescriptionTextView)
    private val star1ImageView: ImageView = itemView.findViewById(R.id.itemMovieStar1ImageView)
    private val star2ImageView: ImageView = itemView.findViewById(R.id.itemMovieStar2ImageView)
    private val star3ImageView: ImageView = itemView.findViewById(R.id.itemMovieStar3ImageView)
    private val star4ImageView: ImageView = itemView.findViewById(R.id.itemMovieStar4ImageView)
    private val star5ImageView: ImageView = itemView.findViewById(R.id.itemMovieStar5ImageView)
    private val movieAgeRatingTextView: TextView =
        itemView.findViewById(R.id.itemMovieAgeRestrictionTextView)

    fun bind(movie: MovieDto) {
        movieCoverImageView.load(movie.imageUrl)
        movieTitleTextView.text = movie.title
        movieDescriptionTextView.text = movie.description
        star1ImageView.setImageResource(if (movie.rateScore > 0) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        star2ImageView.setImageResource(if (movie.rateScore > 1) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        star3ImageView.setImageResource(if (movie.rateScore > 2) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        star4ImageView.setImageResource(if (movie.rateScore > 3) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        star5ImageView.setImageResource(if (movie.rateScore > 4) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        movieAgeRatingTextView.text = itemView.context.getString(
            R.string.age_restriction,
            movie.ageRestriction.toString()
        )
    }
}