package com.example.filmust.workdata

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.filmust.R
import com.example.filmust.databinding.ItemMovieBinding
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.filmust.fragments.ProfileFragment


class MovieItem(
    val binding: ItemMovieBinding,
    val glide: RequestManager,
    val onItemClick: (String) -> Unit
) : ViewHolder(binding.root) {
    private val options: RequestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)

    fun onBind(movie: Movie){
        binding.run {
            twTitleName.text = movie.titleText.text
            if(MoviesRepository.favoriteSet.contains(movie.resultID)){
                btnFavourite.setImageResource(R.drawable.red_heart)
            }
            if(MoviesRepository.viewedSet.contains(movie.resultID)){
                btnViewed.setImageResource(R.drawable.red_eye)
            }
            glide
                .load(movie.primaryImage?.url)
                .placeholder(R.drawable.filmust_not_found)
                .error(R.drawable.filmust_not_found)
                .apply(options)
                .into(movieImage)

            root.setOnClickListener{
                onItemClick(movie.id)
            }
            btnFavourite.setOnClickListener{
                if(MoviesRepository.favoriteSet.contains(movie.resultID)){
                    MoviesRepository.favoriteSet.remove(movie.resultID)
                    btnFavourite.setImageResource(R.drawable.baseline_favorite_24)
                    MoviesRepository.favoriteMovies?.remove(movie)
                } else{
                    MoviesRepository.favoriteSet.add(movie.resultID)
                    btnFavourite.setImageResource(R.drawable.red_heart)
                    MoviesRepository.favoriteMovies?.add(movie)
                }
            }
            btnViewed.setOnClickListener {
                if(MoviesRepository.viewedSet.contains(movie.resultID)){
                    MoviesRepository.viewedSet.remove(movie.resultID)
                    btnViewed.setImageResource(R.drawable.baseline_remove_red_eye_24)
                    MoviesRepository.viewedMovies?.remove(movie)
                } else{
                    MoviesRepository.viewedSet.add(movie.resultID)
                    btnViewed.setImageResource(R.drawable.red_eye)
                    MoviesRepository.viewedMovies?.add(movie)
                }
            }
        }
    }
}