package com.unam.appredsocialigalumnos.data


data class User(
    var username: String = "",
    var email : String = "",
    var postsList: List<Post>? = null
)
