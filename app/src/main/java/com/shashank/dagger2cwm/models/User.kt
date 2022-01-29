package com.shashank.dagger2cwm.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("username")
    @Expose
    val username: String?=null,

    @SerializedName("email")
    @Expose
    val email: String?=null,

    @SerializedName("website")
    @Expose
    val website: String?=null
)
