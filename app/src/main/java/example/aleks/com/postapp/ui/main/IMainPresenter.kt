package example.aleks.com.postapp.ui.main

import example.aleks.com.postapp.mvp.IBasePresenter

/**
 * Created by aleks on 06/01/2018.
 */
interface IMainPresenter : IBasePresenter<IMainView> {

    fun getPosts()
    fun getPostDetails(postId: Int)
}