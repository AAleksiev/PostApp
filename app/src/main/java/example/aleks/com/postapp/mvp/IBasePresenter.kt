package example.aleks.com.postapp.mvp

/**
 * Created by aleks on 06/01/2018.
 */
interface IBasePresenter<in V : BaseView> {

    fun onAttach(view: V)

    fun onDetach()
}