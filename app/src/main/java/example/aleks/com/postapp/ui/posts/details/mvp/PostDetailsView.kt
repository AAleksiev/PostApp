package example.aleks.com.postapp.ui.posts.details.mvp

import example.aleks.com.postapp.models.PostDetailsViewModel
import example.aleks.com.postapp.mvp.BaseView

/**
 * Created by aleks on 06/01/2018.
 */
interface PostDetailsView : BaseView {

    fun onPostLoaded(post: PostDetailsViewModel)
}