package com.jtm.feed;

import com.jtm.feed.data.service.PostServiceTest;
import com.jtm.feed.presenter.controller.PostControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PostServiceTest.class,
        PostControllerTest.class
})
public class FeedServiceTestSuite {}
