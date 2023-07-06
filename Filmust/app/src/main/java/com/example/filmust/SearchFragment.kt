package com.example.filmust

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.filmust.databinding.FragmentSearchBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

import kotlin.Exception


class SearchFragment : Fragment(R.layout.fragment_search) {

    private var binding: FragmentSearchBinding? = null
    private var adapter : MovieAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        initAdapter(MoviesRepository.upcomingMovies)

        binding?.btnSearch?.setOnClickListener{
            val movieName = binding?.etSearch?.text.toString()
            if(movieName.isNotEmpty()){
                HttpResponseGetter.getMoviesListByUrl("https://moviesdatabase.p.rapidapi.com/titles/search/title/$movieName?exact=true&info=base_info&titleType=movie", TypeOfMovieList.Searched)
                Handler().postDelayed({
                    adapter?.updateList(MoviesRepository.searchedMovies)
                    binding?.twSearchTitle?.text = "Here's what I could find"
                }, 500)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initAdapter(listOfMovies : List<Movie>) {
        adapter = MovieAdapter(
            listOfMovies = listOfMovies,
            glide = Glide.with(this),
            onItemClick = {
                val bundle = Bundle()
                bundle.putString("MOVIE_ID", it)
                //TODO("Здесь будет переход на фрагмент конкретного фильма")
            })
        binding?.rwSearchResults?.adapter = adapter
    }

}