package com.shashank.dagger2cwm.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.shashank.dagger2cwm.R
import com.shashank.dagger2cwm.models.User
import com.shashank.dagger2cwm.ui.auth.AuthResource
import com.shashank.dagger2cwm.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment: DaggerFragment() {

    private val TAG = "ProfileFragment"

    private lateinit var viewModel: ProfileViewModel

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var email: TextView
    private lateinit var username: TextView
    private lateinit var website: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: ProfileFragment was created...")
        email = view.findViewById(R.id.email)
        username = view.findViewById(R.id.username)
        website = view.findViewById(R.id.website)

        viewModel = ViewModelProvider(this, providerFactory)[ProfileViewModel::class.java]
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner, { userAuthResource: AuthResource<User> ->
            when(userAuthResource.status) {
                AuthResource.AuthStatus.AUTHENTICATED -> {
                    setUserDetails(userAuthResource.data)
                }

                AuthResource.AuthStatus.ERROR -> {
                    setErrorDetails(userAuthResource.message)
                }
            }
        })
    }

    private fun setErrorDetails(message: String?) {
        email.text = message
        username.text = "error"
        website.text = "error"
    }

    private fun setUserDetails(data: User?) {
        email.text = data?.email
        username.text = data?.username
        website.text = data?.website
    }


}