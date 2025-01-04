package fr.fms.mapper;

import fr.fms.dto.ArticleDto;
import fr.fms.entities.ArticleEntity;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    public ArticleDto entityToDto(ArticleEntity articleEntity){

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

    public ArticleEntity dtoToEntity(ArticleDto articleDto){

        if (articleDto == null)
            return null;

        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setId(articleDto.getId());
        articleEntity.setBrand(articleDto.getBrand());
        articleEntity.setDescription(articleDto.getDescription());
        articleEntity.setPrice(articleDto.getPrice());
        articleEntity.setCategory(articleDto.getCategory());

        return articleEntity;
    }
}
