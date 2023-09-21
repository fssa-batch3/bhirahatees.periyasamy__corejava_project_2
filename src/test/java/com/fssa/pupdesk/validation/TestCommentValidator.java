package com.fssa.pupdesk.validation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.fssa.pupdesk.model.Comment;
import com.fssa.pupdesk.validation.exceptions.InvalidCommentException;

public class TestCommentValidator {
	@Test
	void testCommentInvalidName() {
		Comment comment = new Comment("EE$$AB7ACDD31", "Bhirahateesvaran", "bhirahatees.periyasamy@fssa.freshworks.com",
				"Didn't received", false);
		assertThrows(InvalidCommentException.class, () -> CommentValidator.validateComment(comment));
	}

	@Test
	void testCommentInvalidEmail() {
		Comment comment = new Comment("EE$$AB7ACDD31", "Bhirahateesvaran", "bhirahatees.periyasamyfssa.freshworks.com",
				"Didn't received", false);
		assertThrows(InvalidCommentException.class, () -> CommentValidator.validateComment(comment));
	}

	@Test
	void testCommentInvalidTicketId() {
		Comment comment = new Comment("EE$$AB7ACDD31", "Bhirahateesvaran", "bhirahatees.periyasamy@fssa.freshworks.com",
				"Didn't received", false);
		assertThrows(InvalidCommentException.class, () -> CommentValidator.validateComment(comment));
	}

	@Test
	void testCommentValid() {
		Comment comment = new Comment("EEAB7F41ACDD31", "Bhirahateesvaran Periyasamy",
				"bhirahatees.periyasamy@fssa.freshworks.com", "Didn't received", false);
		assertDoesNotThrow(() -> CommentValidator.validateComment(comment));
	}

	@Test
	void testCommentInvalid() {
		Comment comment = new Comment("EEAB7F41ACDD31", "Bhirahateesvaran Periyasmay",
				"bhirahatees.periyasamy@fssa.freshworks.com", "", false);
		assertThrows(InvalidCommentException.class, () -> CommentValidator.validateComment(comment));
	}

	@Test
	void testCommentDescriptionValidatorPass() {
		try {
			assertTrue(CommentValidator.commentDescriptionValidator("Can you re-check the values"));
		} catch (InvalidCommentException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	void testCommentDescriptionValidatorEmptyString() {
		assertThrows(InvalidCommentException.class, () -> CommentValidator.commentDescriptionValidator(""));
	}

	@Test
	void testCommentDescriptionValidatorNull() {
		assertThrows(InvalidCommentException.class, () -> CommentValidator.commentDescriptionValidator(null));
	}
}
