package com.unam.appredsocialigalumnos.ui


import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unam.appredsocialigalumnos.R
import com.unam.appredsocialigalumnos.data.PostApi
import com.unam.appredsocialigalumnos.data.PostList
import com.unam.appredsocialigalumnos.databinding.FragmentFeedBinding
import com.unam.appredsocialigalumnos.network.RetrofitInstance
import com.unam.appredsocialigalumnos.ui.recyclerviews.AdapterPosts
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class FeedFragment : FragmentBase<FragmentFeedBinding>(
    R.layout.fragment_feed, FragmentFeedBinding::bind) {

    private var layoutManager: RecyclerView.LayoutManager?=null
    private var adapterPost:RecyclerView.Adapter<AdapterPosts.ViewHolder>?=null
    private var postList= mutableListOf<PostApi>()
    private lateinit var  call : Call<PostList>


    override fun initElements() {
        showCollapsingToolBar(true)
        binding.lavRadar.visibility= View.VISIBLE
        layoutManager=LinearLayoutManager(requireContext())
        binding.recyclerViewFeed.layoutManager=layoutManager

        adapterPost = AdapterPosts(postList,requireContext())
        binding.recyclerViewFeed.adapter=adapterPost
        binding.recyclerViewFeed.setHasFixedSize(true)

        //consumo de apis

        val apiService = RetrofitInstance.api
        call = apiService.getPost("character")
        call.enqueue(object: Callback <PostList> {
            override fun onResponse(call: Call<PostList>, response: Response<PostList>) {
                postList.addAll(response.body()!!.results)
                binding.lavRadar.visibility= View.GONE
                binding.recyclerViewFeed.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<PostList>, t: Throwable) {
                t.message?.let{ Log.e("RETROFIT: ", it)}
                t.stackTrace
            }

        })



     }
}