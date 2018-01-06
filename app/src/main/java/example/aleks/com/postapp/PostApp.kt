package example.aleks.com.postapp

import android.app.Application
import example.aleks.com.postapp.di.components.ApplicationComponent
import example.aleks.com.postapp.di.components.DaggerApplicationComponent
import example.aleks.com.postapp.di.modules.ApplicationModule
import example.aleks.com.postapp.di.modules.NetworkModule
import java.io.File

/**
 * Created by aleks on 06/01/2018.
 */
class PostApp : Application() {

    //region properties
    val applicationComponent: ApplicationComponent by lazy {

        // Setup Dagger object graph
        DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .networkModule(NetworkModule(File(cacheDir, "responses")))
                .build()
    }
    //endregion

    //region Application methods
    override fun onCreate() {

        super.onCreate()
        applicationComponent.inject(this)
    }
    //endregion
}