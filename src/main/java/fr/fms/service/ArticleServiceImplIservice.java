package fr.fms.service;

import fr.fms.dao.ArticleRepository;
import fr.fms.dto.ArticleDto;
import fr.fms.entities.ArticleEntity;
import fr.fms.exceptions.ArticleNotDeletedException;
import fr.fms.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ArticleServiceImplIservice implements IService<ArticleDto> {

    Map<Long, ArticleDto> cart = new HashMap<Long, ArticleDto>();

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleRepository articleRepository;

    public Map<Long, ArticleDto> addToCart(ArticleDto article) {
        cart.put(article.getId(), article);
        return cart;
    }


    public Map<Long, ArticleDto> getCart(){
        return cart;
    }
    /// cart ////////////////////////////


    @Override
    public List<ArticleDto> getAll() {
       List<ArticleEntity> articleEntity = articleRepository.findAll();
        return articleEntity.stream().map(a-> articleMapper.entityToDto(a)).toList();
    }

    @Override
    public Page<ArticleDto> getAll(String kw, int page) {
        Page<ArticleEntity> articles = articleRepository.findByDescriptionContains(kw, PageRequest.of(page, 5));
        return articles.map(articleMapper::entityToDto);
    }


    @Override
    public List<ArticleDto> findByAttribute(Long categoryId) {
        return articleRepository.findByCategoryId(categoryId).stream().map(articleMapper::entityToDto).toList();
    }

    @Override
    public Optional<ArticleDto> getOne(Long id) {
        return articleRepository.findById(id).map(articleMapper::entityToDto);
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
    public void createOne(ArticleDto article) {
        ArticleEntity articleEntity = articleMapper.dtoToEntity(article);
        articleRepository.save(articleEntity);
    }

}
