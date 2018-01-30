package example.aleks.com.postapp.ui.posts.details.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import example.aleks.com.postapp.mvvm.BaseViewModel

/**
 * Created by aleks on 30/01/2018.
 */
class MVVMPostDetailsViewModel : ViewModel(), BaseViewModel {


    override val isLoading: LiveData<Boolean> = MutableLiveData<Boolean>()
}