package example.aleks.com.postapp.di.modules

import android.content.Context
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import example.aleks.com.postapp.avatar.AvatarProvider
import example.aleks.com.postapp.di.ActivityContext
import example.aleks.com.postapp.di.ActivityScope
import example.aleks.com.postapp.rest.PostService
import example.aleks.com.postapp.schedulers.SchedulersProvider
import example.aleks.com.postapp.ui.main.IMainPresenter
import example.aleks.com.postapp.ui.main.MainPresenter
import example.aleks.com.postapp.ui.posts.IPostsPresenter
import example.aleks.com.postapp.ui.posts.PostsAdapter
import example.aleks.com.postapp.ui.posts.PostsPresenter
import example.aleks.com.postapp.ui.posts.details.mvp.IPostDetailsPresenter
import example.aleks.com.postapp.ui.posts.details.mvp.PostDetailsPresenter


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
    fun providesMainBasePresenter(mainPresenter: MainPresenter): IMainPresenter = mainPresenter

    @Provides
    fun providesPostsAdapter(appCompatActivity: AppCompatActivity, mainPresenter: MainPresenter, avatarProvider: AvatarProvider) = PostsAdapter(appCompatActivity, mainPresenter, avatarProvider)

    @Provides
    fun providesPostsPresenter(postService: PostService, schedulersProvider: SchedulersProvider) = PostsPresenter(postService, schedulersProvider)

    @Provides
    fun providesPostsBasePresenter(postsPresenter: PostsPresenter): IPostsPresenter = postsPresenter

    @Provides
    fun providesPostDetailsPresenter(postService: PostService, schedulersProvider: SchedulersProvider) = PostDetailsPresenter(postService, schedulersProvider)

    @Provides
    fun providesPostDetailsBasePresenter(postDetailsPresenter: PostDetailsPresenter): IPostDetailsPresenter = postDetailsPresenter
}