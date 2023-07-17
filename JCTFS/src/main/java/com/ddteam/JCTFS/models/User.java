package com.ddteam.JCTFS.models;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.ddteam.JCTFS.models.Result;
import com.ddteam.JCTFS.models.Record;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.alibaba.fastjson.annotation.JSONField;
import com.auth0.jwt.JWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import com.ddteam.JCTFS.db.UserManage;
import com.ddteam.JCTFS.db.RecordsManage;

public class User {
	@Autowired
    static private UserManage userManage;
    @Autowired
    static private RecordsManage recordsManage;
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
	public static int token_to_id(String token){
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("ID").asInt();
        }catch (JWTDecodeException e){
            return -1;
        }
    }
	public String signToken() throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException{
        Date date = new Date(System.currentTimeMillis() + TOKEN_EXP);
        //附带username信息的token
        return JWT.create().withClaim("ID",ID).withExpiresAt(date).sign(Algorithm.HMAC256(hashed_passwd));
    }
	public User userview(){
		User res=this;
		res.hashed_passwd=res.salt="";
		return res;
	}
	@JSONField(serialize = false)
    public void sync_with_database(){
        records = recordsManage.user_sync(this.ID);
    }
	// Options
	// boolean hidden, banned;
	// String affiliation;
}

