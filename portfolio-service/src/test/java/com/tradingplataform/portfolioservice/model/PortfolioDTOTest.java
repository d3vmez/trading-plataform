package com.tradingplataform.portfolioservice.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { PortfolioDTOTest.class })
class PortfolioDTOTest {

	@Test
	void testPortfolioDTOModel() {
		
		PortfolioDTO p1 = new PortfolioDTO("product name", 1, 10);
		PortfolioDTO p2 = new PortfolioDTO();
		p2.setProductName("product name");
		p2.setUserId(2);
		p2.setCuantity(10);
		
		assertAll("Test getters and setters",
				()->assertEquals(p1.getProductName(), p2.getProductName()),
				()->assertNotEquals(p1.getUserId(), p2.getUserId()),
				()->assertEquals(p1.getCuantity(), p2.getCuantity())
				);
		
		assertEquals(p1.equals(p2), false);
		assertEquals("PortfolioDTO(productName=product name, userId=1, cuantity=10)", p1.toString());
		assertEquals(956403014,p1.hashCode());
		
	}

}
