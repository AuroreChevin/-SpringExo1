package fr.fms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class SpringShopApplication implements CommandLineRunner{
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ArticleRepository articleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringShopApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		Category smartphone = categoryRepository.save(new Category("Smartphone"));
		Category tablet = categoryRepository.save(new Category("Tablet"));
		Category pc = categoryRepository.save(new Category("PC"));
		articleRepository.save(new Article("S9","Samsung", 350, smartphone));
		articleRepository.save(new Article("S10","Samsung", 500, smartphone));
		articleRepository.save(new Article("GalaxyTab","Samsung", 450, tablet));
		articleRepository.save(new Article("MI10","Xiaomi", 100, smartphone));
		articleRepository.save(new Article("Ipad","Apple", 350, tablet));
		articleRepository.save(new Article("R510","Asus", 600, pc));
		for(Article article : articleRepository.findByBrand("Samsung")) {
			System.out.println(article);
		}
		for(Article article : articleRepository.findByBrandAndPrice("Samsung", 250)) {
			System.out.println(article);
		}
		for(Article article : articleRepository.findByDescriptionContains("x")) {
			System.out.println(article);
		}
		for(Article article : articleRepository.findByBrandAndPriceGreaterThan("Samsung", 300)) {
			System.out.println(article);
		}
		for(Article article : articleRepository.searchArticle("sung", 250)) {
			System.out.println(article);
		}
		for(Article article : articleRepository.findByBrandContainsAndPriceGreaterThan("sung", 250)) {
			System.out.println(article);
		}
		for(Article article : articleRepository.findByCategoryId((long) 1)) {
			System.out.println(article);
		}
		
		//Exercice 1.2 : Trouver 2 moyens d’afficher sur la console un article en base.
		for(Article article : articleRepository.findByDescription("S9")) {
			System.out.println(article);
		}
		for(Article article : articleRepository.getReferenceById((long) 1)) {
			System.out.println(article);
		}
		Article article = articleRepository.findById((long) 1).get();
			System.out.println(article);
		
		/* Exercice 1.3 : Ajouter une méthode qui permet de renvoyer tous les articles contenants 
		telle description et telle marque.*/
		for(Article article3 : articleRepository.findByBrandAndDescription("Asus","R510")) {
			System.out.println(article3);
		}
		
		//Exercice 1.4 : Ajouter une méthode qui permet de supprimer un article à partir de l’id
		articleRepository.deleteById((long) 3);
		
		// Exercice 1.5 : Ajouter une méthode qui permet de mettre à jour un article à partir de l’id
		Article article1 = articleRepository.findById((long) 1).get();
		article1.setPrice(2000);
		updateArticle(article1);
		System.out.println(article1);
		
		/*Exercice 1.6 : Ajouter des moyens pour afficher les noms des catégories classés par ordre 
		croissant puis décroissant*/
		for(Category category : categoryRepository.findAllByOrderByNameAsc()) {
			System.out.println(category);
		}
		for(Category category : categoryRepository.findAllByOrderByNameDesc()) {
			System.out.println(category);
		}
		
		// Exercice 1.7 : Ajouter une méthode de votre choix
		for(Article article2 : articleRepository.searchArticleByCat("a")) {
			System.out.println(article2);
		}
		int sum = articleRepository.sumPrice("a");
		System.out.println(sum);
	}
	
		public int updateArticle(Article article) {
			return articleRepository.updateArticleById(article.getId(), article.getBrand(), article.getDescription(), article.getPrice());
		}
	
}
