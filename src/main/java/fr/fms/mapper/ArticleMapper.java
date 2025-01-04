package fr.fms.mapper;

import fr.fms.dto.ArticleDto;
import fr.fms.entities.ArticleEntity;
import fr.fms.entities.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    @Autowired
    CategoryMapper categoryMapper;

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
        ArticleEntity articleEntity = new ArticleEntity();
        CategoryEntity categoryEntity = categoryMapper.categoryDtoToEntity(articleDto.getCategory());

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

}
