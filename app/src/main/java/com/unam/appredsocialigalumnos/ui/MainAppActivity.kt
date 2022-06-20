package com.unam.appredsocialigalumnos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.unam.appredsocialigalumnos.R
import com.unam.appredsocialigalumnos.databinding.ActivityMainBinding

class MainAppActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AppRedSocialIGAlumnos)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }
}