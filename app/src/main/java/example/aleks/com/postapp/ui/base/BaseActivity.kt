package example.aleks.com.postapp.ui.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import example.aleks.com.postapp.PostApp
import example.aleks.com.postapp.R
import example.aleks.com.postapp.di.components.ActivityComponent
import example.aleks.com.postapp.di.components.DaggerActivityComponent
import example.aleks.com.postapp.di.modules.ActivityModule
import example.aleks.com.postapp.mvp.BaseView

/**
 * Created by aleks on 06/01/2018.
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {

    //region properties
    val activityComponent: ActivityComponent by lazy {

        // Setup Dagger object graph
        DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as PostApp).applicationComponent)
                .build()
    }
    //endregion

    //region AppCompatActivity implementation
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        when (id) {

            android.R.id.home -> {

                if (supportFragmentManager.backStackEntryCount > 0) {

                    supportFragmentManager.popBackStackImmediate()

                    return true
                }
            }
            else -> {
            }
        }

        return super.onOptionsItemSelected(item)
    }
    //endregion

    //region show screen
    fun showScreen(content: Fragment,
                   contentTag: String,
                   addToBackStack: Boolean,
                   transitionContent: Boolean) {

        val ft = supportFragmentManager.beginTransaction()

        // Content area slide animation
        if (transitionContent) {

            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        }

        ft.replace(R.id.placeholder_content, content, contentTag)

        if (addToBackStack) {
            ft.addToBackStack(contentTag + System.identityHashCode(content).toString())
        }

        ft.commitAllowingStateLoss()
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

    override fun isNetworkConnected() = true

    override fun hideKeyboard() {

    }
    //endregion
}