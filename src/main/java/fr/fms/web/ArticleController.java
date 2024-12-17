package fr.fms.web;

import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;


    @RequestMapping("/index")
    public String index (Model model) {
          List<Article> articles = articleRepository.findAll();
          model.addAttribute("listArticles", articles);
        return "articles";
    }

}
