package fr.fms.dao;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.fms.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{
	
	public List<Article> findByBrand(String brand);
	public List<Article> findByDescription(String description);
	public List<Article> findByDescriptionContains(String description);
	public List<Article> findByBrandAndPrice(String brand, double price);
	public List<Article> findByBrandAndPriceGreaterThan(String brand, double price);
	@Query("select A from Article A where A.brand like %:x% and A.price > :y")
	public List<Article> searchArticle(@Param("x") String kw, @Param("y")double price);
	public List<Article> findByBrandContainsAndPriceGreaterThan(String brand, double price);
	public List<Article> findByCategoryId(Long categoryId);
	public List<Article> getReferenceById(Long id);
	public List<Article> findByBrandAndDescription(String brand, String description);
	
	@Transactional
	@Modifying
	@Query("update Article a set a.brand = ?2 , a.description = ?3, a.price = ?4 where a.id = ?1")
	public int updateArticleById(Long id, String brand, String description, double price);
	@Query("select A from Article A where A.category.name like %:x%")
	public List<Article> searchArticleByCat(@Param("x") String kw);
	@Query("select sum(a.price) from Article a where a.category.name like %:x%")
	public int sumPrice(@Param("x") String kw);
	@Query("select a.brand from Article a group by a.brand")
	public List<Article> group();
}
