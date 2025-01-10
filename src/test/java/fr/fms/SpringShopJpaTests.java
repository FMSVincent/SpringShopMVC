package fr.fms;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SpringShopJpaTests {

    @Autowired(required = true)
    ArticleRepository articleRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void test_add_article() {
        Category anonymous = categoryRepository.save(new Category(0, "anonymous", "anonymous", null));
        articleRepository.save(new Article(0, "anonymous", "anonymous", 250.0, anonymous));

        Article article = articleRepository.findByBrandContains("anonymous");
        assertEquals("anonymous", article.getBrand());
    }

    @Test
    void test_get_all_articles() {
        int initialSize = articleRepository.findAll().size();

        Category category1 = categoryRepository.save(new Category(0, "category1", "description1", null));
        Category category2 = categoryRepository.save(new Category(0, "category2", "description2", null));

        articleRepository.save(new Article(0, "brand1", "description1", 100.0, category1));
        articleRepository.save(new Article(0, "brand2", "description2", 200.0, category2));

        int newSize = articleRepository.findAll().size();
        assertEquals(initialSize + 2, newSize);
    }
}
