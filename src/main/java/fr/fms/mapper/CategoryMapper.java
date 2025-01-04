package fr.fms.mapper;

import fr.fms.dto.CategoryDto;
import fr.fms.entities.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto categoryEntityToDto(CategoryEntity categoryEntity) {
        if (categoryEntity == null)
            return null;

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryEntity.getId());
        categoryDto.setName(categoryEntity.getName());

        return categoryDto;
    }

    public CategoryEntity categoryDtoToEntity(CategoryEntity categoryDto) {
        if (categoryDto == null)
            return null;

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryDto.getId());
        categoryEntity.setName(categoryDto.getName());

        return categoryEntity;
    }
}
