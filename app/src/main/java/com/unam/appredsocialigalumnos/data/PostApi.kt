package com.unam.appredsocialigalumnos.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class PostApi (
    @SerializedName("id") var likes: Int,
    @SerializedName("name") var name: String,
    @SerializedName("status") var status: String,
    @SerializedName("species") var species: String,
    @SerializedName("type") var type: String,
    @SerializedName("gender") var gender: String,
    @SerializedName("origin") var origin: Objects,
    @SerializedName("location") var location: Objects,
    @SerializedName("image") var image:String,
    @SerializedName("episode") var episode: List<String>,
    @SerializedName("url") var url: String,
    @SerializedName("created") var created: String
)