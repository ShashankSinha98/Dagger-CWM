package com.shashank.dagger2cwm.network.auth

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

// need to be only accessible bu AuthComponent
interface AuthApi {

    // need to replace it with original api
    @GET
    fun getFakeStuff(): Call<ResponseBody>
}