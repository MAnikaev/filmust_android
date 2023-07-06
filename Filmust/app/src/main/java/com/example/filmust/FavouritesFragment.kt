package com.example.filmust

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.filmust.databinding.ActivityMainBinding
import com.example.filmust.databinding.FragmentFavouritesBinding

class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    private var binding: FragmentFavouritesBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavouritesBinding.bind(view)

        //TODO: Recycler View
        }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    }