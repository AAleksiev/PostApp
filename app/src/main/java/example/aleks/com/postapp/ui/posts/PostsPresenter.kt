package example.aleks.com.postapp.ui.posts

import android.support.v7.util.DiffUtil
import example.aleks.com.postapp.models.PostViewModel
import example.aleks.com.postapp.rest.PostService
import example.aleks.com.postapp.schedulers.SchedulersProvider
import example.aleks.com.postapp.utils.DiffUtilCallback
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by aleks on 06/01/2018.
 */
class PostsPresenter @Inject constructor(private val postService: PostService, private val schedulersProvider: SchedulersProvider) : IPostsPresenter {

    //region properties
    private var IPostsView: IPostsView? = null
    private var disposable: Disposable? = null
    //endregion

    //region IBasePresenter implementation
    override fun onAttach(view: IPostsView) {

        dispose()
        IPostsView = view
        getPosts()
    }

    override fun onDetach() {

        dispose()
        IPostsView = null
    }

    override fun getPosts() {

        if (IPostsView?.viewItems?.isEmpty() == true) {

            IPostsView?.showLoading()

            disposable = getPostsFlowable()
                    .subscribeOn(schedulersProvider.ioScheduler())
                    .observeOn(schedulersProvider.mainScheduler())
                    .subscribe({ success ->

                        IPostsView?.hideLoading()
                        IPostsView?.onPostsLoaded(success.first, success.second)
                    }, { error ->

                        IPostsView?.hideLoading()
                        IPostsView?.onError(error.message)
                    })
        }
    }
    //endregion

    //region private methods
    private fun getPostsFlowable(): Flowable<Pair<List<PostViewModel>, DiffUtil.DiffResult?>> {

        val initialPair: Pair<List<PostViewModel>, DiffUtil.DiffResult?> = (IPostsView?.viewItems ?: listOf()) to null

        return postService.getPosts()
                .map { posts -> posts.map { post -> PostViewModel(postId = post.id, postTitle = post.title, userId = post.userId) } }
                .toFlowable()
                .scan(initialPair, { listDiffResultPair, currentForecastViewModel ->

                    val callback = DiffUtilCallback(listDiffResultPair.first, currentForecastViewModel)
                    val result = DiffUtil.calculateDiff(callback)
                    currentForecastViewModel to result
                })
                .filter { filter -> filter.second != null }
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