package com.example.filmust

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.filmust.databinding.FragmentProfileBinding
import com.example.filmust.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var binding: FragmentProfileBinding? = null
    private lateinit var rootNode: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private var login: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)
        putStrings()

        binding!!.ivExit.setOnClickListener{
            findNavController().navigate(
                R.id.action_profileFragment_to_signInFragment,
                SignInFragment.createBundle(login, "")
            )
        }

//        binding!!.cardView.setOnClickListener{
//
//        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        private const val ARG_LOGIN = "ARG_LOGIN"

        fun createBundle(login: String): Bundle {
            val bundle = Bundle()
            bundle.putString(ARG_LOGIN, login)
            return bundle
        }
    }

    fun putStrings(){
        login = arguments?.getString(ARG_LOGIN)!!
        binding!!.tvEmail.setText("@$login")
        rootNode = FirebaseDatabase.getInstance()
        reference = rootNode.getReference("users")
        val query = reference.orderByChild("login").equalTo(login)
        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val userName = dataSnapshot.child(login).child("name").getValue(String::class.java)
                    binding!!.tvName.setText(userName)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("TAG", error.message, error.toException())
            }
        })
    }
}