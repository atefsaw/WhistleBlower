package com.whistleBlower.api;

import com.whistleBlower.business_logic.BusinessLogic;
import com.whistleBlower.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    private BusinessLogic businessLogic;

    public Controller(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }


    @GetMapping("/")
    public String test() {
        return "Hello World!";
    }

    @GetMapping("/createUser")
    public User createUser(){
        return businessLogic.createUser();
    }

    @PostMapping("/createGroup")
    public void createGroup(Group group){
        businessLogic.createGroup(group);
    }

    @PostMapping("/sendMessage")
    public void sendMessage(Message message){
        businessLogic.sendMessage(message);
    }

    @GetMapping("/pullMessages/{phoneNumber}")
    public MessageListWrapper pullMessages(@PathVariable int userId){
        return new MessageListWrapper(businessLogic.pullMessagesForUser(userId));
    }

    @GetMapping("/pullGroups/{phoneNumber}")
    public GroupListWrapper pullGroups(@PathVariable int userId){
        return new GroupListWrapper(businessLogic.pullGroupsForUser(userId));
    }


}
