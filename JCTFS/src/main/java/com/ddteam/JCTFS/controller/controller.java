package com.ddteam.JCTFS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @GetMapping("/devtest")
    public String devtest(){
        return "Hello, world";
    }
}
