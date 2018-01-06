package example.aleks.com.postapp.rest

import example.aleks.com.postapp.rest.models.Comment
import example.aleks.com.postapp.rest.models.Post
import example.aleks.com.postapp.rest.models.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by aleks on 06/01/2018.
 */
interface PostService {

    @GET("/posts")
    fun getPosts(): Single<List<Post>>

    @GET("/posts")
    fun getPost(@Query("id") postId: Int): Single<Post>

    @GET("/comments")
    fun getPostComments(@Query("postId") postId: Int): Single<List<Comment>>

    @GET("/users")
    fun getUser(@Query("id") userId: Int): Single<User>
}