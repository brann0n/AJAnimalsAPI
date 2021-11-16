package com.brandon.animalapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("HelloWorld")
public class HelloWorld {

    @GetMapping()
    public String helloWorld(){
        return "Hello World!";
    }
}
