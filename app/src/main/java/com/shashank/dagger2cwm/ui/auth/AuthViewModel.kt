package com.shashank.dagger2cwm.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.shashank.dagger2cwm.network.auth.AuthApi
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authApi: AuthApi
): ViewModel() {

    private val TAG = "AuthViewModel"

    init {
        Log.d(TAG,"xlr8: Auth ViewModel is working")

        if(authApi==null) {
            Log.d(TAG, "xlr8: Auth Api is null")
        } else {
            Log.d(TAG, "xlr8: Auth Api is not null")
        }
    }
}