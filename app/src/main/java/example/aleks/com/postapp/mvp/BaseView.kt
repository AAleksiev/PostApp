package example.aleks.com.postapp.mvp

import android.support.annotation.StringRes

/**
 * Created by aleks on 06/01/2018.
 */
interface BaseView {

    fun viewAttached()

    fun viewDetached()

    fun showLoading()

    fun hideLoading()

    fun onError(@StringRes resId: Int)

    fun onError(message: String?)

    fun showMessage(message: String?)

    fun showMessage(@StringRes resId: Int)

    fun isNetworkConnected(): Boolean

    fun hideKeyboard()
}