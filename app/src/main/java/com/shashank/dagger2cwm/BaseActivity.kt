package com.shashank.dagger2cwm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.shashank.dagger2cwm.ui.auth.AuthActivity
import com.shashank.dagger2cwm.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseActivity: DaggerAppCompatActivity() {

    private val TAG = "BaseActivity"

    @Inject
    lateinit var sessionManager: SessionManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        sessionManager.getAuthUser().observe(this) { authResource ->
            authResource?.let {

                when (it.status) {

                    AuthResource.AuthStatus.LOADING -> {
                    }

                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        Log.d(TAG, "onChanged: Login Success: ${it.data?.email}")
                    }

                    AuthResource.AuthStatus.ERROR -> {
                    }

                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        navLoginScreen()
                    }

                }
            }
        }
    }


    private fun navLoginScreen() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}