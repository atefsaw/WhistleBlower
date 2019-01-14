package com.android.example.myapplication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class RestHandler {

    static String HOST_IP = "172.29.123.134";
    static RestTemplate restTemplate = new RestTemplate();

    public static void createUser(User user) throws JsonProcessingException {
        final String uri = "http://" + HOST_IP + ":8027/createUser";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        final HttpEntity<User> request = new HttpEntity<>(user, headers);
        restTemplate = new RestTemplate();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    restTemplate.postForObject(uri, request, String.class);
                } catch (RestClientException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static void createGroup(Group group){
        final String uri = "http://" + HOST_IP +  ":8027/createGroup";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        final HttpEntity<Group> request = new HttpEntity<>(group, headers);
        restTemplate = new RestTemplate();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    restTemplate.postForObject(uri, request, String.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void sendMessages(Message message){
        final String uri = "http://" + HOST_IP +  ":8027/sendMessage";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        final HttpEntity<Message> request = new HttpEntity<>(message, headers);
        restTemplate = new RestTemplate();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    restTemplate.postForObject(uri, request, String.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static List<Message> pullMessages(String userId, int groupId){
        final String uri = "http://" + HOST_IP + ":8027/pullMessagesForGroup/" + groupId + "/" + userId;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        restTemplate = new RestTemplate();
        final List<List<Message>> messages = new ArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    String jsonOutput = restTemplate.getForObject(uri, String.class);
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Message>>(){}.getType();
                    messages.add((List) gson.fromJson(jsonOutput, listType));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return messages.get(0);
    }


    public static List<Group> pullGroups(String phoneNumber){
        final String uri = "http://" + HOST_IP + ":8027/pullGroups/" + phoneNumber;
        restTemplate = new RestTemplate();
        final List<List<Group>> groups = new ArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    String jsonOutput = restTemplate.getForObject(uri, String.class);
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Group>>(){}.getType();
                    groups.add((List) gson.fromJson(jsonOutput, listType));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return groups.get(0);
    }

    public static List<Message> pullLastGroupMessages(String userId){
        final String uri = "http://" + HOST_IP + ":8027/pullLastMessages/" + userId;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        restTemplate = new RestTemplate();
        final List<List<Message>> messages = new ArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    String jsonOutput = restTemplate.getForObject(uri, String.class);
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Message>>(){}.getType();
                    messages.add((List) gson.fromJson(jsonOutput, listType));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return messages.get(0);
    }

    public static List<User> getRegisteredUsers(){
        final String uri = "http://" + HOST_IP + ":8027/getUsers/";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        restTemplate = new RestTemplate();
        final List<List<User>> users = new ArrayList<>();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try  {
                    String jsonOutput = restTemplate.getForObject(uri, String.class);
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<User>>(){}.getType();
                    users.add((List) gson.fromJson(jsonOutput, listType));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return users.get(0);
    }



}
