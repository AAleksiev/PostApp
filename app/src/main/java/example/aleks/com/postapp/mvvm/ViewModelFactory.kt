package example.aleks.com.postapp.mvvm

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by aleks on 25/03/2018.
 */
class ViewModelFactory @Inject constructor(private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        val creator = creators[modelClass] ?:

        creators.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value

        ?: throw IllegalArgumentException("unknown model class " + modelClass)

        return try {

            creator.get() as T

        } catch (e: Exception) {

            throw RuntimeException(e)

        }
    }

}