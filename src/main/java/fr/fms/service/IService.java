package fr.fms.service;

import fr.fms.dto.ArticleDto;
import fr.fms.dto.CategoryDto;
import fr.fms.exceptions.CategoryNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IService {

    Map<Long, ArticleDto> addToCart(ArticleDto article);

    Map<Long, ArticleDto> getCart();

    Page<ArticleDto> getArticles(String kw, int page);

    List<ArticleDto> findByCategory(Long categoryId);

    Optional<ArticleDto> getArticle(Long id);

    void deleteArticle(Long id);

    void createArticle(ArticleDto article);

    List<CategoryDto> getCategories() throws CategoryNotFoundException;
}
