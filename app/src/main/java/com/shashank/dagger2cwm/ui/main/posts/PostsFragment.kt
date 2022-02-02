package com.shashank.dagger2cwm.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shashank.dagger2cwm.R
import com.shashank.dagger2cwm.models.Post
import com.shashank.dagger2cwm.ui.main.Resource
import com.shashank.dagger2cwm.util.VerticalSpaceItemDecoration
import com.shashank.dagger2cwm.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostsFragment: DaggerFragment() {

    private val TAG = "PostsFragment"

    private lateinit var viewModel: PostsViewModel
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var postRecyclerAdapter: PostRecyclerAdapter

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

        initRecyclerView()

        subscribeObservers()
    }


    private fun subscribeObservers() {
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(viewLifecycleOwner, { listResource: Resource<List<Post>> ->

            when(listResource.status) {
                Resource.Status.LOADING -> {
                    Log.d(TAG,"onChanged: LOADING...")
                }

                Resource.Status.SUCCESS -> {
                    Log.d(TAG,"xlr8 onChanged: got posts...")
                    postRecyclerAdapter.setPosts(listResource.data)
                }

                Resource.Status.ERROR -> {
                    Log.d(TAG,"onChanged: Error... ${listResource.message}")
                }
            }
        })
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(VerticalSpaceItemDecoration(15))
        recyclerView.adapter = postRecyclerAdapter
    }
}