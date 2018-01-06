package example.aleks.com.postapp.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import example.aleks.com.postapp.di.components.ActivityComponent
import example.aleks.com.postapp.mvp.BaseView

/**
 * Created by aleks on 06/01/2018.
 */
abstract class BaseFragment : Fragment(), BaseView {

    //region properties
    protected val activityComponent: ActivityComponent
        get() = (activity as BaseActivity).activityComponent
    //endregion

    //region onCreate
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (activity !is BaseActivity) {

            throw ClassCastException("Hosting activity MUST inherit BaseActivity")
        }
    }
    //endregion

    //region BaseView implementation
    override fun onError(resId: Int) {
    }

    override fun onError(message: String?) {
    }

    override fun showMessage(message: String?) {
    }

    override fun showMessage(resId: Int) {
    }

    override fun isNetworkConnected(): Boolean = true

    override fun hideKeyboard() {
    }
    //endregion
}