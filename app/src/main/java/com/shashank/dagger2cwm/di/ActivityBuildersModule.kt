package com.shashank.dagger2cwm.di

import com.shashank.dagger2cwm.AuthActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector




// All activities in this project which will serve as client for dagger app component need to be declared here

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeAuthActivity(): AuthActivity
}