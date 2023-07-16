package com.ddteam.JCTFS.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @GetMapping("/devtest")
    public String devtest(){
        return "Hello, world";
    }

    @RequestMapping(value = "/problemview", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity problemview(@RequestBody Req req)
}
