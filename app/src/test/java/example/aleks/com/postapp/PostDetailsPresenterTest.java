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

import java.util.ArrayList;
import java.util.List;

import example.aleks.com.postapp.models.PostDetailsViewModel;
import example.aleks.com.postapp.rest.PostService;
import example.aleks.com.postapp.rest.models.Comment;
import example.aleks.com.postapp.rest.models.Post;
import example.aleks.com.postapp.rest.models.User;
import example.aleks.com.postapp.schedulers.SchedulersProvider;
import example.aleks.com.postapp.ui.posts.details.mvp.PostDetailsPresenter;
import example.aleks.com.postapp.ui.posts.details.mvp.IPostDetailsView;
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
    IPostDetailsView postDetailsView;
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
    public void postDetailsShouldBeLoadedIntoView() {

        final InOrder inOrder = Mockito.inOrder( postDetailsView );

        Mockito.doAnswer( new Answer() {
            @Override
            public Object answer( InvocationOnMock invocation ) throws Throwable {

                final List<Post> posts = new ArrayList<>();
                posts.add(new Post(1, 1, "Post Title", "Post Body"));
                return Single.just(posts);
            }
        } ).when( postService ).getPost( Mockito.anyInt() );

        Mockito.doAnswer( new Answer() {
            @Override
            public Object answer( InvocationOnMock invocation ) throws Throwable {

                final List<User> users = new ArrayList<>();
                users.add(new User(1, "User Name"));
                return Single.just(users);
            }
        } ).when( postService ).getUser( Mockito.anyInt() );

        Mockito.doAnswer( new Answer() {
            @Override
            public Object answer( InvocationOnMock invocation ) throws Throwable {

                final List<Comment> comments = new ArrayList<>();
                comments.add(new Comment());
                return Single.just(comments);
            }
        } ).when( postService ).getPostComments( Mockito.anyInt() );

        postDetailsView.viewAttached();
        testScheduler.triggerActions();
        inOrder.verify( postDetailsView, Mockito.times( 1 ) ).showLoading();
        inOrder.verify( postDetailsView, Mockito.times( 1 ) ).hideLoading();
        inOrder.verify( postDetailsView, Mockito.times( 1 ) ).onPostLoaded( Mockito.nullable( PostDetailsViewModel.class ) );
    }

    @Test
    public void postDetailsShouldNotBeLoadedIntoView() {

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