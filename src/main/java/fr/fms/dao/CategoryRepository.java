package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
	public List<CategoryEntity> findByOrderByNameDesc();
	public List<CategoryEntity> findByOrderByNameAsc();
	
	public CategoryEntity findByName(String name);

}
