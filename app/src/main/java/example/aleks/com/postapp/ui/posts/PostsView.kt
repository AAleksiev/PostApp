package example.aleks.com.postapp.ui.posts

import android.support.v7.util.DiffUtil
import example.aleks.com.postapp.models.PostViewModel
import example.aleks.com.postapp.mvp.BaseView

/**
 * Created by aleks on 06/01/2018.
 */
interface PostsView : BaseView {

    val viewItems: List<PostViewModel>
    fun onPostsLoaded(comics: List<PostViewModel>, diffResult: DiffUtil.DiffResult?)
}