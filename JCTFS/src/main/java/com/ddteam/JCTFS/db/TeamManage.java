package com.ddteam.JCTFS.db;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.ddteam.JCTFS.models.User;

@Repository
public interface TeamManage {
    // @Insert("insert into user (ID,username,email,hashed_passwd,salt) values (#{ID},#{username},#{email},#{hashed_passwd},#{salt})")
    int add(User user);
    // @Select("SELECT * FROM user WHERE user_id=#{user_id} limit 1")
    User select(int user_id);
    // @Select("SELECT * FROM user limit 1")
    User select(User user);
    // @Select("SELECT * FROM user")
    int[] viewall();
    @Select("SELECT COUNT(*) FROM user")
    int count();
    @Select("SELECT problem_id FROM records WHERE user_id=#{user_id}")
    int[] solved(@Param("user_id") int user_id);
    // @Update("UPDATE user")
    // int update(int user_id);
}
