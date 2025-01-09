package fr.fms;

import fr.fms.entities.Article;
import fr.fms.service.ArticleServiceImplIservice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class SpringShopJpaApplicationTests {

	@Autowired
	ArticleServiceImplIservice service;

	@Test
	void contextLoads() {
		assertFalse(1==2);
	}

	@Test
	void testTotalAmount(){
		service.addToCart(new Article(1L, "brandtest", "descriptionTest", 250, null ));
		Double  total  = service.getTotalAmount();;
		assertEquals(total, 250);
	}
}
