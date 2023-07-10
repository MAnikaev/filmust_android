package com.example.filmust.fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.filmust.databinding.FragmentDetailBinding
import com.example.filmust.workdata.HttpResponseGetter
import com.example.filmust.workdata.Movie
import com.example.filmust.workdata.MoviesRepository


class DetailFragment : Fragment(com.example.filmust.R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

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
            if(movie.id == id)
                return movie
        }

        for (movie in MoviesRepository.viewedMovies!!) {
            if(movie.id == id)
                return movie
        }
        return null
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

        Glide.with(this).load(movie?.primaryImage?.url).into(binding.imageView)

        binding.tvTitle.text = "${movie?.titleText?.text}"

        binding.tvDesc.text = "The film ${movie?.titleText?.text} was released in ${movie?.releaseDate?.day}." +
                "${movie?.releaseDate?.month} ${movie?.releaseDate?.year}"

        binding.run {
            historyBtn.setOnClickListener() {
                //Сначала идет проверка на наличие в списке и затем смена на нужный цвет
                //TODO: if (id нет в списке)
                //{
                historyBtn.setBackgroundColor(getResources().getColor(R.color.white))
                //TODO: добавление в список
                //}
                //TODO: else(id есть в списке)
                //{
                historyBtn.setBackgroundColor(getResources().getColor(R.color.black))
                //TODO: удаление из списка
                //}
            }
        }


        binding.run {
            likeBtn.setOnClickListener() {

                //Сначала идет проверка на наличие в списке и затем смена на нужный цвет
                //TODO: if (id нет в списке)
                //{
                likeBtn.setBackgroundColor(getResources().getColor(R.color.white))
                //TODO: добавление в список
                //}
                //TODO: else(id есть в списке)
                //{
                likeBtn.setBackgroundColor(getResources().getColor(R.color.black))
                //TODO: удаление из списка
                //}
            }
        }

        return binding.root
    }
}