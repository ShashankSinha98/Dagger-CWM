package com.shashank.dagger2cwm.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.shashank.dagger2cwm.SessionManager
import com.shashank.dagger2cwm.network.auth.AuthApi
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import com.shashank.dagger2cwm.models.User
import java.lang.Exception


class AuthViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
): ViewModel() {

    private val TAG = "AuthViewModel"

    fun authenticateWithId(userId: Int) {
        Log.d(TAG,"authenticateWithId: Attempting to login...")
        sessionManager.authenticateWithId(queryUserId(userId))
    }

    private fun queryUserId(userId: Int): LiveData<AuthResource<User>> {
        return LiveDataReactiveStreams.fromPublisher(

            authApi.getUser(userId) // get flowable user

                // instead of calling onError, do this
                .onErrorReturn {
                    val errorUser = User(-1)
                    errorUser
                }

                // wrap User object in AuthResource
                .map<AuthResource<User>>(Function<User, AuthResource<User>> { user ->
                    if (user.id == -1) {
                        AuthResource.error("Could not authenticate", null)
                    } else AuthResource.authenticated(user)
                })
                .subscribeOn(Schedulers.io())
        )
    }


    fun observeAuthState(): LiveData<AuthResource<User>> = sessionManager.getAuthUser()
}