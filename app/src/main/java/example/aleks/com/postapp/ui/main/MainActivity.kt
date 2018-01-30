package example.aleks.com.postapp.ui.main

import android.os.Bundle
import example.aleks.com.postapp.R
import example.aleks.com.postapp.ui.base.BaseActivity
import example.aleks.com.postapp.ui.posts.PostsFragment
import example.aleks.com.postapp.ui.posts.details.mvp.PostDetailsFragment
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    //region properties
    @Inject
    lateinit var presenter: MainBasePresenter
    //endregion

    //region Activity methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent.inject(this)
        viewAttached()

        if (savedInstanceState == null) {

            presenter.getPosts()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewDetached()
    }
    //endregion

    //region MainView implementation
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

    override fun showPosts() {

        var fragment = supportFragmentManager.findFragmentByTag(PostsFragment.TAG)
        if (fragment == null) {

            fragment = PostsFragment.newInstance()
        }

        showScreen(fragment, PostsFragment.TAG, false, true)
    }

    override fun showPostDetails(postId: Int) {

        var fragment = supportFragmentManager.findFragmentByTag(PostDetailsFragment.TAG)
        if (fragment == null) {

            fragment = PostDetailsFragment.newInstance(postId)
        }

        showScreen(fragment, PostDetailsFragment.TAG, true, true)
    }
    //endregion
}
