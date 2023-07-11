package com.example.filmust.workdata

object MoviesRepository {
    var upcomingMovies : List<LightMovie> = mutableListOf()
    var searchedMovies : List<LightMovie> = mutableListOf()
    var favoriteMovies : MutableList<LightMovie>? = mutableListOf()
    var viewedMovies : MutableList<LightMovie>? = mutableListOf()
    val favoriteSet : HashSet<String> = hashSetOf()
    val viewedSet : HashSet<String> = hashSetOf()
}