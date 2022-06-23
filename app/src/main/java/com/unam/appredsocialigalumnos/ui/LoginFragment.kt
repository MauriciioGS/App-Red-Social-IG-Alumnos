package com.unam.appredsocialigalumnos.ui

import android.content.Intent
import com.unam.appredsocialigalumnos.R
import com.unam.appredsocialigalumnos.databinding.FragmentHostLoginBinding
import com.unam.appredsocialigalumnos.util.findNavControllerSafely

class LoginFragment :  FragmentBase<FragmentHostLoginBinding>(
    R.layout.fragment_host_login, FragmentHostLoginBinding::bind) {

    override fun initElements() {
        binding.tvSignUp.setOnClickListener {
            findNavControllerSafely()?.navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.btnLogin.setOnClickListener {
            Intent(requireContext(),NavigationActivity::class.java ).let(::startActivity)
        requireActivity().finish()}

    }

}