package com.shashank.dagger2cwm.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.shashank.dagger2cwm.R
import com.shashank.dagger2cwm.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment: DaggerFragment() {

    private val TAG = "ProfileFragment"

    private lateinit var viewModel: ProfileViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Toast.makeText(activity, "Profile Fragment", Toast.LENGTH_SHORT).show()
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: ProfileFragment was created...")

        viewModel = ViewModelProvider(this, providerFactory)[ProfileViewModel::class.java]
    }
}