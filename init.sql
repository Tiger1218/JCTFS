CREATE TABLE IF NOT EXISTS problems (
	`problem_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`score` INT NOT NULL,
	`flag` VARCHAR(512) NOT NULL,
	`desc` VARCHAR(512) ,
	`attachment_id` INT 
);
CREATE TABLE IF NOT EXISTS records (
	`records_id` BIGINT PRIMARY KEY AUTO_INCREMENT,
	`problem_id` INT NOT NULL,
	`submit_flag` VARCHAR(512) NOT NULL,
	`user_id` INT NOT NULL,
	`submit_time` DATETIME NOT NULL
);
