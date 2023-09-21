package com.fssa.pupdesk.validation;

import com.fssa.pupdesk.model.Comment;
import com.fssa.pupdesk.validation.exceptions.InvalidCommentException;
import com.fssa.pupdesk.validation.exceptions.InvalidTicketException;
import com.fssa.pupdesk.validation.exceptions.InvalidUserException;

public class CommentValidator {
	public static void validateComment(Comment comment) throws InvalidCommentException {
		String[] name = comment.getName().split(" ");
		try {
			if (UserValidator.validateFirstname(name[0]) && UserValidator.validateFirstname(name[1])
					&& UserValidator.validateEmail(comment.getEmail())
					&& TicketValidator.validateTicketId(comment.getTicketId())
					&& TicketValidator.validateTime(comment.getCreatedTime())
					&& commentDescriptionValidator(comment.getComment())) {
				System.out.println("Valid Comment");
			} else {
				throw new InvalidCommentException("Invalid Comment");
			}
		} catch (InvalidUserException | ArrayIndexOutOfBoundsException | InvalidTicketException e) {
			throw new InvalidCommentException(e.getMessage());
		}
	}

	public static boolean commentDescriptionValidator(String description) throws InvalidCommentException {
		try {
			if ((description.isEmpty() || description.equals(null))) {
				throw new InvalidCommentException("There is no Text");
			} else {
				return true;
			}
		} catch (NullPointerException e) {
			throw new InvalidCommentException("There is no Text");
		}
	}
}
