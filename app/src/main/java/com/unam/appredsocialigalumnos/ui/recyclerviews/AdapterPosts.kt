package com.unam.appredsocialigalumnos.ui.recyclerviews


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.unam.appredsocialigalumnos.R
import com.unam.appredsocialigalumnos.data.PostApi

class AdapterPosts (private val postList :List <PostApi>, val context:Context):RecyclerView.Adapter<AdapterPosts.ViewHolder>(){
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var username:TextView
        var userPhoto:ImageView
        var username2:TextView
        var currUserPhoto:ImageView
        var post:ImageView
        var likes:TextView
        init{
            username=itemView.findViewById(R.id.tv_username)
            userPhoto=itemView.findViewById(R.id.iv_userPhoto)
            username2=itemView.findViewById(R.id.tv_username2)
            currUserPhoto=itemView.findViewById(R.id.iv_curr_userPhoto)
            post=itemView.findViewById(R.id.iv_post_image)
            likes=itemView.findViewById(R.id.tv_likesPost)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val v=LayoutInflater.from(parent.context)
          .inflate(R.layout.post_item,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.username.text=postList[position].name
        holder.username2.text=postList[position].name
        holder.likes.text=postList[position].likes.toString()
        Glide.with(context)
            .load(postList[position].image)
            .circleCrop()
            .into(holder.userPhoto)
        Glide.with(context)
            .load(postList[position].image)
            .centerInside()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.post)
    }

    override fun getItemCount(): Int = postList.size




}