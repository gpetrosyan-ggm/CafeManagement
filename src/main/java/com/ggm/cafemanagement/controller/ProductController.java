package com.ggm.cafemanagement.controller;

import com.ggm.cafemanagement.domain.dto.ProductDto;
import com.ggm.cafemanagement.domain.enums.ProductCategoryEnum;
import com.ggm.cafemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("newProduct", new ProductDto());
        model.addAttribute("productCategories", ProductCategoryEnum.values());
        return "/create-product";
    }

    @GetMapping
    public String allProducts(Model model) {
        model.addAttribute("allProducts", productService.findAll());
        return "/product";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("newProduct") @Valid ProductDto productDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productCategories", ProductCategoryEnum.values());
            return "/create-product";
        }
        productService.save(productDto);
        return "redirect:/product";
    }

}
