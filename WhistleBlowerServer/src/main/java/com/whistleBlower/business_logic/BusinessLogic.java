package com.whistleBlower.business_logic;

import com.whistleBlower.model.Group;
import com.whistleBlower.model.Message;
import com.whistleBlower.model.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public User createUser(){
        User user = new User();
        users.add(user);
        return user;
    }

    public void createGroup(Group group){
        groups.add(group);
        users.forEach(user -> addGroupToUser(group, user));
    }

    public void sendMessage(Message message){
        messages.add(message);
        Group group = message.getGroup();
        List<User> users = group.getUsers();
        users.forEach(user -> sendMessageToUser(user, message));
    }

    private void addGroupToUser(Group group, User user){
        user.addGroup(group);
    }

    public List<Group> pullGroupsForUser(int userId){
        Optional<User> user = users.stream().filter(curr_user -> curr_user.getUserId() == userId).findAny();
        if (user.isPresent()){
            return user.get().pullGroups();
        } else {
            return new ArrayList<>();
        }
    }

    private void sendMessageToUser(User user, Message message){
        user.addMessage(message);
    }

    public List<Message> pullMessagesForUser(int userId){
        Optional<User> user = users.stream().filter(curr_user -> curr_user.getUserId() == userId).findAny();
        if (user.isPresent()){
            return user.get().pullMessages();
        } else {
            return new ArrayList<>();
        }
    }


    }
