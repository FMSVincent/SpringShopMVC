/**
 * Application console de simulation de l'administration de gestion d'un stock d'articles avec un menu permettant d'ajouter, supprimer, mettre à jour un article ou les afficher par pagination ou pas
 * De même pour les catégories d'articles : ajouter, supprimer, maj ou afficher les catégories ou tous les articles d'une catégorie
 *
 * @author El-Babili - 2023
 */

package fr.fms;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"fr.fms", "web", "resources"})
@SpringBootApplication
public class SpringShopJpaApplication implements CommandLineRunner {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CategoryRepository categoryRepository;


    public static void main(String[] args) {
        SpringApplication.run(SpringShopJpaApplication.class, args);
    }

    @Override
    public void run(String... args) {
//        generateDatas();
//		List<Article> articles = articleRepository.findAll();
//		articles.forEach(article -> System.out.println(article));
    }

    /**
     * Méthode qui permet de regénérer les données dans la base
     * Il faudra basculer en mode create ou supprimer la bdd
     */
    private void generateDatas() {
        Category smartphone = categoryRepository.save(new Category(0, "Smartphone", "Tel mobile", null));
        Category pc = categoryRepository.save(new Category(0, "Ordinateur", "PC & Laptop", null));
        Category tablet = categoryRepository.save(new Category(0, "Tablette", " ", null));
        Category printer = categoryRepository.save(new Category(0, "Imprimante", " ", null));
        Category camera = categoryRepository.save(new Category(0, "Camera", " ", null));
        Category tv = categoryRepository.save(new Category(0, "TV", " ", null));
        Category telescope = categoryRepository.save(new Category(0, "Telescope", " ", null));
        Category gps = categoryRepository.save(new Category(0, "Gps", " ", null));
        Category enceinte = categoryRepository.save(new Category(0, "Enceinte", " ", null));

        articleRepository.save(new Article(0, "Samsung", "S8", 250, smartphone));
        articleRepository.save(new Article(0, "Samsung", "S9", 300, smartphone));
        articleRepository.save(new Article(0, "Iphone", "10", 500, smartphone));
        articleRepository.save(new Article(0, "Xiaomi", "MI11", 100, smartphone));
        articleRepository.save(new Article(0, "OnePlus", "9 Pro", 200, smartphone));
        articleRepository.save(new Article(0, "Google", "Pixel 5", 350, smartphone));
        articleRepository.save(new Article(0, "Poco", "F3", 150, smartphone));

        articleRepository.save(new Article(0, "Dell", "810", 550, pc));
        articleRepository.save(new Article(0, "Asus", "F756", 600, pc));
        articleRepository.save(new Article(0, "Asus", "E80", 700, pc));
        articleRepository.save(new Article(0, "MacBook", "Pro", 1000, pc));
        articleRepository.save(new Article(0, "MacBook", "Air", 1200, pc));

        articleRepository.save(new Article(0, "IPad", "XL 5", 300, tablet));
        articleRepository.save(new Article(0, "IPad", "XL 7", 500, tablet));

        articleRepository.save(new Article(0, "Canon", "MG30", 50, printer));
        articleRepository.save(new Article(0, "Canon", "MG50", 60, printer));
        articleRepository.save(new Article(0, "HP", "800", 50, printer));
        articleRepository.save(new Article(0, "Epson", "3T", 100, printer));

        articleRepository.save(new Article(0, "GoPro", "7", 150, camera));
        articleRepository.save(new Article(0, "GoPro", "10", 200, camera));

        articleRepository.save(new Article(0, "Panasonic", "HT", 1500, tv));
        articleRepository.save(new Article(0, "Philips", "L43", 450, tv));
    }

}
