package example.aleks.com.postapp.ui.posts.details.mvp

import example.aleks.com.postapp.mvp.IBasePresenter

/**
 * Created by aleks on 06/01/2018.
 */
interface IPostDetailsPresenter : IBasePresenter<IPostDetailsView> {

    fun getPost(postId: Int)
}