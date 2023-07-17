package com.ddteam.JCTFS.db;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.ddteam.JCTFS.models.Team;
import com.ddteam.JCTFS.models.User;

@Repository
public interface TeamManage {
    @Insert("INSERT INTO teams (caption_user_id, hashed_team_passwd, team_name) VALUES (#{caption_user_id}, #{hashed_team_passwd}, #{team_name})")
    int addteam(int caption_user_id, String hashed_team_passwd, String team_name);

    @Select("SELECT * FROM teams WHERE team_id=#{team_id}")
    Team selectteam_by_id(int team_id);

    // @Select("SELECT LAST_INSERT_ID()");
    // int last_insert_id();

    @Update("UPDATE user SET team_id=#{change_team_id} WHERE user_id=#{user_id}")
    int addteam(int change_team_id, int user_id);
}
