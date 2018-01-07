package example.aleks.com.postapp.ui.posts

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import example.aleks.com.postapp.R
import example.aleks.com.postapp.avatar.AvatarProvider
import example.aleks.com.postapp.models.PostViewModel
import example.aleks.com.postapp.ui.main.MainPresenter
import javax.inject.Inject

/**
 * Created by aleks on 06/01/2018.
 */
class PostsAdapter @Inject constructor(private val appCompatActivity: AppCompatActivity,
                                       private val presenter: MainPresenter,
                                       private val avatarProvider: AvatarProvider) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    //region properties
    val adapterItems: MutableList<PostViewModel> = mutableListOf()
    //endregion

    //region RecyclerView.Adapter implementation
    override fun getItemCount() = adapterItems.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): PostViewHolder {

        val inflater = LayoutInflater.from(parent?.context)
        val itemView = inflater.inflate(R.layout.layout_post_view_holder, parent, false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder?, position: Int) {

        val viewModel = adapterItems[position]
        holder?.onBind(viewModel)
        holder?.itemView?.setOnClickListener({

            viewModel.postId.let { postId ->

                presenter.getPostDetails(postId)
            }
        })
    }
    //endregion

    //region ViewHolder class
    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //region properties
        private val postTitleTextView: TextView = itemView.findViewById(R.id.postTitleTextView)
        private val userAvatarImageView: ImageView = itemView.findViewById(R.id.userAvatarImageView)
        //endregion

        fun onBind(viewModel: PostViewModel) {

            postTitleTextView.text = viewModel.postTitle
            Glide.with(this@PostsAdapter.appCompatActivity)
                    .load(this@PostsAdapter.avatarProvider.getAvatarUrl(viewModel.userId))
                    .error(Glide.with(userAvatarImageView).load(R.drawable.ic_sentiment_dissatisfied_black))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(com.bumptech.glide.request.RequestOptions.circleCropTransform())
                    .into(userAvatarImageView)
        }
    }
    //endregion
}