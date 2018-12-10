package com.android.example.myapplication;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by eladbendavid on 12/10/18.
 */

public class RestHandler {


     void createUser(String phoneNumber){
        final String uri = "http://localhost:8027/createUser";
        User currUser = new User(phoneNumber);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<User> request = new HttpEntity<>(currUser, headers);

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(uri, request, String.class);
        System.out.println(result);
    }


    private void createGroup(List<User> groupMembers, String name){
        final String uri = "http://localhost:8027/createGroup";
        Group currGroup = new Group(groupMembers, name);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<Group> request = new HttpEntity<>(currGroup, headers);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(uri, request, String.class);
        System.out.println(result);
    }


    private void sendMessages(String phoneNumber){
        final String uri = "http://localhost:8027/createUser";
        User currUser = new User(phoneNumber);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<User> request = new HttpEntity<>(currUser, headers);

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(uri, request, String.class);
        System.out.println(result);
    }


    private void pullMessages(String phoneNumber){
        final String uri = "http://localhost:8027/pullMessages/" + phoneNumber;
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json");
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);
    }


    public static void pullGroups(String phoneNumber){
        final String uri = "http://localhost:8027/pullGroups/" + phoneNumber;
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json");
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        System.out.println(result);
    }

}
