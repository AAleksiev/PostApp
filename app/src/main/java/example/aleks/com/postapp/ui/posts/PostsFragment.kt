package example.aleks.com.postapp.ui.posts


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import example.aleks.com.postapp.R
import example.aleks.com.postapp.models.PostViewModel
import example.aleks.com.postapp.ui.base.BaseFragment
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [PostsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostsFragment : BaseFragment(), PostsView {

    //region properties
    @Inject
    lateinit var presenter: PostsPresenter

    @Inject
    lateinit var postsAdapter: PostsAdapter

    override val viewItems: List<PostViewModel>
        get() = postsAdapter.adapterItems
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewAttached()
    }

    override fun onPause() {
        super.onPause()
        viewDetached()
    }
    //endregion

    //region companion object
    companion object {

        val TAG = PostsFragment::class.java.simpleName

        fun newInstance() = PostsFragment()
    }
    //endregion

    //region PostsView implementation
    override fun viewAttached() {

        presenter.onAttach(this)
    }

    override fun viewDetached() {

        presenter.onDetach()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onPostsLoaded(comics: List<PostViewModel>, diffResult: DiffUtil.DiffResult?) {

        postsAdapter.adapterItems.clear()
        postsAdapter.adapterItems.addAll(comics)
        diffResult?.dispatchUpdatesTo(postsAdapter)
    }
    //endregion

}// Required empty public constructor
