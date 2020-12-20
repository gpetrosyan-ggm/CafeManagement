package com.ggm.cafemanagement.controller;

import com.ggm.cafemanagement.domain.dto.ProductInOrderDto;
import com.ggm.cafemanagement.domain.enums.ProductInOrderStatusEnum;
import com.ggm.cafemanagement.service.ProductInOrderService;
import com.ggm.cafemanagement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/product-in-order")
public class ProductInOrderController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductInOrderService productInOrderService;

    @GetMapping("/form/{orderId}")
    public String form(@PathVariable Long orderId, Model model) {
        ProductInOrderDto productInOrder = new ProductInOrderDto();
        productInOrder.setOrderId(orderId);
        model.addAttribute("newProductInOrder", productInOrder);
        model.addAttribute("products", productService.findAll());
        return "/create-product-in-order";
    }

    @GetMapping("/edit/form/{productInOrderId}")
    public String editForm(@PathVariable Long productInOrderId, Model model) {
        ProductInOrderDto productInOrderDto = productInOrderService.findById(productInOrderId);
        model.addAttribute("productInOrder", productInOrderDto);
        model.addAttribute("orderId", productInOrderDto.getOrderId());
        model.addAttribute("productInOrderStatuses", ProductInOrderStatusEnum.values());
        return "/edit-product-in-order";
    }


    @GetMapping("/{orderId}")
    public String allByOrderId(@PathVariable Long orderId, Model model) {
        model.addAttribute("userId", productInOrderService.findUserId(orderId));
        model.addAttribute("orderId", orderId);
        model.addAttribute("allProductInOrders", productInOrderService.findAllByOrderId(orderId));
        return "/product-in-order";
    }

    @PostMapping("/create")
    public String createProductInOrder(@ModelAttribute("newProductInOrder") @Valid ProductInOrderDto productInOrderDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("products", productService.findAll());
            return "/create-product-in-order";
        }
        model.addAttribute("orderId", productInOrderDto.getOrderId());
        productInOrderService.create(productInOrderDto);
        return "redirect:/product-in-order/" + productInOrderDto.getOrderId();
    }

    @PostMapping("/edit")
    public String editProductInOrder(@ModelAttribute("productInOrder") @Valid ProductInOrderDto productInOrderDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productInOrderStatuses", ProductInOrderStatusEnum.values());
            return "/edit-product-in-order";
        }
        model.addAttribute("orderId", productInOrderDto.getOrderId());
        productInOrderService.update(productInOrderDto);
        return "redirect:/product-in-order/" + productInOrderDto.getOrderId();
    }

}
