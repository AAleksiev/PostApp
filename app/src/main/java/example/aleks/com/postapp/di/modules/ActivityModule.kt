package example.aleks.com.postapp.di.modules

import dagger.Module
import dagger.Provides
import example.aleks.com.postapp.di.ActivityScope
import example.aleks.com.postapp.rest.PostService
import example.aleks.com.postapp.schedulers.SchedulersProvider
import example.aleks.com.postapp.ui.main.MainPresenter
import example.aleks.com.postapp.ui.posts.PostsAdapter
import example.aleks.com.postapp.ui.posts.PostsPresenter
import example.aleks.com.postapp.ui.posts.details.PostDetailsPresenter

/**
 * Created by aleks on 06/01/2018.
 */
@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun providesMainPresenter() = MainPresenter()

    @Provides
    fun providesPostsAdapter(mainPresenter: MainPresenter) = PostsAdapter(mainPresenter)

    @Provides
    fun providesPostsPresenter(postService: PostService, schedulersProvider: SchedulersProvider) = PostsPresenter(postService, schedulersProvider)

    @Provides
    fun providesPostDetailsPresenter(postService: PostService, schedulersProvider: SchedulersProvider) = PostDetailsPresenter(postService, schedulersProvider)
}