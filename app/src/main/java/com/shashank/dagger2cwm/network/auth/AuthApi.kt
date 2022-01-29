package com.shashank.dagger2cwm.network.auth

import com.shashank.dagger2cwm.models.User
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

// need to be only accessible bu AuthComponent
interface AuthApi {

    // need to replace it with original api
    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Flowable<User>
}