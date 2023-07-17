# MySQL version et al

MySQL Version: ```mariadb from 11.0.2-MariaDB, client 15.2 for Linux (x86_64) using readline 5.1```. 

# Set-ups

```sql
CREATE USER 'jctfs'@'localhost' IDENTIFIED BY 'jctfs';

GRANT INSERT, DELETE, SELECT ON jctfs.* TO 'jctfs'@'localhost' WITH GRANT OPTION;

CREATE TABLE IF NOT EXISTS problem (
	`problem_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`score` INT NOT NULL,
	`flag` VARCHAR(512) NOT NULL,
	`attachment_id` INT 
);

INSERT INTO problem 
	(score, flag, records_id, attachment_id)
	VALUES
	(500, "flag{test_flag}", -1, -1);

SELECT LAST_INSERT_ID();

CREATE TABLE IF NOT EXISTS records (
	`records_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`problem_id` INT NOT NULL,
	`submit_flag` VARCHAR(512) NOT NULL,
	`user_id` INT NOT NULL,
	`submit_time` DATETIME NOT NULL
);

```
