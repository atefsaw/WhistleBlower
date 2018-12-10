package com.whistleBlower.demo.api;

import com.whistleBlower.demo.buisness_logic.BusinessLogic;
import com.whistleBlower.demo.buisness_logic.model.Group;
import com.whistleBlower.demo.buisness_logic.model.Message;
import com.whistleBlower.demo.buisness_logic.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/getUsers")
    public List<User> getUsers(){
        return businessLogic.getUsers();
    }

    @GetMapping("/getGroups")
    public List<Group> getGroups(){
        return businessLogic.getGroups();
    }



}
