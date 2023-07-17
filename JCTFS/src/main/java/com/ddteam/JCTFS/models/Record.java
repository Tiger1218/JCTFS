package com.ddteam.JCTFS.models;
import java.util.Date;

public class Record {
    int records_id;
    int problem_id;
    int user_id;
    String submit_flag;
    Date submit_time;
    public Record(int sproblem_id, int suser_id, String ssubmit_flag){
        problem_id = sproblem_id;
        user_id = suser_id;
        submit_flag = ssubmit_flag;
        submit_time = new Date();
    }
    // public Result submit()
    // static  
}
