package com.fssa.pupdesk.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import com.fssa.pupdesk.model.Ticket;

public class EmailSender {
	public static void emailSender(Ticket ticket) {
		final String email = "pupdesks@gmail.com";
		final String password = "5PIgiDrov@T&ewRl6!Sw";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ticket.getTo()));
			message.setSubject(ticket.getSummary());
			message.setText("Subject: Helpdesk Support Ticket Created - Ticket #[" + ticket.getTicketId() + "]\r\n"
					+ "\r\n" + "Dear " + ticket.getFrom() + ",\r\n" + "\r\n"
					+ "Thank you for reaching out to our helpdesk. Your request has been received and a support ticket has been generated for your issue.\r\n"
					+ "\r\n" + "Ticket Number: #[" + ticket.getTicketId() + "]\r\n" + "Date: " + ticket.getCreatedTime()
					+ "+\r\n" + "\r\n"
					+ "Our team is dedicated to resolving your issue as efficiently as possible. Please provide us with the following details to assist us in understanding your concern better:\r\n"
					+ "\r\n" + "- Brief Description of the Issue:\r\n"
					+ "- Any Relevant Screenshots or Error Messages:\r\n" + "\r\n"
					+ "You can reply directly to this email with the requested details. Our support staff will review your ticket and begin investigating your issue promptly.\r\n"
					+ "\r\n"
					+ "For future reference, you can always track the status of your ticket by logging into our helpdesk portal using the following link: [Helpdesk Portal URL].\r\n"
					+ "\r\n"
					+ "If you have any urgent inquiries or need immediate assistance, please don't hesitate to contact our helpdesk hotline at [Support Phone Number].\r\n"
					+ "\r\n"
					+ "Thank you for giving us the opportunity to assist you. We value your business and look forward to resolving your issue to your satisfaction.\r\n"
					+ "\r\n" + "Best regards,\r\n" + "Pupdesk Team\r\n");

			Transport.send(message);

			System.out.println("Email sent successfully.");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	public static void main(String[] args) {
		Ticket ticket = new Ticket("bhirahatees.periysamy@fssa.freshworks.com", "gowtham.sathyamoorthy@fssa.freshworks.com", "I have a find bugs in your code", "High", "Pending", "While Testing I find the bugs in you code");
		emailSender(ticket);
	}
}
