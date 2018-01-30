package example.aleks.com.postapp.ui.posts.details.mvp


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import example.aleks.com.postapp.R
import example.aleks.com.postapp.avatar.AvatarProvider
import example.aleks.com.postapp.models.PostDetailsViewModel
import example.aleks.com.postapp.ui.base.BaseFragment
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [PostDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostDetailsFragment : BaseFragment(), PostDetailsView {

    //region properties
    @Inject
    lateinit var presenter: PostDetailsBasePresenter

    @Inject
    lateinit var avatarProvider: AvatarProvider

    @Inject
    lateinit var appCompatActivity: AppCompatActivity

    private var postId: Int = 0

    private lateinit var loadingView: ProgressBar
    private lateinit var postTitleTextView: TextView
    private lateinit var postBodyTextView: TextView
    private lateinit var userNameTextView: TextView
    private lateinit var postTotalCommentsTextView: TextView
    private lateinit var postTitleLabelTextView: TextView
    private lateinit var userNameLabelTextView: TextView
    private lateinit var postBodyLabelTextView: TextView
    private lateinit var userAvatarImageView: ImageView
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)

        val bundle = savedInstanceState ?: arguments
        postId = bundle.getInt(postIdKey, -1)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        appCompatActivity.supportActionBar?.show()
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appCompatActivity.supportActionBar?.setDisplayShowTitleEnabled(true)

        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_post_details, container, false)

        loadingView = rootView.findViewById(R.id.loadingIndicator)
        postTitleTextView = rootView.findViewById(R.id.postTitleTextView)
        postBodyTextView = rootView.findViewById(R.id.postBodyTextView)
        userNameTextView = rootView.findViewById(R.id.userNameTextView)
        postTotalCommentsTextView = rootView.findViewById(R.id.postTotalCommentsTextView)
        postTitleLabelTextView = rootView.findViewById(R.id.postTitleLabelTextView)
        userNameLabelTextView = rootView.findViewById(R.id.userNameLabelTextView)
        postBodyLabelTextView = rootView.findViewById(R.id.postBodyLabelTextView)
        userAvatarImageView = rootView.findViewById(R.id.userAvatarImageView)

        return rootView
    }

    override fun onResume() {
        super.onResume()
        viewAttached()
    }

    override fun onPause() {
        super.onPause()
        viewDetached()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState?.putInt(postIdKey, postId)
    }
    //endregion

    //region companion object
    companion object {

        val TAG = PostDetailsFragment::class.java.simpleName

        private val postIdKey = "PostDetailsFragment.comicId"

        fun newInstance(postId: Int): PostDetailsFragment {

            val bundle = Bundle()
            bundle.putInt(postIdKey, postId)

            val posDetailsFragment = PostDetailsFragment()
            posDetailsFragment.arguments = bundle

            return posDetailsFragment
        }
    }
    //endregion

    //region PostsView implementation
    override fun viewAttached() {

        presenter.onAttach(this)
        presenter.getPost(postId)
    }

    override fun viewDetached() {

        presenter.onDetach()
    }

    override fun showLoading() {

        loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {

        loadingView.visibility = View.GONE
    }

    override fun onPostLoaded(post: PostDetailsViewModel) {

        postTitleTextView.text = post.postTitle
        postBodyTextView.text = post.postBody
        userNameTextView.text = post.user?.userName ?: getString(R.string.not_available)
        postTotalCommentsTextView.text = String.format("%s: %d", getString(R.string.comments), post.commentsCount)

        postTitleLabelTextView.visibility = View.VISIBLE
        postBodyLabelTextView.visibility = View.VISIBLE
        userNameLabelTextView.visibility = View.VISIBLE

        Glide.with(appCompatActivity)
                .load(avatarProvider.getAvatarUrl(post.userId))
                .apply(com.bumptech.glide.request.RequestOptions.circleCropTransform())
                .error(Glide.with(userAvatarImageView).load(R.drawable.ic_sentiment_dissatisfied_black))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(userAvatarImageView)
    }
    //endregion

}// Required empty public constructor
