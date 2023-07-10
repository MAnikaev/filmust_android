package com.example.filmust.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.filmust.R
import com.example.filmust.databinding.FragmentDetailBinding
import com.example.filmust.workdata.Movie


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var binding: FragmentDetailBinding? = null

    var imageView: ImageView? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBinding.bind(view)

        imageView = getView()?.findViewById(R.id.imageView)

        var movie = arguments?.get("ARG_ID")

        if (movie != null){

            movie = movie as Movie

            Glide.with(this).load(movie.primaryImage?.url).into(binding!!.imageView)

            binding?.run {
                tvTitle?.text = movie.titleText.text

                tvDesc?.text = "The film ${movie.titleText.text}, shot in the genres: ${movie.genres!!.genres[0]}" +
                        "and ${movie.genres!!.genres[1]}" +
                        "and lasting ${movie.runtime!!.seconds} seconds, was released in ${movie.releaseDate.day}" +
                        "${movie.releaseDate.month} ${movie.releaseDate.year}. At the moment, " +
                        "his rating is ${movie.meterRanking!!.currentRank}. Description: ${movie.plot?.plotText}"
            }
        }

        binding?.run {
            historyBtn.setOnClickListener(){
                //Сначала идет проверка на наличие в списке и затем смена на нужный цвет
                //TODO: if (id нет в списке)
                //{
                historyBtn.setBackgroundColor(getResources().getColor(R.color.historyButtonColor_on))
                //TODO: добавление в список
                //}
                //TODO: else(id есть в списке)
                //{
                historyBtn.setBackgroundColor(getResources().getColor(R.color.buttonColor_off))
                //TODO: удаление из списка
                //}
            }
        }


        binding?.run {
            likeBtn.setOnClickListener(){

                //Сначала идет проверка на наличие в списке и затем смена на нужный цвет
                //TODO: if (id нет в списке)
                //{
                likeBtn.setBackgroundColor(getResources().getColor(R.color.favButtonColor_on))
                //TODO: добавление в список
                //}
                //TODO: else(id есть в списке)
                //{
                likeBtn.setBackgroundColor(getResources().getColor(R.color.buttonColor_off))
                //TODO: удаление из списка
                //}
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
