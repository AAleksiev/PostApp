package example.aleks.com.postapp;

import android.accounts.NetworkErrorException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import example.aleks.com.postapp.models.PostDetailsViewModel;
import example.aleks.com.postapp.rest.PostService;
import example.aleks.com.postapp.rest.models.Post;
import example.aleks.com.postapp.schedulers.SchedulersProvider;
import example.aleks.com.postapp.ui.posts.details.PostDetailsPresenter;
import example.aleks.com.postapp.ui.posts.details.PostDetailsView;
import io.reactivex.Single;
import io.reactivex.schedulers.TestScheduler;

/**
 * Created by aleks on 06/01/2018
 */

public class PostDetailsPresenterTest extends BaseTest {

    @Mock
    PostService postService;
    @Mock
    SchedulersProvider testSchedulersProvider;
    @Mock
    PostDetailsView postDetailsView;
    @InjectMocks
    protected PostDetailsPresenter postDetailsPresenter;

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

                postDetailsPresenter.onAttach( postDetailsView );
                postDetailsPresenter.getPost( 1 );
                return 1;
            }
        } ).when( postDetailsView ).viewAttached();
    }

    @Test
    public void comicsShouldBeLoadedIntoView() {

        final InOrder inOrder = Mockito.inOrder( postDetailsView );

        Mockito.doAnswer( new Answer() {
            @Override
            public Object answer( InvocationOnMock invocation ) throws Throwable {

                return Single.just( new Post(1, 1, "Post Title", "Post Body"));
            }
        } ).when( postService ).getPost( Mockito.anyInt() );

        postDetailsView.viewAttached();
        testScheduler.triggerActions();
        inOrder.verify( postDetailsView, Mockito.times( 1 ) ).showLoading();
        inOrder.verify( postDetailsView, Mockito.times( 1 ) ).hideLoading();
        inOrder.verify( postDetailsView, Mockito.times( 1 ) ).onPostLoaded( Mockito.nullable( PostDetailsViewModel.class ) );
    }

    @Test
    public void comicsShouldNotBeLoadedIntoView() {

        final InOrder inOrder = Mockito.inOrder( postDetailsView );

        Mockito.doAnswer( new Answer() {
            @Override
            public Object answer( InvocationOnMock invocation ) throws Throwable {

                return Single.error( new NetworkErrorException() );
            }
        } ).when( postService ).getPost( Mockito.anyInt() );

        postDetailsView.viewAttached();
        testScheduler.triggerActions();
        inOrder.verify( postDetailsView, Mockito.times( 1 ) ).showLoading();
        inOrder.verify( postDetailsView, Mockito.times( 1 ) ).hideLoading();
        inOrder.verify( postDetailsView, Mockito.times( 1 ) ).onError( Mockito.nullable( String.class ) );
    }
}