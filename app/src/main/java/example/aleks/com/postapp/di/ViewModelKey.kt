package example.aleks.com.postapp.di

import android.arch.lifecycle.ViewModel

import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by aleks on 25/03/2018.
 */

@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)