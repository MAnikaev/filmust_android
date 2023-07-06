package com.example.filmust

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.filmust.databinding.FragmentStartBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartFragment : Fragment(R.layout.fragment_start) {
   var binding : FragmentStartBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartBinding.bind(view)
        HttpResponseGetter.getMoviesListByUrl("https://moviesdatabase.p.rapidapi.com/titles/x/upcoming", TypeOfMovieList.Upcoming)

        CoroutineScope(Dispatchers.Main).launch {
            binding?.progressBar?.max = 10000
            val value = 9900
            ObjectAnimator.ofInt(binding?.progressBar, "progress", value).setDuration(2000).start()
            delay(2000L)
            findNavController().navigate(R.id.action_startFragment_to_searchFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}