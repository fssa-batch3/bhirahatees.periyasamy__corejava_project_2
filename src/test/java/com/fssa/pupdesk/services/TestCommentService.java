package com.fssa.pupdesk.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.fssa.pupdesk.model.Comment;
import com.fssa.pupdesk.services.exceptions.ServiceException;

public class TestCommentService {
	@Test
	void TestCreateCommentPass() {
		Comment comment = new Comment("EEAB7F41ACDD31", "Bhirahateesvaran Periyasamy",
				"bhirahatees.periyasamy@fssa.freshworks.com", "Didn't work", true);
		CommentService createComment = new CommentService();
		try {
			assertTrue(createComment.createCommentService(comment));
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	void TestCreateCommentFail() {
		Comment comment = new Comment("EEAB7F$$1ACDD31", "Bhirahateesvaran Periyasamy",
				"bhirahatees.periyasamy@fssa.freshworks.com", "", false);
		CommentService createComment = new CommentService();
		assertThrows(ServiceException.class, () -> createComment.createCommentService(comment));
	}

	@Test
	void TestListCommentPass() {
		CommentService listComment = new CommentService();
		try {
			assertNotNull(listComment.listCommentService("EEAB7F41ACDD31"));
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			fail();
		}
	}

	@Test
	void TestListCommentFail() {
    	CommentService listComment = new CommentService();

			assertThrows(ServiceException.class,()->{listComment.listCommentService("EEAB7F41&#CDD31");});
	
	}
	@Test
	void TestEditCommentPass() {
		CommentService editComment = new CommentService();
		try {
			assertNotNull(editComment.editCommentService(3,"Thanks You "));
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			fail();
		}
	}

	@Test
	void TestEditCommentFail() {
    	CommentService editComment = new CommentService();
			assertThrows(ServiceException.class,()->{editComment.editCommentService(2, "");});
	
	}
	
	@Test
	void TestDeleteCommentPass() {
		CommentService deleteComment = new CommentService();
		try {
			assertNotNull(deleteComment.editCommentService(3,"EEAB7F41ACDD31"));
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			fail();
		}
	}
	@Test
	void TestDeleteCommentFail() {
    	CommentService deleteComment = new CommentService();
			assertThrows(ServiceException.class,()->{deleteComment.editCommentService(2, "");});
	
	}
}