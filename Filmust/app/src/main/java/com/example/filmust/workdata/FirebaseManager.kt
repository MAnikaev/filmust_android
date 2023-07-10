package com.example.filmust.workdata

import com.example.filmust.fragments.ProfileFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.database.ValueEventListener


class FirebaseManager {

    private var rootNode: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var reference: DatabaseReference = rootNode.getReference("users")
    var favoriteReference = reference.child(ProfileFragment.login).child("favoriteMovie")
    var viewedReference = reference.child(ProfileFragment.login).child("viewedMovies")

    fun readUserData() {
        favoriteReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                MoviesRepository.favoriteMovies = snapshot.getValue(object : GenericTypeIndicator<MutableList<Movie>>() {})
            }

            override fun onCancelled(error: DatabaseError) {
                // Обработка ошибки
            }
        })

        viewedReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                MoviesRepository.viewedMovies = snapshot.getValue(object : GenericTypeIndicator<MutableList<Movie>>() {})
            }

            override fun onCancelled(error: DatabaseError) {
                // Обработка ошибки
            }
        })
    }

    fun writeUserData(favoriteMovies: List<Movie>, viewedMovies: List<Movie>, callback: () -> Unit) {
        favoriteReference.setValue(MoviesRepository.favoriteMovies)
        viewedReference.setValue(MoviesRepository.viewedMovies)
    }

    fun logoutUser() {
        // Выход пользователя из приложения
    }
}