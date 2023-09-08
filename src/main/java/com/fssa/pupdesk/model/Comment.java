package com.fssa.pupdesk.model;

import com.fssa.pupdesk.utils.*;

public class Comment {
	private int commentId;
	private String ticketId;
	private String createdAt;
	private String createdBy;
	private String ticketComment;

	/**
	 * Constructs a new Comment object with the specified parameters.
	 *
	 * @param commentId The unique identifier for the comment.
	 * @param ticketId  The unique identifier for the associated ticket.
	 * @param createdBy The name of the user who created the comment.
	 * @param comment   The text content of the comment.
	 */
	public Comment(int commentId, String ticketId, String createdBy, String comment) {
		this.commentId = commentId;
		this.ticketId = ticketId;
		this.createdAt = CurrentTimeGenerator.getCurrentDateTime();
		this.createdBy = createdBy;
		this.ticketComment = comment;
	}

	/**
	 * Constructs a new Comment object with the specified parameters.
	 *
	 * @param commentId The unique identifier for the comment.
	 * @param ticketId  The unique identifier for the associated ticket.
	 * @param createdAt The timestamp when the comment was created.
	 * @param createdBy The name of the user who created the comment.
	 * @param comment   The text content of the comment.
	 */
	public Comment(int commentId, String ticketId, String createdAt, String createdBy, String comment) {
		this.commentId = commentId;
		this.ticketId = ticketId;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.ticketComment = comment;
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
		this.ticketComment = comment;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", ticketId=" + ticketId + ", createdAt=" + createdAt
				+ ", createdBy=" + createdBy + ", comment=" + ticketComment + "]";
	}
}
