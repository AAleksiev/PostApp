package example.aleks.com.postapp.ui.posts.details

import example.aleks.com.postapp.mvp.BasePresenter

/**
 * Created by aleks on 06/01/2018.
 */
interface PostDetailsBasePresenter<in V : PostDetailsView> : BasePresenter<V> {

    fun getPost(postId: Int)
}