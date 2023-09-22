package com.fssa.pupdesk.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.fssa.pupdesk.dao.exceptions.DAOException;
import com.fssa.pupdesk.model.Comment;
import com.fssa.pupdesk.utils.*;

public class CommentDAO {
	// Connect to database
	ConnectionUtil dbConnection = new ConnectionUtil();

	public boolean createComment(Comment ticketComment) throws DAOException {

		String insertQuery = "INSERT INTO comments (ticket_id,name , email ,comment_description, is_edited, created_at)VALUES(?,?,?,?,?,?)";
		try (Connection connect = dbConnection.getConnection();
				PreparedStatement statement = connect.prepareStatement(insertQuery)) {
			statement.setString(1, ticketComment.getTicketId());
			statement.setString(2, ticketComment.getName());
			statement.setString(3, ticketComment.getEmail());
			statement.setString(4, ticketComment.getComment());
			statement.setBoolean(5, ticketComment.isEdited());
			statement.setString(6, ticketComment.getCreatedTime());
			int rows = statement.executeUpdate();
			return rows == 1;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	public ArrayList<Comment> listCommentsByTicketId(String ticketId) throws DAOException {
		String selectQuery = "SELECT * FROM comments WHERE ticket_id = ? ORDER BY created_at DESC";
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try (Connection connect = dbConnection.getConnection();
				PreparedStatement statement = connect.prepareStatement(selectQuery)) {
			statement.setString(1, ticketId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment(rs.getString("ticket_id"), rs.getString("name"), rs.getString("email"),
						rs.getString("comment_description"), rs.getBoolean("is_edited"));
				comment.setCreatedTime(rs.getString("created_at"));
				comment.setCommentId(rs.getInt("comment_id"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return comments;
	}

	public boolean editCommentById(int commentId, String commentDescription) throws DAOException {
		String updateQuery = "UPDATE comments SET comment_description = ?,is_edited = ? , created_at= ? WHERE comment_id = ?";
		try (Connection connect = dbConnection.getConnection();
				PreparedStatement statement = connect.prepareStatement(updateQuery)) {
			statement.setString(1, commentDescription);
			statement.setBoolean(2, true);
			statement.setString(3, CurrentTimeGenerator.getCurrentDateTime());
			statement.setInt(4, commentId);
			int rows = statement.executeUpdate();
			return rows == 1;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
	}

	public boolean deleteCommentById(int commentId, String ticketId) throws DAOException {
		String deleteQuery = "DELETE FROM comments WHERE ticket_id = ? && comment_id = ?";
		try (Connection connect = dbConnection.getConnection();
				PreparedStatement statement = connect.prepareStatement(deleteQuery)) {
			statement.setString(1, ticketId);
			statement.setInt(2, commentId);
			int rows = statement.executeUpdate();
			return rows == 1;
		} catch (SQLException e) {
			throw new DAOException("Failed to delete");
		}
	}

}
