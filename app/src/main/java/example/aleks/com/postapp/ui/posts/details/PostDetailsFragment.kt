package example.aleks.com.postapp.ui.posts.details


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import example.aleks.com.postapp.R
import example.aleks.com.postapp.models.PostDetailsViewModel
import example.aleks.com.postapp.ui.base.BaseFragment
import example.aleks.com.postapp.ui.posts.PostsFragment
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [PostDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostDetailsFragment : BaseFragment(), PostDetailsView {

    //region properties
    @Inject
    lateinit var presenter: PostDetailsPresenter
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

    override fun onPostLoaded(post: PostDetailsViewModel) {

    }
    //endregion

}// Required empty public constructor
