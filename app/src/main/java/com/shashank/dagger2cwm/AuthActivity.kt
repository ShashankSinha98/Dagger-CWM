package com.shashank.dagger2cwm

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import javax.inject.Named

class AuthActivity : DaggerAppCompatActivity() {

    private val TAG = "AuthActivity"

    @Inject @JvmField
    var logo: Drawable?=null

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        setLogo()

    }

    private fun setLogo() {
        requestManager.load(logo)
            .into(findViewById<ImageView>(R.id.login_logo))
    }
}