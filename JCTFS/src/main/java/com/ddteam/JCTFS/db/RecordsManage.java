package com.ddteam.JCTFS.db;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ddteam.JCTFS.models.Problem;
import com.ddteam.JCTFS.models.Record;

@Repository
public interface RecordsManage {
    @Select("SELECT * FROM records WHERE problem_id=#{problem_id}")
    Record[] problem_sync(int problem_id);
    @Select("SELECT * FROM records WHERE user_id=#{user_id}")
    Record[] user_sync(int user_id);
    @Insert("INSERT INTO records (problem_id, user_id, submit_flag, submit_time) VALUES (#{problem_id}, #{user_id}, #{submit_flag}, #{submit_time})")
    int submit(Record record);
}



// public interface ProblemManage {
//     // @Insert("INSERT")
//     int add(Problem problem);
//     @Select("SELECT * FROM problems WHERE problem_id=#{problem_id}")
//     Problem select(int problem_id);
//     @Select("SELECT problem_id FROM problems")
//     int[] viewall();

// }
