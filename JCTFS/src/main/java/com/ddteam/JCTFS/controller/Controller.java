package com.ddteam.JCTFS.controller;

import java.util.concurrent.ExecutionException;

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
import com.ddteam.JCTFS.db.UserManage;
import com.ddteam.JCTFS.models.Problem;
import com.ddteam.JCTFS.models.User;
import com.ddteam.JCTFS.models.Record;
import com.ddteam.JCTFS.models.Result;

@RestController
public class Controller {
    @Autowired
    private ProblemManage problemManage;
    @Autowired
    private RecordsManage recordsManage;
    @Autowired
    private UserManage userManage;
    @GetMapping("/devtest")
    public String devtest(){
        return "Hello, world";
    }

    @RequestMapping(value = "/problemview", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Result<String>> problemview(String problem_id){
        // TODO: Exception Handle
        if(problem_id == null||"".equals(problem_id)){
            return ResponseEntity.ok(Result.error("问题ID不能为空",""));
        }
        try{

            Problem searched = problemManage.select(Integer.parseInt(problem_id));
            return ResponseEntity.ok(Result.ok("查询成功",JSON.toJSONString(searched)));
        }catch(Exception e){
            return ResponseEntity.ok(Result.error("不存在的问题",""));
        }
    }

    @RequestMapping(value = "/problemlist", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<String> problemlist(){
        int[] idlist = problemManage.viewall();
        JSONObject new_object = new JSONObject();
        new_object.put("problem_id_list", idlist);
        return ResponseEntity.ok(new_object.toString());
    }
    @RequestMapping(value = "/submit", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Result<String>> submit(String token, int problemID, String flag){
        try{
            Record new_record = new Record(problemID, User.token_to_id(token), flag);
            return ResponseEntity.ok(Result.error("提交成功",JSON.toJSONString(recordsManage.submit(new_record))));
        }catch(Exception e){
            // Result result=new Result(1,"登录失败","");
            // return ResponseEntity.ok(result);
            return ResponseEntity.ok(Result.error("无效提交请求",""));
        }
        
        // return ResponseEntity.ok(new_object.toString());
    }
    @PostMapping("/login")
    public ResponseEntity<Result> login(String username, String hashed_passwd, String salt){
        User user=new User();
        user.username=username;
        user.hashed_passwd=hashed_passwd;
        user.salt=salt;
        return user.login();
    }
    ResponseEntity<Result> register(String username, String email, String hashed_passwd, String salt){
        User user=new User();
        user.username=username;
        user.email=email;
        user.hashed_passwd=hashed_passwd;
        user.salt=salt;
        user.ID=userManage.count()+1;
        return user.register();
    }
    @RequestMapping(value = "/userview", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<Result<String>> userview(String username){
        try{
            if(username==null||"".equals(username)){
                // Result<String> result=new Result<String>(1,"请输入用户名","");
                // return ResponseEntity.ok(result);
                return ResponseEntity.ok(Result.error("请输入用户名",""));
            }
            User searched = userManage.select(username);
            // searched.hashed_passwd=searched.salt="";
            // Result<String> result=new Result<String>(0,"查询成功",JSON.toJSONString(searched.to_view_copy()));
            return ResponseEntity.ok(Result.ok("查询成功",JSON.toJSONString(searched.to_view_copy())));

        }catch(Exception e){
            return ResponseEntity.ok(Result.error("用户不存在",""));
        }
    }
}
