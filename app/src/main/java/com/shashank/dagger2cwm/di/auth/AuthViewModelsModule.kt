package com.shashank.dagger2cwm.di.auth

import androidx.lifecycle.ViewModel
import com.shashank.dagger2cwm.di.ViewModelKey
import com.shashank.dagger2cwm.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel
}