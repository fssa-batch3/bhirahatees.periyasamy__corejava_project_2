package com.fssa.pupdesk.model;

import com.fssa.pupdesk.utils.CurrentTimeGenerator;

public class Comment {

	private int commentId;
	private String name;
	private String email;
	private String comment;
	private boolean isEdited;
	private String createdTime;
	private String ticketId;

	public Comment(String ticketId, String name, String email, String comment, boolean isEdited) {
		this.name = name;
		this.email = email;
		this.comment = comment;
		this.isEdited = isEdited;
		this.createdTime = CurrentTimeGenerator.getCurrentDateTime();
		this.ticketId = ticketId;
	}

	public Comment() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getComment() {
		return comment;
	}

	public boolean isEdited() {
		return isEdited;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setEdited(boolean isEdited) {
		this.isEdited = isEdited;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public void setCreatedTime(String creaetedTime) {
		this.createdTime = creaetedTime;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	@Override
	public String toString() {
		return "Comment [name=" + name + ", email=" + email + ", comment=" + comment + ", isEdited=" + isEdited
				+ ", creaetedTime=" + createdTime + ", ticketId=" + ticketId + "]";
	}

}
