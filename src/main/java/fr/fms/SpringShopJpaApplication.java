/**
 * Application console de simulation de l'administration de gestion d'un stock d'articles avec un menu permettant d'ajouter, supprimer, mettre à jour un article ou les afficher par pagination ou pas
 * De même pour les catégories d'articles : ajouter, supprimer, maj ou afficher les catégories ou tous les articles d'une catégorie
 *
 * @author El-Babili - 2023
 */

package fr.fms;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.ArticleEntity;
import fr.fms.entities.CategoryEntity;
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
        CategoryEntity smartphone = categoryRepository.save(new CategoryEntity(0, "Smartphone", "Tel mobile", null, null));
        CategoryEntity pc = categoryRepository.save(new CategoryEntity(0, "Ordinateur", "PC & Laptop", null, null));
        CategoryEntity tablet = categoryRepository.save(new CategoryEntity(0, "Tablette", " ", null, null));
        CategoryEntity printer = categoryRepository.save(new CategoryEntity(0, "Imprimante", " ", null, null));
        CategoryEntity camera = categoryRepository.save(new CategoryEntity(0, "Camera", " ", null, null));
        CategoryEntity tv = categoryRepository.save(new CategoryEntity(0, "TV", " ", null, null));
        CategoryEntity telescope = categoryRepository.save(new CategoryEntity(0, "Telescope", " ", null, null));
        CategoryEntity gps = categoryRepository.save(new CategoryEntity(0, "Gps", " ", null, null));
        CategoryEntity enceinte = categoryRepository.save(new CategoryEntity(0, "Enceinte", " ", null, null));

        articleRepository.save(new ArticleEntity(0, "Samsung", "S8", 250, smartphone, null));
        articleRepository.save(new ArticleEntity(0, "Samsung", "S9", 300, smartphone, null));
        articleRepository.save(new ArticleEntity(0, "Iphone", "10", 500, smartphone, null));
        articleRepository.save(new ArticleEntity(0, "Xiaomi", "MI11", 100, smartphone, null));
        articleRepository.save(new ArticleEntity(0, "OnePlus", "9 Pro", 200, smartphone, null));
        articleRepository.save(new ArticleEntity(0, "Google", "Pixel 5", 350, smartphone, null));
        articleRepository.save(new ArticleEntity(0, "Poco", "F3", 150, smartphone, null));

        articleRepository.save(new ArticleEntity(0, "Dell", "810", 550, pc, null));
        articleRepository.save(new ArticleEntity(0, "Asus", "F756", 600, pc, null));
        articleRepository.save(new ArticleEntity(0, "Asus", "E80", 700, pc, null));
        articleRepository.save(new ArticleEntity(0, "MacBook", "Pro", 1000, pc, null));
        articleRepository.save(new ArticleEntity(0, "MacBook", "Air", 1200, pc, null));

        articleRepository.save(new ArticleEntity(0, "IPad", "XL 5", 300, tablet, null));
        articleRepository.save(new ArticleEntity(0, "IPad", "XL 7", 500, tablet, null));

        articleRepository.save(new ArticleEntity(0, "Canon", "MG30", 50, printer, null));
        articleRepository.save(new ArticleEntity(0, "Canon", "MG50", 60, printer, null));
        articleRepository.save(new ArticleEntity(0, "HP", "800", 50, printer, null));
        articleRepository.save(new ArticleEntity(0, "Epson", "3T", 100, printer, null));

        articleRepository.save(new ArticleEntity(0, "GoPro", "7", 150, camera, null));
        articleRepository.save(new ArticleEntity(0, "GoPro", "10", 200, camera, null));

        articleRepository.save(new ArticleEntity(0, "Panasonic", "HT", 1500, tv, null));
        articleRepository.save(new ArticleEntity(0, "Philips", "L43", 450, tv, null));
    }

}
