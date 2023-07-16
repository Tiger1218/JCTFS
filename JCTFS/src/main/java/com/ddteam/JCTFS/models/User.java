package com.ddteam.JCTFS.models;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;

import com.ddteam.JCTFS.models.Result;
import com.ddteam.JCTFS.models.SolveRecord;

public class User {
	int ID;
	boolean isAdmin;
	int TeamID; // ID of team he belongs to.
	SolveRecord[] records; // ID of problems he solved;
	String nickname; 
	String username;
	String hashed_passwd, salt; // password which have been hashed by salt; (hex form)
	String email;
	ResponseEntity<Result> register(String username, String email, String hashed_passwd, String salt){
        return ResponseEntity.status(200).body(null);
    }
	ResponseEntity<Result> login(String username, String hashed_passwd, String salt){
        return ResponseEntity.status(200).body(null);
    }
	// Options
	// boolean hidden, banned;
	// String affiliation;
}

