package com.example.filmust.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.filmust.workdata.Movie
import com.example.filmust.workdata.MovieAdapter
import com.example.filmust.R
import com.example.filmust.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    private var binding: FragmentFavouritesBinding? = null
    private var adapter : MovieAdapter? = null

    private lateinit var likeButton: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavouritesBinding.bind(view)




        //initAdapter()




        binding!!.rvFavourites.findViewHolderForItemId(R.id.btn_favourite.toLong())
    }

    fun likeMovie(){

    }

    fun markWatchedMovie(){

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
        binding?.rvFavourites?.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    }