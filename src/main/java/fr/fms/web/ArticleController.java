package fr.fms.web;

import fr.fms.dto.ArticleDto;
import fr.fms.dto.CategoryDto;
import fr.fms.exceptions.ArticleNotDeletedException;
import fr.fms.exceptions.ArticleNotFoundException;
import fr.fms.exceptions.CategoryNotFoundException;
import fr.fms.service.ArticleServiceImplIservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ArticleController {

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    ArticleServiceImplIservice articleService;

    //-- cart  ------------------------------------------------------------------------------------------------//
    //-- -----------------------------------------------------------------------------------------------------//

    /**
     * méthode qui permet d'afficher le panier
     *
     * @param model
     * @return la page du panier
     */
    @GetMapping("/cart")
    public String cart(Model model) {
        Map<Long, ArticleDto> cart = articleService.getCart();
        List<ArticleDto> articles = new ArrayList<>(cart.values());
        model.addAttribute("cartList", articles);
        return "cart";
    }

    /**
     * méthode qui permet de sauvegarder un article dans le panier
     * et redirige ver la page du panier
     *
     * @param id                 de l'article
     * @param redirectAttributes
     * @return redirige vers le panier
     */
    @GetMapping("/saveToCart")
    public String saveArticleToCart(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<ArticleDto> getArticle = articleService.getArticle(id);
        if (getArticle.isPresent()) {
            ArticleDto article = getArticle.get();
            Map<Long, ArticleDto> articles = articleService.addToCart(article);
            List<ArticleDto> articlesList = new ArrayList<>(articles.values());
            redirectAttributes.addFlashAttribute("cartList", articlesList);
        }
        return "redirect:/cart";
    }

    //-- Article  ------------------------------------------------------------------------------------------------//
    //-- ---------------------------------------------------------------------------------------------------------//

    /**
     * méthode qui permet d'afficher la page principale avec la liste des articles
     * avec une pagination
     *
     * @param model
     * @param page
     * @param kw
     * @return la page principale
     */
    @GetMapping({"/index", "/"})
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "keyword", defaultValue = "") String kw) {
        try {
            Page<ArticleDto> articles = articleService.getArticles(kw, page);

            model.addAttribute("listArticles", articles.getContent());
            model.addAttribute("page", IntStream.range(0, articles.getTotalPages()).boxed().collect(Collectors.toList()));
            model.addAttribute("currentPage", page);
            model.addAttribute("keyword", kw);
            List<CategoryDto> categories = articleService.getCategories();
            model.addAttribute("listCategories", categories);
        } catch (ArticleNotFoundException e) {
            model.addAttribute("articlesNotFound", e.getMessage());
            log.error("Article not found");
        } catch (CategoryNotFoundException e) {
            model.addAttribute("categoriesNotFound", e.getMessage());
            log.error("Category not found");
        }
        return "articles";
    }

    /**
     * Méthode qui permet de supprimee un article
     *
     * @param redirectAttributes
     * @param id
     * @param page
     * @param keyword
     * @return la page principale
     */
    @GetMapping("/delete")
    public String delete(RedirectAttributes redirectAttributes,
                         Model model,
                         @RequestParam Long id,
                         @RequestParam int page,
                         @RequestParam String keyword) {
        try {
            if (id == null || id < 0) {
                throw new IllegalArgumentException("id pas valide");
            }
            articleService.deleteArticle(id);
            redirectAttributes.addFlashAttribute("successDeleted", "Article supprimé");
        } catch (ArticleNotDeletedException e) {
            redirectAttributes.addFlashAttribute("notDeleted", e.getMessage());
            log.error(e.getMessage());
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
        }

        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    /**
     * Méthode qui permet affiche un article s'il existe
     *
     * @param model
     * @param id
     * @return la page article
     */
    @GetMapping("/article")
    public String article(Model model, @RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            Optional<ArticleDto> article = articleService.getArticle(id);
            article.ifPresent(value -> model.addAttribute("article", value));
        } else {
            model.addAttribute("article", new ArticleDto());
        }
        List<CategoryDto> categories = articleService.getCategories();
        model.addAttribute("listCategories", categories);

        return "article";
    }

    /**
     * Méthode qui permet de sauvegarder un article en le modifiant ou le créant
     *
     * @param model
     * @param article
     * @param bindingResult
     * @param id
     * @return la page principale
     */
    @PostMapping({"/save", "/save/{id}"})
    public String save(Model model, @Valid ArticleDto article, BindingResult bindingResult, @PathVariable(required = false) Long id) {
        System.out.println(":::::::::::::::" + article);
        if (bindingResult.hasErrors()) {
            List<CategoryDto> categories = articleService.getCategories();
            model.addAttribute("listCategories", categories);
            return "article";
        }
        if (id != null) {
            articleService.getArticle(id).ifPresent(articleUpdate -> {
                articleUpdate.setBrand(article.getBrand());
                articleUpdate.setDescription(article.getDescription());
                articleUpdate.setPrice(article.getPrice());
                articleUpdate.setCategory(article.getCategory());
                articleService.createArticle(articleUpdate);
            });
        } else articleService.createArticle(article);
        return "redirect:/index";
    }

    /**
     * Méthode qui permet d'afficher les articles par catégorie
     *
     * @param categoryId
     * @param model
     * @return
     */
    @GetMapping("/articlesByCategory")
    public String getArticlesByCategory(@RequestParam Long categoryId, Model model) {
        try {
            List<ArticleDto> articles = articleService.findByCategory(categoryId);
            List<CategoryDto> categories = articleService.getCategories();
            model.addAttribute("listCategories", categories);
            model.addAttribute("listArticles", articles);
            model.addAttribute("categoryId", categoryId);
        } catch (ArticleNotFoundException e) {
            log.error(e.getMessage());
        }

        return "articles";
    }

    /**
     * méthode qui renvoi sur la page error
     *
     * @return page 403
     */
    @GetMapping("/403")
    public String error() {
        return "403";
    }

}
