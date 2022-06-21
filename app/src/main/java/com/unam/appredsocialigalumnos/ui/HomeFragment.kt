package com.unam.appredsocialigalumnos.ui

import androidx.viewpager2.widget.ViewPager2
import com.unam.appredsocialigalumnos.R
import com.unam.appredsocialigalumnos.databinding.FragmentHomeBinding
import com.unam.appredsocialigalumnos.databinding.FragmentSearchBinding
import com.unam.appredsocialigalumnos.util.ViewPagerAdapter

class HomeFragment :  FragmentBase<FragmentHomeBinding>(
    R.layout.fragment_home, FragmentHomeBinding::bind) {

    override fun initElements() {
        showCollapsingToolBar(true)
        initViews()
    }

    private fun initViews() {
        setUpViewPager(binding.viewpagerHome)
    }

    private fun setUpViewPager(viewpager: ViewPager2) {
        val adapter = ViewPagerAdapter(this)
        adapter.addFragment(FeedFragment())
        // Mensajes
        // Historias
        viewpager.adapter = adapter
    }
}