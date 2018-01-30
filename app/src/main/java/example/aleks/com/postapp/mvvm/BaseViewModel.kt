package example.aleks.com.postapp.mvvm

import android.arch.lifecycle.LiveData

/**
 * Created by aleks on 30/01/2018.
 */
interface BaseViewModel {

    val isLoading: LiveData<Boolean>
}