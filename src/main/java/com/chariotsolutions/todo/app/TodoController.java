package com.chariotsolutions.todo.app;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    @RequestMapping("/")
    public String index() {
        return "My first Spring Boot based microservice!";
    }
}
