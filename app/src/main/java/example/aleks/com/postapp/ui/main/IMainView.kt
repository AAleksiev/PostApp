package example.aleks.com.postapp.ui.main

import example.aleks.com.postapp.mvp.BaseView

/**
 * Created by aleks on 06/01/2018.
 */
interface IMainView : BaseView {

    fun showPosts()
    fun showPostDetails(postId: Int)
}