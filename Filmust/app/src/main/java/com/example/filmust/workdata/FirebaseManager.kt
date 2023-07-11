package com.example.filmust.workdata

import com.example.filmust.fragments.ProfileFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class FirebaseManager {
    companion object {
        private var rootNode: FirebaseDatabase = FirebaseDatabase.getInstance()
        private var reference: DatabaseReference = rootNode.getReference("users")
        var favoriteReference = reference.child(ProfileFragment.login).child("favoriteMovie")
        var viewedReference = reference.child(ProfileFragment.login).child("viewedMovies")
        fun readUserData() {
            favoriteReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val jsonString = snapshot.getValue().toString()
                    val list = deserializeJsonToMoviesList(jsonString)
                    MoviesRepository.favoriteMovies = list.toMutableList()
                }
                override fun onCancelled(error: DatabaseError) {
                    // Обработка ошибки
                }
            })
            if (MoviesRepository.favoriteMovies != null) {
                for (movie in MoviesRepository.favoriteMovies!!) {
                    MoviesRepository.favoriteSet.add(movie.resultID)
                }
            }

            viewedReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val jsonString = snapshot.getValue().toString()
                    val list = deserializeJsonToMoviesList(jsonString)
                    MoviesRepository.viewedMovies = list.toMutableList()
                }
                override fun onCancelled(error: DatabaseError) {
                    // Обработка ошибки
                }
            })
            if (MoviesRepository.viewedMovies != null) {
                for (movie in MoviesRepository.viewedMovies!!) {
                    MoviesRepository.viewedSet.add(movie.resultID)
                }
            }
        }

        fun writeUserData() {
            if(MoviesRepository.favoriteMovies?.isNotEmpty() == true)
            {
                val favoriteMovies : MutableList<HashMap<String, Any?>> = mutableListOf()
                for(movie in MoviesRepository.favoriteMovies!!){
                    favoriteMovies.add(movie.toHashMap())
                }
                favoriteReference.setValue(favoriteMovies)
            }

            if(MoviesRepository.viewedMovies?.isNotEmpty() == true)
            {
                val viewedMovies : MutableList<HashMap<String, Any?>> = mutableListOf()
                for(movie in MoviesRepository.viewedMovies!!){
                    viewedMovies.add(movie.toHashMap())
                }
                viewedReference.setValue(viewedMovies)
            }
        }

        private fun deserializeJsonToMoviesList(jsonString : String) : List<Movie> {
            val json = Json { allowStructuredMapKeys = true }
            return json.decodeFromString(MovieResponse.serializer(), jsonString).results
        }

    }
}