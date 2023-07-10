package com.example.filmust.workdata

object MoviesRepository {
    var upcomingMovies : List<Movie> = mutableListOf()
    var searchedMovies : List<Movie> = mutableListOf()
    var favoriteMovies : MutableList<Movie>? = mutableListOf()
    var viewedMovies : MutableList<Movie>? = mutableListOf()
}