package com.ddteam.JCTFS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import com.ddteam.JCTFS.db.ProblemManage;
import com.ddteam.JCTFS.db.RecordsManage;
import com.ddteam.JCTFS.models.Problem;
import com.ddteam.JCTFS.models.User;
import com.ddteam.JCTFS.models.Record;

@RestController
public class Controller {
    @Autowired
    private ProblemManage problemManage;
    @Autowired
    private RecordsManage recordsManage;
    @GetMapping("/devtest")
    public String devtest(){
        return "Hello, world";
    }

    @RequestMapping(value = "/problemview", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<String> problemview(String problem_id){
        // TODO: Exception Handle
        Problem searched = problemManage.select(Integer.parseInt(problem_id));
        return ResponseEntity.ok(JSON.toJSONString(searched));
    }

    @RequestMapping(value = "/problemlist", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<String> problemlist(){
        int[] idlist = problemManage.viewall();
        JSONObject new_object = new JSONObject();
        new_object.put("problem_id_list", idlist);
        return ResponseEntity.ok(new_object.toString());
    }
    @RequestMapping(value = "/submit", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<String> submit(String token, int problemID, String flag){
        Record new_record = new Record(problemID, User.token_to_id(token), flag);
        return ResponseEntity.ok(JSON.toJSONString(recordsManage.submit(new_record)));
        // return ResponseEntity.ok(new_object.toString());
    }
}
