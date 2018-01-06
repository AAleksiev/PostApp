package example.aleks.com.postapp.ui.posts

import example.aleks.com.postapp.mvp.BasePresenter

/**
 * Created by aleks on 06/01/2018.
 */
interface PostsBasePresenter<in V : PostsView> : BasePresenter<V> {

    fun getPosts()
}