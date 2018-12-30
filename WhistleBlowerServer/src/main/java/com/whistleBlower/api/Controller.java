package com.whistleBlower.api;

import com.whistleBlower.business_logic.BusinessLogic;
import com.whistleBlower.model.*;
import org.springframework.web.bind.annotation.*;

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
    public void createUser(@RequestBody User user){
        businessLogic.createUser(user);
    }

    @PostMapping("/createGroup")
    public void createGroup(@RequestBody Group group){
        businessLogic.createGroup(group);
    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody Message message){
        businessLogic.sendMessage(message);
    }


    @GetMapping("/pullGroups/{phoneNumber}")
    public List<Group> pullGroups(@PathVariable String phoneNumber){
        return businessLogic.pullGroupsForUser(phoneNumber);
    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return businessLogic.getUsers();
    }

    @GetMapping("/getGroups")
    public List<Group> getGroups() {
        return businessLogic.getGroups();
    }

    @GetMapping("/pullMessagesForGroup/{groupId}/{phoneNumber}")
    public List<Message> pullMessagesForGroup(@PathVariable int groupId, @PathVariable String phoneNumber){
        return businessLogic.pullMessagesForGroup(groupId, phoneNumber);
    }

    @GetMapping("/pullLastMessages/{phoneNumber}")
    public List<Message> pullLastMessages(@PathVariable String phoneNumber){
        return businessLogic.pullLastMessagesForUser(phoneNumber);
    }



}
