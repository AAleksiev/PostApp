package example.aleks.com.postapp.di.components

import dagger.Component
import example.aleks.com.postapp.di.ActivityScope
import example.aleks.com.postapp.di.modules.ActivityModule
import example.aleks.com.postapp.di.modules.ViewModelModule
import example.aleks.com.postapp.ui.main.MainActivity
import example.aleks.com.postapp.ui.posts.PostsFragment
import example.aleks.com.postapp.ui.posts.details.mvp.PostDetailsFragment

/**
 * Created by aleks on 06/01/2018.
 */
@ActivityScope
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ActivityModule::class), (ViewModelModule::class)])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(postsFragment: PostsFragment)
    fun inject(postDetailsFragment: PostDetailsFragment)
}