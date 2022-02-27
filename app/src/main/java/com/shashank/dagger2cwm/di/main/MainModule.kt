package com.shashank.dagger2cwm.di.main

import com.shashank.dagger2cwm.network.main.MainApi
import com.shashank.dagger2cwm.ui.main.posts.PostRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideRecyclerAdapter(): PostRecyclerAdapter {
        return PostRecyclerAdapter()
    }

    @MainScope
    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }
}