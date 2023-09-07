USE project;

USE bhirahatees_periyasamy_corejava_project;

DROP TABLE tickets;

CREATE TABLE IF NOT EXISTS users(
  firstname VARCHAR(16),
  lastname VARCHAR(16),
  email VARCHAR(255) PRIMARY KEY,
  teamcode VARCHAR(7),
  password VARCHAR(16)
);

ALTER TABLE users
ADD islogged boolean;

DELETE FROM users where email = "bhirahatees@fssa.freshworks.com"; 

SELECT * FROM users;	


INSERT INTO users (firstname, lastname, email, teamcode, password)
VALUES ('Arunkumar', 'Dhanraj', 'arunkumar.dhanraj@fssa.freshworks.com', 'IQU6A1', 'Arun@123');	


CREATE TABLE IF NOT EXISTS tickets(
  from_email VARCHAR(255),
  to_email VARCHAR(255),
  summary VARCHAR(255),
  ticketid VARCHAR(34) PRIMARY KEY,
  created_at DATETIME,
  priority VARCHAR(16),
  closing_description VARCHAR(1000),
  status VARCHAR(16),
  description VARCHAR(1000),
  FOREIGN KEY (from_email) REFERENCES users(email)
);

ALTER TABLE tickets
DROP FOREIGN KEY fk_tickets_from_email;

DROP TABLE tickets;

ALTER TABLE users
ADD profile_image_url VARCHAR(300);

SELECT * FROM tickets;		

INSERT INTO users (profile_image_url)
SELECT 'https://cdn-icons-png.flaticon.com/512/64/64572.png'
FROM users;

UPDATE users
SET profile_image_url = 'https://cdn-icons-png.flaticon.com/512/64/64572.png'
WHERE profile_image_url = null;





CREATE TABLE IF NOT EXISTS comments(
  comment_id INT AUTO_INCREMENT,
  ticketid VARCHAR(34),
  createdat DATETIME,
  createdby VARCHAR(20),
  ticket_comment VARCHAR(200),
  FOREIGN KEY(ticketid) REFERENCES tickets(ticketid)
);

ALTER TABLE tickets 
RENAME COLUMN ticketid TO ticket_id;





