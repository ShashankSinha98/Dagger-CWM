package com.shashank.dagger2cwm.network.main

import retrofit2.http.GET

interface MainApi {

    //  /posts?userId=1/
    @GET("posts")
    fun getPostsFromUser()
}