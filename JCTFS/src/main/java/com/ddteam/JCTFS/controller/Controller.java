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
import com.ddteam.JCTFS.db.TeamManage;
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
    @Autowired
    private TeamManage teamManage;
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

    @RequestMapping(value = "/createteam", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<String> createteam(String token, String team_name, String hashed_team_passwd){
        return ResponseEntity.ok(JSON.toJSONString(teamManage.addteam(User.token_to_id(token), hashed_team_passwd, team_name)));
    }

    @RequestMapping(value = "/viewteam", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<String> viewteam(int team_id){
        return ResponseEntity.ok(JSON.toJSONString(teamManage.selectteam_by_id(team_id)));
    }

    @RequestMapping(value = "/jointeam", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseEntity<String> jointeam(String token, int team_id, String hashed_team_passwd){
        if(hashed_team_passwd != teamManage.selectteam_by_id(team_id).hashed_team_passwd){
            return ResponseEntity.ok(JSON.toJSONString("...")); // TODO
        } else {
            return ResponseEntity.ok(JSON.toJSONString(teamManage.addteam(team_id, User.token_to_id(team_id))));
        }
    }
}
