package com.tan_ds.gituserloader;

import android.content.Context;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static com.tan_ds.gituserloader.SomeGenerator.createSomeBundle;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Tan-DS on 6/23/2017.
 */
public class GitUserLoaderTest {

    public Bundle bundle;
    public GitUserLoader mGitUserLoader;
    public JSONObject mObject;
    public User mUser;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    public Context context;

    @Mock
    public GitUserLoader gitUserLoader;

    @Before
    public void setUp() throws Exception {
        mObject = new JSONObject();
        mUser = new User();
        bundle = createSomeBundle();
        String str = bundle.getString("login");
        long lg = bundle.getLong("id");
        try {
            mObject.put("id", lg);
            mObject.put("login", str);
        } catch (JSONException e){
            e.printStackTrace();
        }
        mUser.setmName(str);
        mUser.setmId(lg);
        mGitUserLoader = new GitUserLoader(context);

    }

    @Test
    public void loadingInfoFromGit() throws Exception {

       Mockito.when(gitUserLoader.LoadingInfoFromGit("qwe")).thenReturn(mObject);
       JSONObject object = gitUserLoader.LoadingInfoFromGit("qwe");
       Mockito.verify(gitUserLoader).LoadingInfoFromGit("qwe");

       // ВАЖНЫЙ ВОПРОC ИБО Я НЕ ПОНИМАЮ НЕМНОГО, ПОЧЕМУ ТАКОЙ ТЕСТ НЕ СРАБАТЫВАЕТ:
/*
        Mockito.when(gitUserLoader.LoadingInfoFromGit("qwe")).thenReturn(mObject);
        gitUserLoader.setLINK("qwe");
        User user= gitUserLoader.loadInBackground();
        Mockito.verify(gitUserLoader).LoadingInfoFromGit("qwe");
        Mockito.verify(gitUserLoader).JsonToUserParser(mObject);*/

        /*
        ведь, вроде как. я в третьей строке делаю якобы загрузку, внутри которой он должен
        вызвать метод LoadingInfoFromGit и парсить внутри. вот
        Можно, пожалуйста, объяснить? а то я что-то запутался слегка.
        */

    }

    @Test
    public void jsonToUserParser() throws Exception {
        User userActual = mGitUserLoader.JsonToUserParser(mObject);
        assertThat(userActual, is(mUser));
    }

}