package com.shashank.dagger2cwm.di

import android.app.Application
import com.shashank.dagger2cwm.BaseApplication
import com.shashank.dagger2cwm.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
                ActivityBuildersModule::class,
                AppModule::class, ViewModelFactoryModule::class]
)
interface AppComponent: AndroidInjector<BaseApplication> {

    fun sessionManager(): SessionManager

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}