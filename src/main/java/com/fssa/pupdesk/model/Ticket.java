package com.fssa.pupdesk.model;

import java.security.SecureRandom;
import com.fssa.pupdesk.utils.CurrentTimeGenerator;

/**
 * Represents a ticket with various attributes.
 */
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

	/**
	 * Default constructor for creating an empty Ticket object.
	 */
	public Ticket() {

	}

	/**
	 * Constructor for creating a Ticket object with specified parameters.
	 *
	 * @param from        The sender of the ticket.
	 * @param to          The recipient of the ticket.
	 * @param summary     A brief summary of the ticket.
	 * @param priority    The priority level of the ticket.
	 * @param status      The status of the ticket.
	 * @param description A detailed description of the ticket.
	 */
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

	/**
	 * Getter method to retrieve the sender of the ticket.
	 *
	 * @return The sender of the ticket.
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Getter method to retrieve the recipient of the ticket.
	 *
	 * @return The recipient of the ticket.
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Getter method to retrieve the summary of the ticket.
	 *
	 * @return The summary of the ticket.
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * Getter method to retrieve the unique ticket ID.
	 *
	 * @return The ticket ID.
	 */
	public String getTicketId() {
		return ticketId;
	}

	/**
	 * Getter method to retrieve the priority level of the ticket.
	 *
	 * @return The priority level of the ticket.
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * Getter method to retrieve the status of the ticket.
	 *
	 * @return The status of the ticket.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Getter method to retrieve the detailed description of the ticket.
	 *
	 * @return The description of the ticket.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Getter method to retrieve the creation timestamp of the ticket.
	 *
	 * @return The creation timestamp of the ticket.
	 */
	public String getCreatedTime() {
		return createdTime;
	}

	/**
	 * Setter method to update the sender of the ticket.
	 *
	 * @param from The new sender of the ticket.
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * Setter method to update the recipient of the ticket.
	 *
	 * @param to The new recipient of the ticket.
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * Setter method to update the summary of the ticket.
	 *
	 * @param summary The new summary of the ticket.
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * Setter method to update the unique ticket ID.
	 *
	 * @param ticketId The new ticket ID.
	 */
	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	/**
	 * Setter method to update the priority level of the ticket.
	 *
	 * @param priority The new priority level of the ticket.
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * Setter method to update the status of the ticket.
	 *
	 * @param status The new status of the ticket.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Setter method to update the detailed description of the ticket.
	 *
	 * @param description The new description of the ticket.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Setter method to update the creation timestamp of the ticket.
	 *
	 * @param createdTime The new creation timestamp of the ticket.
	 */
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	/**
	 * Generates a random ID for the ticket using SecureRandom.
	 *
	 * @return The randomly generated ticket ID.
	 */
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

	/**
	 * Getter method to retrieve the closing description of the ticket.
	 *
	 * @return The closing description of the ticket.
	 */
	public String getClosingDescription() {
		return closingDescription;
	}

	/**
	 * Setter method to update the closing description of the ticket.
	 *
	 * @param closingDescription The new closing description of the ticket.
	 */
	public void setClosingDescription(String closingDescription) {
		this.closingDescription = closingDescription;
	}

	/**
	 * Returns a string representation of the Ticket object.
	 *
	 * @return A string containing ticket details.
	 */
	@Override
	public String toString() {
		return "Ticket [from=" + from + ", to=" + to + ", summary=" + summary + ", ticketId=" + ticketId + ", priority="
				+ priority + ", status=" + status + ", description=" + description + " , Created Time=" + createdTime
				+ "]";
	}
	
	
}
