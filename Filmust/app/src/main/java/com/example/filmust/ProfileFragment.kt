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
    private var name: String = ""
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

        binding!!.cardView.setOnClickListener{

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        private const val ARG_LOGIN = "ARG_LOGIN"
        private const val ARG_NAME = "ARG_NAME"

        fun createBundle(login: String, name: String?): Bundle {
            val bundle = Bundle()
            bundle.putString(ARG_LOGIN, login)
            bundle.putString(ARG_NAME, name)
            return bundle
        }
    }

    fun putStrings(){
        val loginStr = arguments?.getString(ARG_LOGIN)
        binding!!.tvEmail.setText("@$loginStr")
        val nameStr = arguments?.getString(ARG_NAME)
        binding!!.tvName.setText(nameStr)
    }
}