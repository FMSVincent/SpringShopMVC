package fr.fms.service;

import fr.fms.dao.CategoryRepository;
import fr.fms.dto.ArticleDto;
import fr.fms.entities.CategoryEntity;
import fr.fms.exceptions.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplIservice implements IService<CategoryEntity> {


    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getAll() throws CategoryNotFoundException {
        List<CategoryEntity> categories = categoryRepository.findAll();

        return categories;
    }

    @Override
    public Page<CategoryEntity> getAll(String kw, int page) {
        return null;
    }

    @Override
    public List<CategoryEntity> findByAttribute(Long id) {
        return null;
    }


    @Override
    public Optional<CategoryEntity> getOne(Long id) {
        return null;
    }


    @Override
    public void deleteOne(Long id) {
    }

    @Override
    public void createOne(CategoryEntity category) {
    }

}
