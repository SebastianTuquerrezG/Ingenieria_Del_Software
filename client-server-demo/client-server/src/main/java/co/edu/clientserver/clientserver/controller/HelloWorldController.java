package co.edu.clientserver.clientserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("/hello-world/{username}")
    public String helloWorld(@PathVariable String username) {
        return "Hello World with Spring Boot, welcome: " + username;
    }

    @GetMapping("/suma/{a}/{b}")
    public int suma(@PathVariable int a,@PathVariable int b){
        return a + b;
    }
}
