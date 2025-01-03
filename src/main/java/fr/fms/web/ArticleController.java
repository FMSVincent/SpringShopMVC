package fr.fms.web;

import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.exceptions.ArticleNotDeletedException;
import fr.fms.exceptions.ArticleNotFoundException;
import fr.fms.exceptions.CategoryNotFoundException;
import fr.fms.service.ArticleServiceImplIservice;
import fr.fms.service.CategoryServiceImplIservice;
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

    @Autowired
    ArticleServiceImplIservice articleService;

    @Autowired
    CategoryServiceImplIservice categoryService;

    @Autowired
    CategoryRepository categoryRepository;

    /// cart  ///////////////////////////////////////////////////////////
    @GetMapping("/cart")
    public String cart(Model model) {
        Map<Long, Article> cart = articleService.getCart();
        List<Article> articles = new ArrayList<>(cart.values());
        model.addAttribute("cartList", articles);
        return "cart";
    }

    @GetMapping("/saveToCart")
    public String saveArticleToCart(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Article> getArticle = articleService.getOne(id);
        System.out.println("get:    " + getArticle);
        if (getArticle.isPresent()) {
            Article article = getArticle.get();
            Map<Long, Article> articles = articleService.addToCart(article);
            List<Article> articlesList = new ArrayList<>(articles.values());
            System.out.println("List   " + articlesList);
            redirectAttributes.addFlashAttribute("cartList", articlesList);
        }
        return "redirect:/cart";
    }

    /// cart  ///////////////////////////////////////////////////////////

    @GetMapping({"/index", "/"})
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "keyword", defaultValue = "") String kw) {
        try {
            Page<Article> articles = articleService.getAll(kw, page);

            model.addAttribute("listArticles", articles.getContent());
            model.addAttribute("page", IntStream.range(0, articles.getTotalPages()).boxed().collect(Collectors.toList()));
            model.addAttribute("currentPage", page);
            model.addAttribute("keyword", kw);
        } catch (ArticleNotFoundException e) {
            model.addAttribute("articlesNotFound", e.getMessage());
        }
        try {
            List<Category> categories = categoryService.getAll();
            model.addAttribute("listCategories", categories);

        } catch (CategoryNotFoundException e) {
            model.addAttribute("categoriesNotFound", e.getMessage());
        }
        return "articles";
    }


    @GetMapping("/delete")
    public String delete(RedirectAttributes redirectAttributes, @RequestParam Long id,
                         @RequestParam int page,
                         @RequestParam String keyword) {
        try {
            articleService.deleteOne(id);
            redirectAttributes.addFlashAttribute("successDeleted", "Article supprimé");
        } catch (ArticleNotDeletedException e) {
            redirectAttributes.addFlashAttribute("notDeleted", e.getMessage());
        }

        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/article")
    public String article(Model model, @RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            Optional<Article> article = articleService.getOne(id);
            article.ifPresent(value -> model.addAttribute("article", value));
        } else {
            model.addAttribute("article", new Article());
        }

        List<Category> categories = categoryService.getAll();
        model.addAttribute("listCategories", categories);

        return "article";
    }

    @PostMapping({"/save", "/save/{id}"})
    public String save(Model model, @Valid Article article, BindingResult bindingResult, @PathVariable(required = false) Long id) {
        if (bindingResult.hasErrors()) {
            List<Category> categories = categoryService.getAll();
            model.addAttribute("listCategories", categories);
            return "article";
        }
        if (id != null) {
            articleService.getOne(id).ifPresent(articleUpdate -> {
                articleUpdate.setBrand(article.getBrand());
                articleUpdate.setDescription(article.getDescription());
                articleUpdate.setPrice(article.getPrice());
                articleUpdate.setCategory(article.getCategory());
                articleService.createOne(articleUpdate);
            });
        } else articleService.createOne(article);
        return "redirect:/index";
    }

    @GetMapping("/articlesByCategory")
    public String getArticlesByCategory(@RequestParam Long categoryId, Model model) {
        List<Article> articles = articleService.findByAttribute(categoryId);
        List<Category> categories = categoryService.getAll();
        model.addAttribute("listCategories", categories);
        model.addAttribute("listArticles", articles);
        model.addAttribute("categoryId", categoryId);
        return "articles";
    }

    @GetMapping("/403")
    public String error() {
        return "403";
    }

}
