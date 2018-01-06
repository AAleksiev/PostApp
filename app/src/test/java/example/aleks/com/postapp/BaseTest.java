package example.aleks.com.postapp;

import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.List;

import example.aleks.com.postapp.rest.models.Post;

/**
 * Created by aleks on 06/01/2018
 */

public class BaseTest {

    //region fields
    protected static final List<Post> postList = new ArrayList<>();
    //endregion

    @BeforeClass
    public static void initClassData() {
    }
}
