package com.shashank.dagger2cwm.ui.main.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shashank.dagger2cwm.R
import com.shashank.dagger2cwm.models.Post

class PostRecyclerAdapter: RecyclerView.Adapter<PostRecyclerAdapter.PostViewHolder>() {

    private val posts: MutableList<Post> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.layout_post_list_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size

    fun setPosts(posts: List<Post>?) {
        posts?.let {
            with(this.posts) {
                clear()
                addAll(posts)
            }
            notifyDataSetChanged()
        }
    }

    class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val title: TextView = itemView.findViewById(R.id.title)

        fun bind(post: Post) {
            title.text = post.title
        }
    }
}