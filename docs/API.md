```java
User user{
	int ID;
	boolean isAdmin;
	int TeamID; // ID of team he belongs to.
	int SolveProblemID[]; // ID of problems he solved;
	String nickname; 
	String username;
	String hashed_passwd, salt; // password which have been hashed by salt; (hex form)
	String email;
	int register(username, email, hashed_passwd, salt) -> ErrorHandler;
	int login(username, hashed_passwd, salt) -> ErrorHandler(success -> token);
	// Options
	// boolean hidden, banned;
	// String affiliation;
}
SolveRecord{
	int userid; 
	int problemid;
	Date solve_time;
	int solve_rank;
}

Attachment{
	String name;
	URL url;
	// Options
	// boolean legal(...) -> legal_or_not;
	boolean upload(token) -> upload_success_or_not;
}

Problem{
	int ID;
	String flag;
	SolveRecord solved_by_user [];
	int score;
	Attachmant attachment;
	boolean add(..., ) -> success_or_not;
	// Options
	// Docker ...
}

// Options
// Docker{...}

Team{
	int ID;
	String TeamName;
	String affiliation;
	int score;
	int cached_ranking;
}
```


```java
/login Input: String username / String email & String hashed_password
		Output: success_or_not + token

/regsiter Input: String username & String mailbox & String hashed_password
		Output: success_or_not

/userview Input: username
			Output: exclude hashed_password
/problemview Input: problemID
			Output: exclude flag
/problemlist Input: null
			Output: all problem ID
/submit	Input: token, problemID, flag, 
			Output: success_or_not
```


```java
/scoreboardview Input(null)
		-> Output Array [team_id] by ranking
/viewteam Input(int team_id)
		-> Output Everything about the team
/createteam Input(String token, String team_name, String hashed_team_passwd)
		Output: Int team_id;
/jointeam Input(String token, Int team_id, String hashed_team_passwd)
		Output: Result
```
