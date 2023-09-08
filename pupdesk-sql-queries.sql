USE project;

DROP TABLE tickets;

CREATE TABLE IF NOT EXISTS users(
  firstname VARCHAR(16),
  lastname VARCHAR(16),
  email VARCHAR(255) PRIMARY KEY,
  teamcode VARCHAR(7),
  password VARCHAR(16),
  profile_image_url VARCHAR(255)
);

DROP TABLE users;

ALTER TABLE users
ADD islogged boolean;

DELETE FROM users where email = "bhirahatees@fssa.freshworks.com"; 

SELECT * FROM users;


INSERT INTO users (firstname, lastname, email, teamcode, password)
VALUES ('Bhirahatees', 'Periyasamy', 'bhirahatees.periysamy@fssa.freshworks.com', 'IQU6A1', 'Bhirahatees@123');	


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

DROP TABLE tickets;

SELECT * FROM tickets;		

CREATE TABLE IF NOT EXISTS comments(
comment_description VARCHAR(500),
comment_id INT AUTO_INCREMENT,
ticket_id VARCHAR(34),
createdate DATETIME,
FOREIGN KEY (ticketid) REFERENCES tickets(ticketid)
);



INSERT INTO tickets (fromEmail , toEmail , summary , ticketId , priority , status,description) VALUES(?,?,?,?,?,?,?); 



