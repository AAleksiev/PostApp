package example.aleks.com.postapp.ui.posts.details.mvp

import example.aleks.com.postapp.mvp.BasePresenter

/**
 * Created by aleks on 06/01/2018.
 */
interface PostDetailsBasePresenter : BasePresenter<PostDetailsView> {

    fun getPost(postId: Int)
}