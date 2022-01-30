package com.shashank.dagger2cwm.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.shashank.dagger2cwm.R
import com.shashank.dagger2cwm.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostsFragment: DaggerFragment() {

    private val TAG = "PostsFragment"

    private lateinit var viewModel: PostsViewModel
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view)
        viewModel = ViewModelProvider(this, providerFactory)[PostsViewModel::class.java]

        subscribeObservers()
    }


    private fun subscribeObservers() {
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(viewLifecycleOwner, {
            Log.d(TAG,"onChanged: ${it.data}")
        })
    }
}