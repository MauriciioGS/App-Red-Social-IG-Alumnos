package com.unam.appredsocialigalumnos.ui

import android.content.Intent
import com.unam.appredsocialigalumnos.R
import com.unam.appredsocialigalumnos.databinding.FragmentSingUpBinding


class SignUpFragment : FragmentBase<FragmentSingUpBinding>(
    R.layout.fragment_sing_up,FragmentSingUpBinding::bind) {
    override fun initElements() {
        binding.btnRegister.setOnClickListener {

                Intent(requireContext(),NavigationActivity::class.java ).let(::startActivity)
            requireActivity().finish()
        }
    }
}
