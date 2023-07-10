package com.example.filmust.fragments

import android.R
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.filmust.databinding.FragmentDetailBinding
import com.example.filmust.workdata.HttpResponseGetter
import com.example.filmust.workdata.Movie
import com.example.filmust.workdata.MoviesRepository


class DetailFragment : Fragment(com.example.filmust.R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

    private var isOnFav : Boolean = false

    private var isOnHistory : Boolean = false

    private fun findById(id: String?) : Movie? {

        for (movie in MoviesRepository.searchedMovies) {
            if(movie.id == id)
                return movie
        }

        for (movie in MoviesRepository.upcomingMovies) {
            if(movie.id == id)
                return movie
        }

        for (movie in MoviesRepository.favoriteMovies!!) {
            if(movie.id == id) {
                return movie
            }
        }

        for (movie in MoviesRepository.viewedMovies!!) {
            if(movie.id == id) {
                return movie
            }
        }
        return null
    }

    private fun isOnFavMethod(id: String?) : Boolean {
        for (movie in MoviesRepository.favoriteMovies!!) {
            if (movie.id == id) {
                return true
            }
        }
        return false
    }

    private fun isOnHistMethod(id: String?) : Boolean {
        for (movie in MoviesRepository.viewedMovies!!) {
            if (movie.id == id) {
                return true
            }
        }
        return false
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        super.onCreate(savedInstanceState)

        binding = FragmentDetailBinding.inflate(inflater, container, false)

        var movieId = arguments?.getString("MOVIE_ID")

        val movie = findById(movieId)

        isOnHistory = isOnHistMethod(movieId)
        isOnFav = isOnFavMethod(movieId)


        Glide.with(this).load(movie?.primaryImage?.url).into(binding.imageView)

        binding.tvTitle.text = "${movie?.titleText?.text}"

        binding.tvDesc.text = "The film ${movie?.titleText?.text} planned to be released in " +
                "${movie?.releaseDate?.day}." +
                "${movie?.releaseDate?.month} ${movie?.releaseDate?.year}"

        if(isOnFav){
            binding.likeBtn.setBackgroundColor(Color.parseColor("#E65656"))
        }
        else{
            binding.likeBtn.setBackgroundColor(Color.parseColor("#cfd8dc"))
        }
        if (isOnHistory){
            binding.historyBtn.setBackgroundColor(Color.parseColor("#F1A468"))
        }
        else{
            binding.historyBtn.setBackgroundColor(Color.parseColor("#cfd8dc"))
        }

        binding.run {
            likeBtn.setOnClickListener() {
                if (isOnFav) {
                    likeBtn.setBackgroundColor(Color.parseColor("#cfd8dc"))
                    MoviesRepository.favoriteMovies!!.remove(movie)
                    isOnFav = false
                }
                else{
                    likeBtn.setBackgroundColor(Color.parseColor("#E65656"))
                    MoviesRepository.favoriteMovies!!.add(movie!!)
                    isOnFav = true
                }
            }
        }


        binding.run {
            historyBtn.setOnClickListener() {
                if (isOnHistory) {
                    historyBtn.setBackgroundColor(Color.parseColor("#cfd8dc"))
                    MoviesRepository.viewedMovies!!.remove(movie)
                    isOnHistory = false
                }
                else{
                    historyBtn.setBackgroundColor(Color.parseColor("#F1A468"))
                    MoviesRepository.viewedMovies!!.add(movie!!)
                    isOnHistory = true
                }
            }
        }

        return binding.root
    }
}