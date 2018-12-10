package com.whistleBlower.api;

import com.whistleBlower.business_logic.BusinessLogic;
import com.whistleBlower.model.Group;
import com.whistleBlower.model.Message;
import com.whistleBlower.model.User;
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

    @PostMapping("/createUser")
    public void createUser(User user){
        businessLogic.createUser(user);
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
    public List<Message> pullMessages(@PathVariable String phoneNumber){
        return businessLogic.pullMessagesForUser(phoneNumber);
    }

    @GetMapping("/pullGroups/{phoneNumber}")
    public List<Group> pullGroups(@PathVariable String phoneNumber){
        return businessLogic.pullGroupsForUser(phoneNumber);
    }


}
