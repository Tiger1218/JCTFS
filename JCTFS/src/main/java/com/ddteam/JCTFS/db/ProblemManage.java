package com.ddteam.JCTFS.db;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.ddteam.JCTFS.models.Problem;

@Repository
public interface ProblemManage {
    // @Insert("INSERT")
    // int add(Problem problem);
    @Select("SELECT * FROM problems WHERE problem_id=#{problem_id}")
    Problem select(int problem_id);
    @Select("SELECT problem_id FROM problems")
    int[] viewall();
    @Insert("INSERT INTO problems (score, desc, flag) VALUES (#{score}, #{desc}, #{flag})")
    int add(Problem problem);

}
