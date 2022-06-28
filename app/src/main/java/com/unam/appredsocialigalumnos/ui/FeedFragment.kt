package com.unam.appredsocialigalumnos.ui


import com.unam.appredsocialigalumnos.R
import com.unam.appredsocialigalumnos.databinding.FragmentFeedBinding

class FeedFragment : FragmentBase<FragmentFeedBinding>(
    R.layout.fragment_feed, FragmentFeedBinding::bind) {

    override fun initElements() {
        showCollapsingToolBar(true)
    }
}