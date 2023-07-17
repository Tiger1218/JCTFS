package com.ddteam.JCTFS.models;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;

import com.ddteam.JCTFS.models.Result;
import com.ddteam.JCTFS.models.SolveRecord;
import java.util.Date;

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

	
	private static final long TOKEN_EXP = 24 * 60 * 60 * 1000;//过期时间
	public String signToken(){
        Date date = new Date(System.currentTimeMillis() + TOKEN_EXP);
        Algorithm algorithm = Algorithm.HMAC256(hashed_passwd);
        //附带username信息的token
        return JWT.create().withClaim("ID",ID).withExpiresAt(date).sign(algorithm);
    }
	User userview(){
		User res=this;
		res.hashed_passwd=res.salt="";
		return res;
	}
	// Options
	// boolean hidden, banned;
	// String affiliation;
}

