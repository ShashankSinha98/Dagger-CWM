package com.shashank.dagger2cwm.di.main

import androidx.lifecycle.ViewModel
import com.shashank.dagger2cwm.di.ViewModelKey
import com.shashank.dagger2cwm.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel
}