package com.shashank.dagger2cwm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import javax.inject.Named

class AuthActivity : DaggerAppCompatActivity() {

    private val TAG = "AuthActivity"

    @Inject
    lateinit var myString: String

    @set:[Inject Named("myBool")]
    var myBool: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        Log.d(TAG,"xlr8: $myString")
        Log.d(TAG,"xlr8: $myBool")
    }
}