package com.shashank.dagger2cwm.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
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
    private val authApi: AuthApi
): ViewModel() {

    private val TAG = "AuthViewModel"

    private val authUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    init {
        Log.d(TAG,"xlr8: Auth ViewModel is working")
    }

    fun authenticateWithId(userId: Int) {

        authUser.value = AuthResource.loading(null)

        val source = LiveDataReactiveStreams.fromPublisher(

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

        authUser.addSource(source) { user ->
            authUser.value = user
            authUser.removeSource(source)
        }
    }

    fun observeUser(): LiveData<AuthResource<User>> = authUser
}