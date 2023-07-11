package com.example.filmust.workdata

import androidx.appcompat.app.AppCompatActivity
import com.example.filmust.fragments.ProfileFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener
import kotlinx.serialization.json.JsonElement


class FirebaseManager {
    companion object {
        private var rootNode: FirebaseDatabase = FirebaseDatabase.getInstance()
        private var reference: DatabaseReference = rootNode.getReference("users")
        var favoriteReference = reference.child(ProfileFragment.login).child("favoriteMovie")
        var viewedReference = reference.child(ProfileFragment.login).child("viewedMovies")
        fun readUserData() {
            favoriteReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list =
                        snapshot.getValue(object : GenericTypeIndicator<MutableList<HashMap<String, Any?>>>() {})
                    if (list != null) {
                        for (movie in list){
                            MoviesRepository.favoriteMovies?.add(fromHashMap(movie))
                        }
                    }
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
                    val list =
                        snapshot.getValue(object : GenericTypeIndicator<MutableList<HashMap<String, Any?>>>() {})
                    if (list != null) {
                        for (movie in list){
                            MoviesRepository.viewedMovies?.add(fromHashMap(movie))
                        }
                    }
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

        private fun fromHashMap(hashMap: HashMap<String, Any?>): Movie {
            val id = hashMap["id"] as String
            val resultID = hashMap["resultID"] as String
            val titleType = hashMap["titleType"] as TitleType
            val titleText = hashMap["titleText"] as TitleText
            val originalTitleText = hashMap["originalTitleText"] as TitleText
            val releaseYear = hashMap["releaseYear"] as ReleaseYear
            val releaseDate = hashMap["releaseDate"] as ReleaseDate
            val ratingsSummary = hashMap["ratingsSummary"] as ResultRatingsSummary?
            val episodes = hashMap["episodes"] as ResultEpisodes?
            val primaryImage = hashMap["primaryImage"] as PrimaryImage?
            val genres = hashMap["genres"] as Genres?
            val runtime = hashMap["runtime"] as Runtime?
            val series = hashMap["series"] as JsonElement?
            val meterRanking = hashMap["meterRanking"] as MeterRanking?
            val plot = hashMap["plot"] as Plot?

            return Movie(
                id = id,
                resultID = resultID,
                titleType = titleType,
                titleText = titleText,
                originalTitleText = originalTitleText,
                releaseYear = releaseYear,
                releaseDate = releaseDate,
                ratingsSummary = ratingsSummary,
                episodes = episodes,
                primaryImage = primaryImage,
                genres = genres,
                runtime = runtime,
                series = series,
                meterRanking = meterRanking,
                plot = plot
            )
        }
    }
}