package com.fssa.pupdesk.services;

import java.sql.SQLException;
import java.util.List;

import com.fssa.pupdesk.dao.CommentDAO;
import com.fssa.pupdesk.dao.exceptions.DAOException;
import com.fssa.pupdesk.model.Comment;
import com.fssa.pupdesk.services.exceptions.ServiceException;
import com.fssa.pupdesk.validation.CommentValidator;
import com.fssa.pupdesk.validation.TicketValidator;
import com.fssa.pupdesk.validation.exceptions.InvalidCommentException;
import com.fssa.pupdesk.validation.exceptions.InvalidTicketException;

public class CommentService {
	public boolean createCommentService(Comment comment) throws ServiceException {
		CommentDAO addComment = new CommentDAO();
		try {
			CommentValidator.validateComment(comment);
			return addComment.createComment(comment);
		} catch (InvalidCommentException | DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public List<Comment> listCommentService(String ticketId) throws ServiceException {
		List<Comment> comments = null;
		CommentDAO listComment = new CommentDAO();
		try {
			TicketValidator.validateTicketId(ticketId);
			comments = listComment.listCommentsByTicketId(ticketId);
			for (Comment comment : comments) {
				try {
					CommentValidator.validateComment(comment);
				} catch (InvalidCommentException e) {
					throw new ServiceException(e.getMessage());
				}
			}
			return comments;
		} catch (DAOException | InvalidTicketException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public boolean editCommentService(int commentId, String commentDescription) throws ServiceException {
		CommentDAO comment = new CommentDAO();
		try {
			if(comment.editCommentById(commentId, commentDescription)) {
				return true;
			}else {
				throw new ServiceException("There is no Comment in this id");
			}
		} catch (DAOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public boolean deleteCommentService(int commentId, String ticketId) throws ServiceException {
		CommentDAO comment = new CommentDAO();
		try {
			TicketValidator.validateTicketId(ticketId);
			if(comment.deleteCommentById(commentId, ticketId)) {
				return true;
			}else {
				throw new ServiceException("There is no Comment in this id");
			}
		} catch (DAOException | InvalidTicketException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
