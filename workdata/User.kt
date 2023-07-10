package com.example.filmust.workdata

class User {

    lateinit var password: String
    lateinit var login: String
    lateinit var name: String

    public constructor(_name: String, _login: String, _password: String){
        password = _password
        login = _login
        name = _name
    }
}