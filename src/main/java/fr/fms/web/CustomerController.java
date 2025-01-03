package fr.fms.web;

import fr.fms.entities.Article;
import fr.fms.entities.Customer;
import fr.fms.service.ArticleServiceImplIservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CustomerController {

    @Autowired
    ArticleServiceImplIservice articleService;

    @GetMapping("/customer")
    public String showCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer";
    }

    @PostMapping("/saveCustomer")
    public String submitCustomerInfo(RedirectAttributes redirectAttributes, Customer customer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            Customer customer1 = new Customer();
            model.addAttribute("customer", customer1);
            return "customer";
        }
        redirectAttributes.addFlashAttribute("customer", customer);
        return "redirect:/order";
    }

    @GetMapping("/order")
    public String showOrderForm(Model model) {
        Map<Long, Article> cart = articleService.getCart();
        List<Article> articles = new ArrayList<>(cart.values());
        model.addAttribute("cartList", articles);
        return "order";
    }

}
