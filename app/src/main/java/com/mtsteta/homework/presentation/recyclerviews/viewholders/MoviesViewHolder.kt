package com.mtsteta.homework.presentation.recyclerviews.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mtsteta.homework.R
import com.mtsteta.homework.data.dto.MovieDto

class MoviesViewHolder(itemView: View, onMovieItemClick: (title: String) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    private val movieCoverImageView: ImageView = itemView.findViewById(R.id.ivItemMovieCover)
    private val movieTitleTextView: TextView = itemView.findViewById(R.id.textItemMovieTitle)
    private val movieDescriptionTextView: TextView =
        itemView.findViewById(R.id.textItemMovieDescription)
    private val star1ImageView: ImageView = itemView.findViewById(R.id.ivItemMovieStar1)
    private val star2ImageView: ImageView = itemView.findViewById(R.id.ivItemMovieStar2)
    private val star3ImageView: ImageView = itemView.findViewById(R.id.ivItemMovieStar3)
    private val star4ImageView: ImageView = itemView.findViewById(R.id.ivItemMovieStar4)
    private val star5ImageView: ImageView = itemView.findViewById(R.id.ivItemMovieStar5)
    private val movieAgeRatingTextView: TextView =
        itemView.findViewById(R.id.textItemMovieAgeRestriction)
    private lateinit var item: String

    init {
        itemView.setOnClickListener { onMovieItemClick.invoke(item) }
    }

    fun bind(movie: MovieDto) {
        item = movie.title
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