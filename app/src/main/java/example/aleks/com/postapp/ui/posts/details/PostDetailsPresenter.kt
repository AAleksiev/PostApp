package example.aleks.com.postapp.ui.posts.details

import example.aleks.com.postapp.models.PostDetailsViewModel
import example.aleks.com.postapp.rest.PostService
import example.aleks.com.postapp.schedulers.SchedulersProvider
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by aleks on 06/01/2018.
 */
class PostDetailsPresenter @Inject constructor(private val postService: PostService, private val schedulersProvider: SchedulersProvider) : PostDetailsBasePresenter {

    //region properties
    private var disposable: Disposable? = null
    private var postDetailsView: PostDetailsView? = null
    //endregion

    //region PostDetailsBasePresenter implementation
    override fun onAttach(view: PostDetailsView) {

        dispose()
        postDetailsView = view
    }

    override fun onDetach() {

        dispose()
        postDetailsView = null
    }

    override fun getPost(postId: Int) {

        postDetailsView?.showLoading()

        disposable = callPostService(postId)
                .subscribeOn(schedulersProvider.ioScheduler())
                .observeOn(schedulersProvider.mainScheduler())
                .subscribe({ success ->

                    postDetailsView?.hideLoading()
                    postDetailsView?.onPostLoaded(success)
                }, { error ->

                    postDetailsView?.hideLoading()
                    postDetailsView?.onError(error.message)
                })
    }
    //endregion

    //region private methods
    private fun callPostService(postId: Int): Single<PostDetailsViewModel> {

        return postService.getPost(postId)
                .map { post -> PostDetailsViewModel(postId = post.id, postTitle = post.title, userId = post.userId, postBody = post.body) }
    }
    //endregion

    //region dispose
    private fun dispose() {

        if (disposable?.isDisposed == false) {

            disposable?.dispose()
            disposable = null
        }
    }
    //endregion
}