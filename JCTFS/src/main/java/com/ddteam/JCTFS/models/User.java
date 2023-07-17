package com.ddteam.JCTFS.models;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;

import com.ddteam.JCTFS.models.Result;
import com.ddteam.JCTFS.models.Record;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.JWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class User {
	public int ID;
	boolean isAdmin;
	public int TeamID; // ID of team he belongs to.
	Record[] records; // ID of problems he solved;
	public String nickname; 
	public String username;
	public String hashed_passwd, salt; // password which have been hashed by salt; (hex form)
	public String email;
	ResponseEntity<Result> register(String username, String email, String hashed_passwd, String salt){
        return ResponseEntity.status(200).body(null);
    }
	ResponseEntity<Result> login(String username, String hashed_passwd, String salt){
        return ResponseEntity.status(200).body(null);
    }

	
	private static final long TOKEN_EXP = 24 * 60 * 60 * 1000;//过期时间
	public String signToken() throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException{
        Date date = new Date(System.currentTimeMillis() + TOKEN_EXP);
        //附带username信息的token
        return JWT.create().withClaim("ID",ID).withExpiresAt(date).sign(Algorithm.HMAC256(hashed_passwd));
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

