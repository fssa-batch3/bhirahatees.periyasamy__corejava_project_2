USE project;

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
VALUES ('Bhirahatees', 'Periyasamy', 'bhirahatees.periysamy@fssa.freshworks.com', 'IQU6A1', 'Bhirahatees@123');	


CREATE TABLE IF NOT EXISTS tickets(
  fromEmail VARCHAR(255),
  toEmail VARCHAR(255),
  summary VARCHAR(255),
  ticketid VARCHAR(34) PRIMARY KEY,
  createdate DATETIME,
  priority VARCHAR(16),
  status VARCHAR(16),
  description VARCHAR(500),
  FOREIGN KEY (fromEmail) REFERENCES users(email)
);

DROP TABLE tickets;

SELECT * FROM tickets;		





INSERT INTO tickets (fromEmail , toEmail , summary , ticketId , priority , status,description) VALUES(?,?,?,?,?,?,?); 



