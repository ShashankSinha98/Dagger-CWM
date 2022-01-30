package com.shashank.dagger2cwm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.shashank.dagger2cwm.models.User
import com.shashank.dagger2cwm.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {
    private val TAG = "SessionManager"

    // single source of truth (data)
    private val cachedUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    fun authenticateWithId(source: LiveData<AuthResource<User>>) {
        cachedUser.value = AuthResource.loading(null)
        cachedUser.addSource(source) { userAuthResource: AuthResource<User> ->
            cachedUser.value = userAuthResource
            cachedUser.removeSource(source)
        }

        Log.d(TAG, "authenticateWithId: previous session detected. Retrieving user from cache")
    }

    fun logOut() {
        Log.d(TAG,"logOut: logging out...")
        cachedUser.value = AuthResource.logout()
    }

    fun getAuthUser(): LiveData<AuthResource<User>> = cachedUser




}