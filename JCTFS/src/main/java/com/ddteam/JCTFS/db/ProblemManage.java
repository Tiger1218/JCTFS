package com.ddteam.JCTFS.db;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.ddteam.JCTFS.models.Problem;

public interface ProblemManage {
    // @Insert("INSERT")
    int add(Problem problem);
    @Select("SELECT ")
    Problem select(int problem_id);
}
