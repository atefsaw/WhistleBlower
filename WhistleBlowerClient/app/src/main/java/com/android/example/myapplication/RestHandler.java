package com.android.example.myapplication;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.List;



public class RestHandler {

    static String HOST_IP = "132.64.34.241";


    public static void createUser(User user){
        final String uri = "http://" + HOST_IP + ":8027/createUser";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, request, String.class);
    }


    public static void createGroup(Group group){
        final String uri = "http://" + HOST_IP +  ":8027/createGroup";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<Group> request = new HttpEntity<>(group, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, request, String.class);
    }


    public static void sendMessages(Message message){
        final String uri = "http://" + HOST_IP +  ":8027/sendMessage";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<Message> request = new HttpEntity<>(message, headers);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, request, String.class);
    }


    public static List<Message> pullMessages(String userId){
        final String uri = "http://" + HOST_IP + ":8027/pullMessages/" + userId;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        RestTemplate restTemplate = new RestTemplate();
        MessageListWrapper messageListWrapper = restTemplate.getForObject(uri, MessageListWrapper.class, headers);
        return messageListWrapper.getMessageList();
    }


    public static List<Group> pullGroups(String phoneNumber){
        final String uri = "http://" + HOST_IP + ":8027/pullGroups/" + phoneNumber;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        RestTemplate restTemplate = new RestTemplate();
        GroupListWrapper groupListWrapper = restTemplate.getForObject(uri, GroupListWrapper.class, headers);
        return groupListWrapper.getGroupList();
    }
}