package com.fssa.pupdesk.model;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {

	private String from;
	private String to;
	private String summary;
	private String ticketId;
	private String priority;
	private String status;
	private String description;
	private String createdTime;

	public Ticket() {

	}

	public Ticket(String from, String to, String summary, String priority, String status, String description) {
		super();
		this.from = from;
		this.to = to;
		this.summary = summary;
		this.ticketId = generateRandomId();
		this.priority = priority;
		this.status = status;
		this.description = description;
		this.createdTime = getCurrentDateTime();
	}

	public Ticket(String from, String to, String summary, String ticketId, String priority, String status,
			String description, String createdTime) {
		super();
		this.from = from;
		this.to = to;
		this.summary = summary;
		this.ticketId = ticketId;
		this.priority = priority;
		this.status = status;
		this.description = description;
		this.createdTime = createdTime;
	}

	public static String getCurrentDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return now.format(formatter);
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getSummary() {
		return summary;
	}

	public String getPriority() {
		return priority;
	}

	public String getStatus() {
		return status;
	}

	public String getTicketId() {
		return ticketId;
	}

	public String getDescription() {
		return description;
	}

	public String getCreateTime() {
		return createdTime;
	}

	public static String generateRandomId() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] randomBytes = new byte[7];
		secureRandom.nextBytes(randomBytes);
		StringBuilder result = new StringBuilder();
		for (byte b : randomBytes) {
			result.append(String.format("%02X", b));
		}
		return result.toString();
	}

	@Override
	public String toString() {
		return "Ticket [from=" + from + ", to=" + to + ", summary=" + summary + ", ticketId=" + ticketId + ", priority="
				+ priority + ", status=" + status + ", description=" + description + " , Created Time=" + createdTime
				+ "]";
	}

}
