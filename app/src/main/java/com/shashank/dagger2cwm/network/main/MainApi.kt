package com.shashank.dagger2cwm.network.main

import com.shashank.dagger2cwm.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    //  /posts?userId=1/
    @GET("posts")
    fun getPostsFromUser(@Query("userId") id: Int?): Flowable<List<Post>>
}