package com.mtsteta.homework.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import coil.load
import com.mtsteta.homework.R
import com.mtsteta.homework.model.dto.MovieDto

class MovieDetailsFragment : Fragment() {
    private var movieDto: MovieDto? = null
    private lateinit var movieTitleTextView: TextView
    private lateinit var movieDescriptionTextView: TextView
    private lateinit var movieAgeRestrictionTextView: TextView
    private lateinit var movieCoverImageView: ImageView
    private lateinit var movieStar1ImageView: ImageView
    private lateinit var movieStar2ImageView: ImageView
    private lateinit var movieStar3ImageView: ImageView
    private lateinit var movieStar4ImageView: ImageView
    private lateinit var movieStar5ImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie_details, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData(view)
    }

    private fun loadData(view: View) {
        initViews(view)

        movieDto = arguments?.getParcelable(MOVIE_DTO_BUNDLE_KEY)
        setupViews(movieDto)
    }

    private fun initViews(view: View) {
        movieTitleTextView = view.findViewById(R.id.textMovieTitle)
        movieDescriptionTextView = view.findViewById(R.id.textMovieDescription)
        movieAgeRestrictionTextView = view.findViewById(R.id.textMovieAgeRestriction)
        movieCoverImageView = view.findViewById(R.id.ivMovieCover)
        movieStar1ImageView = view.findViewById(R.id.ivMovieStar1)
        movieStar2ImageView = view.findViewById(R.id.ivMovieStar2)
        movieStar3ImageView = view.findViewById(R.id.ivMovieStar3)
        movieStar4ImageView = view.findViewById(R.id.ivMovieStar4)
        movieStar5ImageView = view.findViewById(R.id.ivMovieStar5)
    }

    private fun setupViews(movieDto: MovieDto?) {
        val rateScore = movieDto?.rateScore!!
        movieTitleTextView.text = movieDto.title
        movieDescriptionTextView.text = movieDto.description
        movieAgeRestrictionTextView.text = getString(R.string.age_restriction, movieDto.ageRestriction.toString())
        movieCoverImageView.load(movieDto.imageUrl)
        movieStar1ImageView.setImageResource(if (rateScore > 0) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        movieStar2ImageView.setImageResource(if (rateScore > 1) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        movieStar3ImageView.setImageResource(if (rateScore > 2) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        movieStar4ImageView.setImageResource(if (rateScore > 3) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        movieStar5ImageView.setImageResource(if (rateScore > 4) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
    }
}