package example.aleks.com.postapp.ui.posts

import example.aleks.com.postapp.mvp.IBasePresenter

/**
 * Created by aleks on 06/01/2018.
 */
interface IPostsPresenter : IBasePresenter<IPostsView> {

    fun getPosts()
}