package example.aleks.com.postapp.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import example.aleks.com.postapp.di.ViewModelKey
import example.aleks.com.postapp.mvvm.ViewModelFactory
import example.aleks.com.postapp.ui.main.MainViewModel

/**
 * Created by aleks on 25/03/2018.
 */
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel
}