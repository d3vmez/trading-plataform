package com.tradingplataform.notificationservice.model.dto;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { NotificationDTOTest.class })
class NotificationDTOTest {

	@Test @Order(1)
	void test() {

		NotificationDTO n1 = new NotificationDTO("recipient", "subject", "content");
		NotificationDTO n2 = new NotificationDTO();
		n2.setRecipient("recipient");
		n2.setSubject("subject");
		n2.setContent("content");
		
		assertAll("Test getters and setters",
				()->assertEquals(n1.getRecipient(),n2.getRecipient()),
				()->assertEquals(n1.getSubject(),n2.getSubject()),
				()->assertEquals(n1.getContent(),n2.getContent())
				);
		
		assertEquals(n1.equals(n2), true);
		assertEquals("NotificationDTO(recipient=recipient, subject=subject, content=content)", n1.toString());
		assertEquals(964980177,n1.hashCode());
		
	}

}
