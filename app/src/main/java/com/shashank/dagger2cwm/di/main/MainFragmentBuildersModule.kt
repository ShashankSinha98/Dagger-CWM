package com.shashank.dagger2cwm.di.main

import com.shashank.dagger2cwm.ui.main.posts.PostsFragment
import com.shashank.dagger2cwm.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

// contains injectors for all fragments belonging to Main Activity/Component
@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostsFragment(): PostsFragment
}