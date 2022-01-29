package com.shashank.dagger2cwm.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.shashank.dagger2cwm.models.User
import com.shashank.dagger2cwm.network.auth.AuthApi
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authApi: AuthApi
): ViewModel() {

    private val TAG = "AuthViewModel"

    private val authUser: MediatorLiveData<User> = MediatorLiveData()

    init {
        Log.d(TAG,"xlr8: Auth ViewModel is working")
    }

    fun authenticateWithId(userId: Int) {
        val source: LiveData<User> = LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId).subscribeOn(Schedulers.io())
        )

        authUser.addSource(source) { user ->
            authUser.value = user
            authUser.removeSource(source)
        }
    }

    fun observeUser(): LiveData<User> = authUser
}