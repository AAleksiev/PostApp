package example.aleks.com.postapp.di.components

import dagger.Component
import example.aleks.com.postapp.PostApp
import example.aleks.com.postapp.avatar.AvatarProvider
import example.aleks.com.postapp.di.modules.ApplicationModule
import example.aleks.com.postapp.di.modules.NetworkModule
import example.aleks.com.postapp.rest.PostService
import example.aleks.com.postapp.schedulers.PostSchedulersProvider
import example.aleks.com.postapp.schedulers.SchedulersProvider
import javax.inject.Singleton

/**
 * Created by aleks on 06/01/2018.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class))
interface ApplicationComponent {

    fun inject(postApp: PostApp)
    fun postService(): PostService
    fun schedulersProvider(): SchedulersProvider
    fun avatarProvider(): AvatarProvider
}