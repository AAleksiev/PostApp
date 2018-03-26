package example.aleks.com.postapp.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import example.aleks.com.postapp.mvvm.BaseViewModel

/**
 * Created by aleks on 25/03/2018.
 */
class MainViewModel : ViewModel(), BaseViewModel{

    override val isLoading: LiveData<Boolean>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}