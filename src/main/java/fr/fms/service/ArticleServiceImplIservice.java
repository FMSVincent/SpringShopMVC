package fr.fms.service;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.dto.ArticleDto;
import fr.fms.dto.CategoryDto;
import fr.fms.entities.ArticleEntity;
import fr.fms.exceptions.ArticleNotDeletedException;
import fr.fms.exceptions.ArticleNotFoundException;
import fr.fms.mapper.ArticleMapper;
import fr.fms.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ArticleServiceImplIservice implements IService {

    Map<Long, ArticleDto> cart = new HashMap<Long, ArticleDto>();

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CategoryRepository categoryRepository;


    //-- cart -------------------------------------------------------------//

    /**
     * Méthode qui permet d'ajouter un article dans le panier
     *
     * @param article
     * @return le panier
     */
    @Override
    public Map<Long, ArticleDto> addToCart(ArticleDto article) {
        cart.put(article.getId(), article);
        return cart;
    }


    /**
     * Méthode qui permet d'obtenir le panier
     *
     * @return le panier
     */
    @Override
    public Map<Long, ArticleDto> getCart() {
        return cart;
    }
    //-- cart -------------------------------------------------------------//
    //--  -------------------------------------------------------------//

    //-- Article -------------------------------------------------------------//
    //--  -------------------------------------------------------------//

    /**
     * Méthode qui renvoi une quantité n d'article par page et qui peut trier et
     * faire une recherche par mot clé
     *
     * @param kw   mot clé
     * @param page éléments par page
     * @return liste d'articles
     */
    @Override
    public Page<ArticleDto> getArticles(String kw, int page) {
        Page<ArticleEntity> articles = articleRepository.findByDescriptionContains(kw, PageRequest.of(page, 5));
        if (articles.isEmpty())
            throw new ArticleNotFoundException();
        return articles.map(articleMapper::articleEntityToDto);
    }

    /**
     * Méthode qui retourne  n article de la catégorie souhaitée
     *
     * @param categoryId
     * @return une liste d'article
     */
    @Override
    public List<ArticleDto> findByCategory(Long categoryId) {
        List<ArticleEntity> articles = articleRepository.findByCategoryId(categoryId);
        if (articles.isEmpty())
            throw new ArticleNotFoundException();
        return articles.stream().map(articleMapper::articleEntityToDto).toList();
    }

    /**
     * Méthode qui retourne l'article sélectionné
     *
     * @param id
     * @return article
     */
    @Override
    public Optional<ArticleDto> getArticle(Long id) {
        return Optional.ofNullable(articleRepository.findById(id).map(articleMapper::articleEntityToDto).orElseThrow(ArticleNotFoundException::new));
    }

    /**
     * Méthode qui permet de supprimer un article
     *
     * @param id
     */
    @Override
    public void deleteArticle(Long id) {
        try {
            articleRepository.deleteById(id);
        } catch (ArticleNotDeletedException e) {
            throw new ArticleNotDeletedException();
        }
    }

    /**
     * Méthode qui permet de creer un article
     *
     * @param article
     */
    @Override
    public void createArticle(ArticleDto article) {
        ArticleEntity articleEntity = articleMapper.articleDtoToEntity(article);
        articleRepository.save(articleEntity);
    }

    //-- Article -------------------------------------------------------------//
    //-- -------------------------------------------------------------//

    //-- Categories -------------------------------------------------------------//
    //-- -------------------------------------------------------------//

    /**
     * Méthode qui permet d'obtenir la liste des categories
     *
     * @return une liste de catégories
     */
    @Override
    public List<CategoryDto> getCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::categoryEntityToDto).toList();
    }

}
