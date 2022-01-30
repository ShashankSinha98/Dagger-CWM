package com.shashank.dagger2cwm.ui.main.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.shashank.dagger2cwm.SessionManager
import com.shashank.dagger2cwm.models.Post
import com.shashank.dagger2cwm.models.User
import com.shashank.dagger2cwm.network.main.MainApi
import com.shashank.dagger2cwm.ui.auth.AuthResource
import com.shashank.dagger2cwm.ui.main.Resource
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi
): ViewModel() {

    private val TAG = "PostsViewModel"

    private val posts: MediatorLiveData<Resource<List<Post>>> = MediatorLiveData()


    fun observePosts(): LiveData<Resource<List<Post>>> {

        posts.value = Resource.loading(null)

        Log.d(TAG,"observePosts: userId: ${sessionManager.getAuthUser().value?.data?.id}")

        val source: LiveData<Resource<List<Post>>> = LiveDataReactiveStreams.fromPublisher( // RxJava
            mainApi.getPostsFromUser(sessionManager.getAuthUser().value?.data?.id)

                .onErrorReturn {
                    val errorPost = Post(id=-1)
                    val posts: List<Post> = listOf(errorPost)
                    posts
                }

                .map<Resource<List<Post>>>(Function<List<Post>, Resource<List<Post>>> { posts: List<Post> ->
                    if (posts.isNotEmpty() && posts[0].id == -1) {
                        Resource.error("Something went wrong", null)
                    } else Resource.success(posts)

                })
                .subscribeOn(Schedulers.io()))

        posts.addSource(source) { listResource: Resource<List<Post>> ->
            posts.value = listResource
            posts.removeSource(source)
        }

        return posts
    }
}