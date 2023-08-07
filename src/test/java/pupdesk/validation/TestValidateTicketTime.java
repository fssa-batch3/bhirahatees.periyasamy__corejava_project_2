package pupdesk.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestValidateTicketTime {
	@Test
	public void TestValidateTicketTimePass() {
		assertTrue(new TicketValidator().validateTime("2023-04-25 06:55:00"));
	}
	public void TestValidateTicketTimeFail() {
		assertFalse(new TicketValidator().validateTime("2024-04-25 06:55:00"));
	}

}
