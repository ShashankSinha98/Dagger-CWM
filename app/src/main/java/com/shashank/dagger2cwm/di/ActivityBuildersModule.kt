package com.shashank.dagger2cwm.di

import com.shashank.dagger2cwm.di.auth.AuthModule
import com.shashank.dagger2cwm.di.auth.AuthScope
import com.shashank.dagger2cwm.di.auth.AuthViewModelsModule
import com.shashank.dagger2cwm.di.main.MainFragmentBuildersModule
import com.shashank.dagger2cwm.di.main.MainModule
import com.shashank.dagger2cwm.di.main.MainScope
import com.shashank.dagger2cwm.di.main.MainViewModelsModule
import com.shashank.dagger2cwm.ui.auth.AuthActivity
import com.shashank.dagger2cwm.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector




// All activities in this project which will serve as client for dagger app component need to be declared here

@Module
abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
        modules = [AuthViewModelsModule::class, AuthModule::class] // Only AuthActivity will be able to use AuthViewModelsModule
    )
    abstract fun contributeAuthActivity(): AuthActivity // Auth Component


    @MainScope
    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class, MainModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity // Main Component
}