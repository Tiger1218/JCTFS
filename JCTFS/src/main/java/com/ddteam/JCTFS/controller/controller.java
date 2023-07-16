package com.ddteam.JCTFS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ddteam.JCTFS.db.ProblemManage;

@RestController
public class controller {
    @Autowired
    private ProblemManage problemManage;
    @GetMapping("/devtest")
    public String devtest(){
        return "Hello, world";
    }

    // @RequestMapping(value = "/problemview", method = {RequestMethod.POST, RequestMethod.GET})
    // public ResponseEntity problemview(String problem_id){

    // }

    @RequestMapping(value = "/problemlist", method = {RequestMethod.POST, RequestMethod.GET})
    public String problemlist(){
        String ret = "";
        for(int value:problemManage.viewall()){
            ret += Integer.toString(value);
        }
        return ret ;
    }
}
