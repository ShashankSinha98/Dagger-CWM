package com.shashank.dagger2cwm.di

import androidx.lifecycle.ViewModelProvider
import com.shashank.dagger2cwm.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(providerFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}