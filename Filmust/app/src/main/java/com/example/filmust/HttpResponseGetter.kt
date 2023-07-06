package com.example.filmust



import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

object HttpResponseGetter {

    fun getMoviesListByUrl(url: String, typeOfMovieList: TypeOfMovieList) {

            val client = OkHttpClient()

            val request = Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Key", "b173d8d0ccmsh62defdcbb5aed73p1efb70jsn183b90ae351a")
                .addHeader("X-RapidAPI-Host", "moviesdatabase.p.rapidapi.com")
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    TODO("Not yet implemented")
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (!response.isSuccessful)
                            throw Exception()
                        when(typeOfMovieList){
                            TypeOfMovieList.Upcoming -> MoviesRepository.upcomingMovies = deserializeJsonToMoviesList(response.body!!.string())
                            TypeOfMovieList.Viewed -> MoviesRepository.viewedMovies = deserializeJsonToMoviesList(response.body!!.string())
                            TypeOfMovieList.Favourite -> MoviesRepository.favouriteMovies = deserializeJsonToMoviesList(response.body!!.string())
                            TypeOfMovieList.Searched -> MoviesRepository.searchedMovies = deserializeJsonToMoviesList((response.body!!.string()))
                        }
                    }
                }
            })
    }

    private fun deserializeJsonToMoviesList(jsonString : String) : List<Movie> {
        val json = Json { allowStructuredMapKeys = true }
        return json.decodeFromString(MovieResponse.serializer(), jsonString).results
    }
}