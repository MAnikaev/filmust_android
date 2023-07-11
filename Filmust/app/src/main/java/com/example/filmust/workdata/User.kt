package com.example.filmust.workdata

class User {

    lateinit var password: String
    lateinit var login: String
    lateinit var name: String
    var favoriteMovies : MutableList<HashMap<String, Any?>>? = mutableListOf()
    var viewedMovies : MutableList<HashMap<String, Any?>>? = mutableListOf()

    public constructor(_name: String, _login: String, _password: String){
        password = _password
        login = _login
        name = _name
        favoriteMovies = mutableListOf()
        viewedMovies = mutableListOf()
    }
}