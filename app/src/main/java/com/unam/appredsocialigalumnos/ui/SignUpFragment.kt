package com.unam.appredsocialigalumnos.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.unam.appredsocialigalumnos.R
import com.unam.appredsocialigalumnos.databinding.FragmentSingUpBinding


class SignUpFragment : FragmentBase<FragmentSingUpBinding>(
    R.layout.fragment_sing_up,FragmentSingUpBinding::bind) {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun reload() {


    }


    override fun initElements() {
        binding.btnRegister.setOnClickListener {
            val username = binding.tvUsernameSignup.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.editPasswordSingup.text.toString()
            val repassword = binding.editRePasswordSignup.toString()
            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && repassword.isNotEmpty()) {
                if (password == repassword) {
                    createAccount(email, password)
                }
            }

        }
    }
    private fun createAccount(email:String,password:String){
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    startNavMainActivity(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(requireContext(), "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    startNavMainActivity(null)
                }
            }
        // [END create_user_with_email]

    }
    private fun startNavMainActivity(user: FirebaseUser?) {
        val intent = Intent(requireContext(),NavigationActivity::class.java)
        intent.putExtra(USERNAME_KEY, user)
        startActivity(intent)
        requireActivity().finish()
    }
    companion object{
        private const val TAG="EmailPassword"
    }
}

