package com.shashank.dagger2cwm.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun someString(): String = "Built by shashank sinha"

        @Provides
        @JvmStatic
        fun someBool(application: Application): Boolean = application==null
    }
}