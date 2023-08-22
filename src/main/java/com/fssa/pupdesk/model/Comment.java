package com.fssa.pupdesk.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment {
	private int commentId;
	private String ticketId;
	private String createdAt;
	private String createdBy;
	private String comment;

	public Comment(int commentId, String ticketId, String createdBy, String comment) {
		this.commentId = commentId;
		this.ticketId = ticketId;
		this.createdAt = new Ticket().getCurrentDateTime();
		this.createdBy = createdBy;
		this.comment = comment;
	}
	public Comment(int commentId, String ticketId, String createdAt, String createdBy, String comment) {
		this.commentId = commentId;
		this.ticketId = ticketId;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.comment = comment;
	}
	


	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", ticketId=" + ticketId + ", createdAt=" + createdAt
				+ ", createdBy=" + createdBy + ", comment=" + comment + "]";
	}

}
