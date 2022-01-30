package com.shashank.dagger2cwm.ui.main.posts

import android.util.Log
import androidx.lifecycle.ViewModel
import com.shashank.dagger2cwm.SessionManager
import com.shashank.dagger2cwm.network.main.MainApi
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi
): ViewModel() {

    private val TAG = "PostsViewModel"

    init {
        Log.d(TAG,"PostsViewModel is working...")
    }
}