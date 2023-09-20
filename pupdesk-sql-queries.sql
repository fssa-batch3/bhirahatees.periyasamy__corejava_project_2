USE bhirahatees_periyasamy_corejava_project;

CREATE TABLE IF NOT EXISTS users(
  firstname VARCHAR(16),
  lastname VARCHAR(16),
  email VARCHAR(255) PRIMARY KEY,
  teamcode VARCHAR(7),
  password VARCHAR(16),
  profile_image_url VARCHAR(255)
);


CREATE TABLE IF NOT EXISTS tickets(
  from_email VARCHAR(255),
  to_email VARCHAR(255),
  summary VARCHAR(255),
  ticket_id VARCHAR(34) PRIMARY KEY,
  created_at DATETIME,
  priority VARCHAR(16),
  status VARCHAR(16),
  description VARCHAR(500),
  closing_description VARCHAR(500)
);

CREATE TABLE IF NOT EXISTS comments(
comment_id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(35),
email VARCHAR(255),
comment_description VARCHAR(350),
ticket_id VARCHAR(34),
is_edited BOOLEAN,
created_at DATETIME
);



DROP TABLE users;

DROP TABLE tickets;

SELECT * FROM users;

SELECT * FROM tickets;


SELECT * FROM comments;


