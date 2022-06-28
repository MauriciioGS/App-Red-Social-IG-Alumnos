package com.unam.appredsocialigalumnos.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.unam.appredsocialigalumnos.R
import com.unam.appredsocialigalumnos.data.User
import com.unam.appredsocialigalumnos.databinding.FragmentSingUpBinding
import com.unam.appredsocialigalumnos.network.Callback
import com.unam.appredsocialigalumnos.network.FirestoreService
import com.unam.appredsocialigalumnos.network.USERS_COLLECTION_NAME
import com.unam.appredsocialigalumnos.util.findNavControllerSafely

class SignUpFragment :  FragmentBase<FragmentSingUpBinding>(
    R.layout.fragment_sing_up, FragmentSingUpBinding::bind) {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestoreService: FirestoreService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        firestoreService = FirestoreService(FirebaseFirestore.getInstance())
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null){
            reload()
        }
    }

    private fun reload() {

    }

    override fun initElements() {
        binding.btnRegister.setOnClickListener {
            showAlert()
            val username = binding.tvUsernameSignup.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.editPasswordSingup.text.toString()
            val repassword = binding.editRePasswordSignup.text.toString()
            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && repassword.isNotEmpty()){
                if (password == repassword){
                    firestoreService.findUserById(username, object : Callback<User>{
                        override fun onSuccess(result: User?) {
                            if (result == null){
                                val user = User()
                                user.username = username
                                user.email = email
                                view?.let { it1 ->
                                    createAccount(user, password)}
                            }else{
                                Toast.makeText(requireContext(),"El correo $email ya existe",Toast.LENGTH_LONG).show()
                                findNavControllerSafely()?.navigate(R.id.action_signUpFragment_to_loginFragment)
                            }
                        }

                        override fun onFailed(exception: java.lang.Exception) {
                            showErrorDialog("¡Error!","Problemas con el servidor")
                            Log.e(TAG, "¡Error!", exception)
                            hideAlert()
                        }

                    })
                }
            }
        }
    }

    private fun createAccount(user: User, password : String) {
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(user.email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    saveUser(user)
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val userAuth = auth.currentUser
                    startNavMainActivity(userAuth)
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

    private fun saveUser(user: User) {
        firestoreService.setDocument(user, USERS_COLLECTION_NAME, user.username, object :
            Callback<Void> {
            override fun onSuccess(result: Void?) {

            }

            override fun onFailed(exception: Exception) {
                showErrorDialog("¡Error!","Problemas con el servidor")
                Log.e(TAG, "¡Error!", exception)
                hideAlert()
            }

        })
    }

    private fun startNavMainActivity(user: FirebaseUser?) {
        val intent = Intent(requireContext(),NavigationActivity::class.java)
        intent.putExtra(USERNAME_KEY, user)
        startActivity(intent)
        hideAlert()
        requireActivity().finish()
    }

    companion object {
        private const val TAG = "EmailPassword"
    }
}