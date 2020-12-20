package com.ggm.cafemanagement.controller;

import com.ggm.cafemanagement.domain.dto.OrderDto;
import com.ggm.cafemanagement.domain.enums.OrderStatusEnum;
import com.ggm.cafemanagement.service.OrderService;
import com.ggm.cafemanagement.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private TableService tableService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("newOrder", new OrderDto());
        model.addAttribute("tables", tableService.findAllByOrderStatus());
        return "/create-order";
    }

    @GetMapping("/edit/form/{orderId}")
    public String editForm(@PathVariable Long orderId, Model model) {
        model.addAttribute("order", orderService.findById(orderId));
        model.addAttribute("orderStatuses", OrderStatusEnum.values());
        return "/edit-order";
    }


    @GetMapping("/{tableId}")
    public String allOrdersByTableId(@PathVariable Long tableId, Model model) {
        model.addAttribute("allOrders", orderService.findAllByTableId(tableId));
        return "/order";
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute("newOrder") @Valid OrderDto orderDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tables", tableService.findAllByOrderStatus());
            return "/create-order";
        }
        orderService.create(orderDto);
        return "redirect:/home";
    }

    @PostMapping("/edit")
    public String editOrder(@ModelAttribute("order") @Valid OrderDto orderDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("orderStatuses", OrderStatusEnum.values());
            return "/edit-order";
        }
        orderService.update(orderDto);
        return "redirect:/home";
    }

}
