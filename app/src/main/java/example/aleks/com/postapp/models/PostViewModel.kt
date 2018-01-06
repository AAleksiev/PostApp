package example.aleks.com.postapp.models

/**
 * Created by aleks on 06/01/2018.
 */
data class PostViewModel(val postId: Int, val postTitle: String) : UniqueId {

    override val uniqueId: String
        get() = postId.toString()

}