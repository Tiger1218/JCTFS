package com.ddteam.JCTFS.models;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;

import com.ddteam.JCTFS.models.Result;
import com.ddteam.JCTFS.models.Record;

public class User {
	int ID;
	boolean isAdmin;
	int TeamID; // ID of team he belongs to.
	Record[] records; // ID of problems he solved;
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
	static public int token_to_id(String token){
		// TODO
		return 1;
	}
	// Options
	// boolean hidden, banned;
	// String affiliation;
}

