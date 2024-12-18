package fr.fms.web;

import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;


    @GetMapping("/index")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        Page<Article> articles = articleRepository.findAll(PageRequest.of(page, 5));
        model.addAttribute("listArticles", articles.getContent());
        model.addAttribute("page", IntStream.range(0, articles.getTotalPages()).boxed().collect(Collectors.toList()));
        model.addAttribute("currentPage", page);
        return "articles";
    }


}
