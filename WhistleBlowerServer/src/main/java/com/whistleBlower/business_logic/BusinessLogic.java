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
import java.util.stream.Collectors;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)

public class BusinessLogic {

    private List<Group> groups;
    private List<User> users;

    public BusinessLogic() {
        groups = new ArrayList<>();
        users = new ArrayList<>();
        createDefaultGroup();
    }

    private void createDefaultGroup() {
        User user1 = new User("1");
        User user2 = new User("2");
        users.add(user1);
        users.add(user2);
        List<String> userIdsList = new ArrayList<>();
        userIdsList.add("1");
        userIdsList.add("2");
        createGroup(new Group(userIdsList, "First"));
    }

    public void createUser(User newUser) {
        boolean userNotExist = users.stream().noneMatch(user -> user.getUserId().equals(newUser.getUserId()));
        if (userNotExist) {
            users.add(newUser);
        }
    }

    public void createGroup(Group group) {
        group.setId();
        groups.add(group);
        users.forEach(user -> addGroupToUser(group, user));
        createDefaultMessage(group);
    }

    public void sendMessage(Message message) {
        Optional<Group> group = groups.stream().filter(curr_group -> curr_group.getId() == message.getGroupId()).findAny();
        if (group.isPresent()) {
            List<String> users = group.get().getUserIds();
            users.stream().filter(userId -> !getUserById(userId).getUserId().equals(message.getSender().getUserId())).forEach(userId -> sendMessageToUser(getUserById(userId), message));
            group.get().setLastMessage(message);
        }
    }

    private void addGroupToUser(Group group, User user) {
        user.addGroup(group);
    }

    public List<Group> pullGroupsForUser(String userId) {
        Optional<User> user = users.stream().filter(curr_user -> curr_user.getUserId().equals(userId)).findAny();
        if (user.isPresent()) {
            return user.get().pullGroups();
        } else {
            return new ArrayList<>();
        }
    }

    private void sendMessageToUser(User user, Message message) {
        user.addMessage(message);
    }

    private void createDefaultMessage(Group group) {
        Message defaultMessage = new Message("Hello, This is anonymous... ", new User("0"), group.getId(), false);
        group.getUserIds().forEach(user -> getUserById(user).addMessage(defaultMessage));
        group.setLastMessage(defaultMessage);
    }


    private User getUserById(String userId) {
        return users.stream().filter(user -> user.getUserId().equals(userId)).findAny().orElse(null);
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Message> pullMessagesForGroup(int groupId, String userId) {
        Optional<User> user = users.stream().filter(curr_user -> curr_user.getUserId().equals(userId)).findAny();
        if (user.isPresent()) {
            List<Message> userMessages = user.get().pullMessages();
            List<Message> groupMessages = userMessages.stream()
                    .filter(message -> message.getGroupId() == groupId)
                    .collect(Collectors.toList());
            userMessages.removeIf(message -> message.getGroupId() == groupId);
            return groupMessages;
        } else {
            return new ArrayList<>();
        }
    }

    public List<Message> pullLastMessagesForUser(String userId) {
        Optional<User> user = users.stream().filter(curr_user -> curr_user.getUserId().equals(userId)).findAny();
        if (user.isPresent()) {
            List<Message> groupsLastMessages = groups.stream()
                    .filter(group -> group.getUserIds().contains(userId))
                    .collect(Collectors.toList())
                    .stream()
                    .map(Group::getLastMessage)
                    .collect(Collectors.toList());
            return groupsLastMessages;
        } else {
            return new ArrayList<>();
        }
    }




}
