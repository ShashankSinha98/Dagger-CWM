package com.shashank.dagger2cwm.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class AuthViewModel @Inject constructor(): ViewModel() {

    private val TAG = "AuthViewModel"

    init {
        Log.d(TAG,"xlr8: Auth ViewModel is working")
    }
}