package example.aleks.com.postapp.ui.posts


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import example.aleks.com.postapp.R
import example.aleks.com.postapp.models.PostViewModel
import example.aleks.com.postapp.ui.base.BaseFragment
import example.aleks.com.postapp.utils.RecyclerViewItemsSpaceDecoration
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [PostsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostsFragment : BaseFragment(), IPostsView {

    //region properties
    @Inject
    lateinit var presenterI: IPostsPresenter

    @Inject
    lateinit var postsAdapter: PostsAdapter

    @Inject
    lateinit var appCompatActivity: AppCompatActivity

    private lateinit var postsRecyclerView: RecyclerView
    private lateinit var loadingView: ProgressBar

    override val viewItems: List<PostViewModel>
        get() = postsAdapter.adapterItems
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        appCompatActivity.supportActionBar?.show()
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        appCompatActivity.supportActionBar?.setDisplayShowTitleEnabled(true)

        // Inflate the layout for this fragment
        val rootView = inflater!!.inflate(R.layout.fragment_posts, container, false)

        loadingView = rootView.findViewById(R.id.loadingIndicator)
        postsRecyclerView = rootView.findViewById(R.id.postsRecyclerView)

        val itemMargin = resources.getDimension(R.dimen.dimen_5dp).toInt()
        postsRecyclerView.layoutManager = LinearLayoutManager(context)
        postsRecyclerView.addItemDecoration(RecyclerViewItemsSpaceDecoration(0, itemMargin, 0, itemMargin))
        postsRecyclerView.adapter = postsAdapter

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
    //endregion

    //region companion object
    companion object {

        val TAG = PostsFragment::class.java.simpleName

        fun newInstance() = PostsFragment()
    }
    //endregion

    //region IPostsView implementation
    override fun viewAttached() {

        presenterI.onAttach(this)
    }

    override fun viewDetached() {

        presenterI.onDetach()
    }

    override fun showLoading() {

        loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {

        loadingView.visibility = View.GONE
    }

    override fun onPostsLoaded(comics: List<PostViewModel>, diffResult: DiffUtil.DiffResult?) {

        postsAdapter.adapterItems.clear()
        postsAdapter.adapterItems.addAll(comics)
        diffResult?.dispatchUpdatesTo(postsAdapter)
    }
    //endregion

}// Required empty public constructor
