package com.shashank.dagger2cwm.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.shashank.dagger2cwm.R
import com.shashank.dagger2cwm.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import com.shashank.dagger2cwm.ui.auth.AuthResource.AuthStatus.*

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    private val TAG = "AuthActivity"

    lateinit var authViewModel: AuthViewModel

    @Inject @JvmField
    var logo: Drawable?=null

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var requestManager: RequestManager

    private lateinit var userIdET: EditText
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        userIdET = findViewById(R.id.user_id_input)
        progressBar = findViewById(R.id.progress_bar)
        findViewById<Button>(R.id.login_button).setOnClickListener(this)

        authViewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)

        setLogo()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        authViewModel.observeAuthState().observe(this){ authResource ->
            authResource?.let {

                when(it.status) {

                    LOADING -> {
                        showProgressBar(true)
                    }

                    AUTHENTICATED -> {
                        showProgressBar(false)
                        Log.d(TAG,"onChanged: Login Success: ${it.data?.email}")
                    }

                    ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(this,"${it.message}\nDid you enter a no between 1 and 10?", Toast.LENGTH_SHORT).show()
                    }

                    NOT_AUTHENTICATED -> {
                        showProgressBar(false)
                    }

                }
            }

        }
    }

    private fun showProgressBar(isVisible: Boolean) {
        if(isVisible)
            progressBar.visibility = View.VISIBLE
        else
            progressBar.visibility = View.INVISIBLE
    }

    private fun setLogo() {
        requestManager.load(logo)
            .into(findViewById<ImageView>(R.id.login_logo))
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.login_button -> {
                attemptLogin()
            }
        }
    }

    private fun attemptLogin() {
        if(TextUtils.isEmpty(userIdET.text.toString())) return

        val userInput = userIdET.text.toString()
        if(userInput.all { Character.isDigit(it) })
            authViewModel.authenticateWithId(userInput.toInt())
        else {
            Toast.makeText(this,"Please enter a valid no", Toast.LENGTH_SHORT).show()
        }
    }
}