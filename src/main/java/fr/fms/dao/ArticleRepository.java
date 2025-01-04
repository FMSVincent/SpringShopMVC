package fr.fms.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.ArticleEntity;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

	Page<ArticleEntity> findByDescriptionContains(String description, Pageable pageable);
	List<ArticleEntity> findByCategoryId(Long categoryId);


//	public List<Article> findByBrand(String brand);
//	public List<Article> findByBrandContains(String brand);
//	public List<Article> findByBrandAndPrice(String brand, double price);
//	public List<Article> findByBrandContainsAndPriceGreaterThan(String brand, double price);
//
//	@Query("select A from Article A where A.brand like %:x% and A.price > :y")
//	public List<Article> searchArticles(@Param("x")String brand,@Param("y")double price);
//
//	@Query("select A from Article A where A.id= :id")
//	public Article findOne(@Param("id")long id);
//
//	@Transactional
//	@Modifying
//	@Query("update Article a set a.brand= :b, a.price= :p where a.id= :id")
//	public int update(@Param("b")String brand ,@Param("p")double price , @Param("id")long id);
//
//	public List<Article> findByDescriptionAndBrand(String description, String brand);
//	//public Page<Article> findAll(Pageable pageable);


}
