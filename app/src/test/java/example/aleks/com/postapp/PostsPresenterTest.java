package example.aleks.com.postapp;

import android.accounts.NetworkErrorException;
import android.support.v7.util.DiffUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import example.aleks.com.postapp.models.PostViewModel;
import example.aleks.com.postapp.rest.PostService;
import example.aleks.com.postapp.rest.models.Post;
import example.aleks.com.postapp.schedulers.SchedulersProvider;
import example.aleks.com.postapp.ui.posts.PostsPresenter;
import example.aleks.com.postapp.ui.posts.IPostsView;
import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

/**
 * Created by aleks on 06/01/2018
 */

public class PostsPresenterTest extends BaseTest {

    @Mock
    PostService postService;
    @Mock
    IPostsView postsView;
    @Mock
    SchedulersProvider testSchedulersProvider;
    @InjectMocks
    protected PostsPresenter postsPresenter;

    private final TestScheduler testScheduler = new TestScheduler();

    @Before
    public void setup() {

        MockitoAnnotations.initMocks( this );

        Mockito.doReturn(testScheduler).when(testSchedulersProvider).ioScheduler();
        Mockito.doReturn(testScheduler).when(testSchedulersProvider).mainScheduler();
        Mockito.doReturn(testScheduler).when(testSchedulersProvider).computationScheduler();

        Mockito.doAnswer( new Answer() {
            @Override
            public Object answer( InvocationOnMock invocation ) throws Throwable {

                postsPresenter.onAttach( postsView );
                return 1;
            }
        } ).when( postsView ).viewAttached();
    }

    @Test
    public void postsShouldBeLoadedIntoView() {

        final InOrder inOrder = Mockito.inOrder( postsView );

        Mockito.doAnswer( new Answer() {
            @Override
            public Object answer( InvocationOnMock invocation ) throws Throwable {

                final List<Post> posts = new ArrayList<>();
                posts.add(new Post(1, 1, "Post Title", "Post Body"));
                return Single.just(posts);
            }
        } ).when( postService ).getPosts();

        postsView.viewAttached();
        testScheduler.triggerActions();
        inOrder.verify( postsView, Mockito.times( 1 ) ).showLoading();
        inOrder.verify( postsView, Mockito.times( 1 ) ).hideLoading();
        inOrder.verify( postsView, Mockito.times( 1 ) ).onPostsLoaded( Mockito.<PostViewModel>anyList(), Mockito.any( DiffUtil.DiffResult.class ) );
    }

    @Test
    public void postsShouldNotBeLoadedIntoView() {

        final InOrder inOrder = Mockito.inOrder( postsView );

        Mockito.doAnswer( new Answer() {
            @Override
            public Object answer( InvocationOnMock invocation ) throws Throwable {

                return Single.error( new NetworkErrorException() );
            }
        } ).when( postService ).getPosts();

        postsView.viewAttached();
        testScheduler.triggerActions();
        inOrder.verify( postsView, Mockito.times( 1 ) ).showLoading();
        inOrder.verify( postsView, Mockito.times( 1 ) ).hideLoading();
        inOrder.verify( postsView, Mockito.times( 1 ) ).onError( Mockito.nullable( String.class ) );
    }
}
