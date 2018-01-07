package example.aleks.com.postapp.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import example.aleks.com.postapp.avatar.AvatarProvider
import example.aleks.com.postapp.di.ActivityScope
import example.aleks.com.postapp.rest.PostService
import example.aleks.com.postapp.schedulers.SchedulersProvider
import example.aleks.com.postapp.ui.main.MainBasePresenter
import example.aleks.com.postapp.ui.main.MainPresenter
import example.aleks.com.postapp.ui.posts.PostsAdapter
import example.aleks.com.postapp.ui.posts.PostsBasePresenter
import example.aleks.com.postapp.ui.posts.PostsPresenter
import example.aleks.com.postapp.ui.posts.details.PostDetailsBasePresenter
import example.aleks.com.postapp.ui.posts.details.PostDetailsPresenter
import android.support.v7.app.AppCompatActivity
import example.aleks.com.postapp.di.ActivityContext





/**
 * Created by aleks on 06/01/2018.
 */
@Module
class ActivityModule(private val appCompatActivity: AppCompatActivity) {

    @Provides
    @ActivityContext
    fun provideContext(): Context = appCompatActivity


    @Provides
    fun provideActivity() = appCompatActivity

    @Provides
    @ActivityScope
    fun providesMainPresenter() = MainPresenter()

    @Provides
    @ActivityScope
    fun providesMainBasePresenter(mainPresenter: MainPresenter): MainBasePresenter = mainPresenter

    @Provides
    fun providesPostsAdapter(appCompatActivity: AppCompatActivity, mainPresenter: MainPresenter, avatarProvider: AvatarProvider) = PostsAdapter(appCompatActivity, mainPresenter, avatarProvider)

    @Provides
    fun providesPostsPresenter(postService: PostService, schedulersProvider: SchedulersProvider) = PostsPresenter(postService, schedulersProvider)

    @Provides
    fun providesPostsBasePresenter(postsPresenter: PostsPresenter): PostsBasePresenter = postsPresenter

    @Provides
    fun providesPostDetailsPresenter(postService: PostService, schedulersProvider: SchedulersProvider) = PostDetailsPresenter(postService, schedulersProvider)

    @Provides
    fun providesPostDetailsBasePresenter(postDetailsPresenter: PostDetailsPresenter): PostDetailsBasePresenter = postDetailsPresenter
}