package com.example.filmust

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.filmust.databinding.ItemMovieBinding

class MovieItem(
    val binding: ItemMovieBinding,
    val glide: RequestManager,
    val onItemClick: (String) -> Unit
) : ViewHolder(binding.root) {
    private val options: RequestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)

    fun onBind(movie: Movie){
        binding.run {
            twTitleName.text = movie.titleText.text

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

            }
        }
    }
}