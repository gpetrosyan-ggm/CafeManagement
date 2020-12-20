package com.ggm.cafemanagement.controller;

import com.ggm.cafemanagement.domain.dto.UserDto;
import com.ggm.cafemanagement.domain.enums.RoleEnum;
import com.ggm.cafemanagement.service.UserService;
import com.ggm.cafemanagement.util.SecurityHelper;
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
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("newUser", new UserDto());
        model.addAttribute("roles", RoleEnum.values());
        return "/create-user";
    }

    @GetMapping
    public String allUsers(Model model) {
        SecurityHelper.retrieveUserName();
        model.addAttribute("allUsers", userService.findAll());
        return "/user";
    }

    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute("newUser") UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("roles", RoleEnum.values());
            return "/create-user";
        }
        userService.save(userDto);
        return "redirect:/home";
    }

}
