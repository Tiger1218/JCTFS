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
