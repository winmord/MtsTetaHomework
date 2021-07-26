package com.mtsteta.homework.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import coil.load
import com.mtsteta.homework.R
import com.mtsteta.homework.data.dto.MovieDto

class MovieDetailsFragment : Fragment() {
    private var movieDto: MovieDto? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_details, container, false)

        movieDto = arguments?.getParcelable(movie_dto_bundle_key)
        setupViews(view, movieDto)

        return view
    }

    private fun setupViews(view: View, movieDto: MovieDto?) {
        view.findViewById<TextView>(R.id.textMovieTitle).text = movieDto?.title
        view.findViewById<TextView>(R.id.textMovieDescription).text = movieDto?.description
        view.findViewById<TextView>(R.id.textMovieAgeRestriction).text =
            getString(R.string.age_restriction, movieDto?.ageRestriction.toString())
        view.findViewById<ImageView>(R.id.ivMovieCover).load(movieDto?.imageUrl)
        val rateScore = movieDto?.rateScore!!
        view.findViewById<ImageView>(R.id.ivMovieStar1)
            .setImageResource(if (rateScore > 0) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        view.findViewById<ImageView>(R.id.ivMovieStar2)
            .setImageResource(if (rateScore > 1) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        view.findViewById<ImageView>(R.id.ivMovieStar3)
            .setImageResource(if (rateScore > 2) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        view.findViewById<ImageView>(R.id.ivMovieStar4)
            .setImageResource(if (rateScore > 3) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
        view.findViewById<ImageView>(R.id.ivMovieStar5)
            .setImageResource(if (rateScore > 4) R.drawable.ic_star_black else R.drawable.ic_star_transparent)
    }
}