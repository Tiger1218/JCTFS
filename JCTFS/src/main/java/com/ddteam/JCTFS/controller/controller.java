package com.ddteam.JCTFS.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ddteam.JCTFS.db.ProblemManage;
import com.ddteam.JCTFS.db.UserManage;
import com.ddteam.JCTFS.models.Result;
import com.ddteam.JCTFS.models.User;

@RestController
public class controller {
    @Autowired
    private ProblemManage problemManage;
    @Autowired
    private UserManage userManage;
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
    @PostMapping("/login")
    public ResponseEntity<Result> login(String username, String hashed_passwd, String salt){
        try {
            User user=new User();
            user.username=username;
            user.hashed_passwd=hashed_passwd;
            user.salt=salt;
            Result result;
            if(userManage.select(user)==null){
                result=new Result(1,"用户名或密码错误","");
            }
            result=new Result<String>(0,"登录成功",user.signToken());
            return ResponseEntity<result>;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch(Exception e){
            
        }
        Result result=new Result(1,"登录失败","");
        return ResponseEntity.ok(result);
    }
    ResponseEntity<Result> register(String username, String email, String hashed_passwd, String salt){
        try {
            User user=new User();
            user.username=username;
            
            Result<String> result;
            if(userManage.select(user)==null){
                user.email=email;
                user.hashed_passwd=hashed_passwd;
                user.salt=salt;
                user.ID=userManage.count()+1;
                result=new Result(0,"注册成功",user.signToken());
                userManage.add(user);
            }else{
                result=new Result(1,"用户已存在",user.signToken());
            }
            return ResponseEntity.ok(result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch(Exception e){
            
        }
        Result result=new Result(1,"注册失败","");
        return ResponseEntity.ok(result);
    }
}
