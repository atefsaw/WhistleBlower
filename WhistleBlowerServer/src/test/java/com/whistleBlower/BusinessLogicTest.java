package com.whistleBlower;

import com.whistleBlower.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessLogicTest {


    @Test
    public void testPostRequest(){
//        String url = "http://localhost:8027/pullGroups/1";
//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<Group[]> responseEntity = restTemplate.getForEntity(url, Group[].class);
//        Group[] objects = responseEntity.getBody();
//        System.out.println(objects);
    }


    @Test
    public void testBusinessLogic(){
        User user = new User("3");
        final String uri = "http://172.29.123.134:8027/createUser";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject(uri, request, String.class);
        System.out.println("check");
    }
}
