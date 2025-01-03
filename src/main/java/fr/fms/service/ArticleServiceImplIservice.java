package fr.fms.service;

import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;
import fr.fms.exceptions.ArticleNotDeletedException;
import fr.fms.exceptions.ArticleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ArticleServiceImplIservice implements IService<Article> {
    Map<Long, Article> cart = new HashMap<Long, Article>();

    @Autowired
    ArticleRepository articleRepository;

    /// cart ////////////////////////////

    public Map<Long, Article> getCart() {
        return cart;
    }

    public Map<Long, Article> addToCart(Article article) {
        cart.put(article.getId(), article);
        return cart;
    }


    /// cart ////////////////////////////


    @Override
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public Page<Article> getAll(String kw, int page) throws ArticleNotFoundException {
        Page<Article> articles = articleRepository.findByDescriptionContains(kw, PageRequest.of(page, 5));
        if (articles.isEmpty()) {
            throw new ArticleNotFoundException("Aucun article à charger les articles !");
        }
        return articles;
    }

    @Override
    public List<Article> findByAttribute(Long categoryId) {
        return articleRepository.findByCategoryId(categoryId);
    }

    @Override
    public Optional<Article> getOne(Long id) {
        return articleRepository.findById(id);
    }

    @Override
    public void deleteOne(Long id) {
        try {
            articleRepository.deleteById(id);
        } catch (ArticleNotDeletedException e) {
            throw new ArticleNotDeletedException("impossible a supprimer !");
        }
    }

    @Override
    public void createOne(Article article) {
        articleRepository.save(article);
    }

}
