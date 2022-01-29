package com.shashank.dagger2cwm.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.shashank.dagger2cwm.BaseActivity
import com.shashank.dagger2cwm.R

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this,"Main Activity", Toast.LENGTH_SHORT).show()
    }
}