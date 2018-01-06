package example.aleks.com.postapp.ui.main

import example.aleks.com.postapp.mvp.BasePresenter

/**
 * Created by aleks on 06/01/2018.
 */
interface MainBasePresenter : BasePresenter<MainView> {

    fun getPosts()
    fun getPostDetails(postId: Int)
}