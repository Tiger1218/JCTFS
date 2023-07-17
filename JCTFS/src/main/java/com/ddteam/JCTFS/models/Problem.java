package com.ddteam.JCTFS.models;
import com.ddteam.JCTFS.models.Record;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.annotation.JSONField;
import com.ddteam.JCTFS.db.ProblemManage;
import com.ddteam.JCTFS.db.RecordsManage;

public class Problem {
    @Autowired
    static private ProblemManage problemManage;
    @Autowired
    static private RecordsManage recordsManage;
    public int problem_id;
    public int score;
    private String flag;
    public Record[] records;
    // @JSONField(serialize = false)
    // static public String problemlist(){
    //     String ret = "";
    //     for(int value:problemManage.viewall()){
    //         ret += Integer.toString(value);
    //     }
    //     return ret ;
    // }
    @JSONField(serialize = false)
    public void sync_with_database(){
        records = recordsManage.problem_sync(this.problem_id);
    }
    @JSONField(serialize = false)
    public String getflag(){
        return this.flag;
    }
}
