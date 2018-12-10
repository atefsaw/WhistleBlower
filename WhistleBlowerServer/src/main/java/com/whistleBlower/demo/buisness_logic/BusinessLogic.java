package com.whistleBlower.demo.buisness_logic;

import com.whistleBlower.demo.buisness_logic.model.Group;
import com.whistleBlower.demo.buisness_logic.model.Message;
import com.whistleBlower.demo.buisness_logic.model.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)

public class BusinessLogic {

    private List<Message> messages;
    private List<Group> groups;
    private List<User> users;

    public BusinessLogic() {
        messages = new ArrayList<>();
        groups = new ArrayList<>();
        users = new ArrayList<>();
    }

    public List<User> getUsers(){
        return users;
    }

    public void createUser(User user){
        users.add(user);
    }

    public void createGroup(Group group){
        groups.add(group);
        users.forEach(user -> sendGroupToUser(group, user));
    }

    public void sendMessage(Message message){
        messages.add(message);
        Group group = message.getGroup();
        List<User> users = group.getUsers();
        users.forEach(user -> sendMessageToUser(user, message));
    }

    public List<Group> getGroups(){
        return groups;
    }

    private void sendGroupToUser(Group group, User user){ // TODO finish this function
        final String uri = "http://localhost:8027/";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "key=IAFUIASXOASCNI#R*U&FDJAS(");
        HttpEntity<Group> request = new HttpEntity<>(group, headers);

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(uri, request, String.class);
    }


    private void sendMessageToUser(User user, Message message){  //  TODO finish this function
        final String uri = "http://localhost:8027/";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "key=IAFUIASXOASCNI#R*U&FDJAS(");

        HttpEntity<Message> request = new HttpEntity<>(message, headers);

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(uri, request, String.class);
        System.out.println(result);

    }





    }
