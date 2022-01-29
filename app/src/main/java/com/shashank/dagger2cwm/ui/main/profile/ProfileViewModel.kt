package com.shashank.dagger2cwm.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shashank.dagger2cwm.SessionManager
import com.shashank.dagger2cwm.models.User
import com.shashank.dagger2cwm.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val sessionManager: SessionManager
): ViewModel() {

    private val TAG = "ProfileViewModel"

    init {
        Log.d(TAG,"ProfileViewModel: viewModel is ready..")
    }

    fun getAuthenticatedUser(): LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }

}