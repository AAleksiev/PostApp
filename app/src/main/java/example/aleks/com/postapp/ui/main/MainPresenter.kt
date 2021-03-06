package example.aleks.com.postapp.ui.main

/**
 * Created by aleks on 06/01/2018.
 */
class MainPresenter : IMainPresenter {

    //region fields
    private var mainView: IMainView? = null
    //endregion

    //region MainMvpPresenter implementation
    override fun onAttach(view: IMainView) {

        mainView = view
    }

    override fun onDetach() {

        mainView = null
    }

    override fun getPosts() {

        mainView?.showPosts()
    }

    override fun getPostDetails(postId: Int) {

        mainView?.showPostDetails(postId)
    }
    //endregion
}