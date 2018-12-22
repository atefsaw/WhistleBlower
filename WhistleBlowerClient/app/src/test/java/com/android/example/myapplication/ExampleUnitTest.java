package com.android.example.myapplication;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void testCreateUser() {
        RestHandler restHandler = new RestHandler();
        User user = restHandler.createUser();
        assertEquals(user.getUserId(), 1);
    }

    @Test
    public void testCreateGroup() {
        RestHandler restHandler = new RestHandler();
        Group group = createGroup();
        restHandler.createGroup(group);
    }

    private Group createGroup(){
        return new Group(createUsers(), "testGroup");
    }

    private List<User> createUsers(){
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        return userList;
    }

}