package fr.fms.mapper;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.dto.ArticleDto;
import fr.fms.entities.ArticleEntity;
import fr.fms.entities.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ArticleMapper {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public ArticleDto articleEntityToDto(ArticleEntity articleEntity) {

        if (articleEntity == null)
            return null;

        ArticleDto articleDto = new ArticleDto();
        articleDto.setCategory(articleEntity.getCategory());
        articleDto.setId(articleEntity.getId());
        articleDto.setBrand(articleEntity.getBrand());
        articleDto.setPrice(articleEntity.getPrice());
        articleDto.setDescription(articleEntity.getDescription());

        return articleDto;
    }

    public ArticleEntity articleDtoToEntity(ArticleDto articleDto) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(articleDto.getCategory().getId());
        if (optionalCategoryEntity.isPresent()) {
            ArticleEntity articleEntity = new ArticleEntity();
            CategoryEntity categoryEntity = optionalCategoryEntity.get();

            articleEntity.setId(articleDto.getId());
            articleEntity.setBrand(articleDto.getBrand());
            articleEntity.setDescription(articleDto.getDescription());
            articleEntity.setPrice(articleDto.getPrice());
            articleEntity.setCategory(categoryEntity);

            if (articleEntity.getCreatedAt() == null) {
                articleEntity.createDate();
            }
            return articleEntity;
        }
        return null;
    }

}
