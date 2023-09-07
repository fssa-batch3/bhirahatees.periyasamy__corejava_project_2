package com.fssa.pupdesk.model;

import java.security.SecureRandom;


import com.fssa.pupdesk.utils.CurrentTimeGenerator;

public class Ticket {

	private String from;
	private String to;
	private String summary;
	private String ticketId;
	private String priority;
	private String status;
	private String description;
	private String createdTime;
	private String closingDescription;

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
		this.createdTime = CurrentTimeGenerator.getCurrentDateTime();
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

	public String getTicketId() {
		return ticketId;
	}

	public String getPriority() {
		return priority;
	}

	public String getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
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

	public String getClosingDescription() {
		return closingDescription;
	}

	public void setClosingDescription(String closingDescription) {
		this.closingDescription = closingDescription;
	}

	@Override
	public String toString() {
		return "Ticket [from=" + from + ", to=" + to + ", summary=" + summary + ", ticketId=" + ticketId + ", priority="
				+ priority + ", status=" + status + ", description=" + description + " , Created Time=" + createdTime
				+ "]";
	}

}
