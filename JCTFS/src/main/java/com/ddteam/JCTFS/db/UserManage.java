package com.ddteam.JCTFS.db;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.ddteam.JCTFS.models.User;

@Repository
public interface UserManage {
    @Insert("INSERT INTO users (user_id,username,email,hashed_passwd,salt) VALUES (#{ID},#{username},#{email},#{hashed_passwd},#{salt})")
    int add(User user);
    // @Select("SELECT * FROM users WHERE user_id=#{user_id} limit 1")
    User select(int user_id);
    // @Select("SELECT * FROM users limit 1")
    User select(User user);
    // @Select("SELECT * FROM users WHERE username=#{username} limit 1")
    User select(String username);
    // @Select("SELECT * FROM users")
    int[] viewall();
    @Select("SELECT COUNT(*) FROM users")
    int count();
    // @Select("SELECT problem_id FROM records WHERE user_id=#{user_id}")
    // int[] solved(@Param("user_id") int user_id);//已经实现了
    // @Update("UPDATE users")
    // int update(int user_id);
}
