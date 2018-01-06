package example.aleks.com.postapp.di.modules

import dagger.Module
import dagger.Provides
import example.aleks.com.postapp.PostApp
import example.aleks.com.postapp.schedulers.PostSchedulersProvider
import example.aleks.com.postapp.schedulers.SchedulersProvider
import javax.inject.Singleton

/**
 * Created by aleks on 06/01/2018.
 */
@Module
class ApplicationModule(private val postApp: PostApp) {

    @Provides
    fun providesApp() = postApp

    @Provides
    @Singleton
    fun providesPostSchedulersProvider() = PostSchedulersProvider()

    @Provides
    @Singleton
    fun providesSchedulersProvider(schedulersProvider: PostSchedulersProvider): SchedulersProvider = schedulersProvider
}