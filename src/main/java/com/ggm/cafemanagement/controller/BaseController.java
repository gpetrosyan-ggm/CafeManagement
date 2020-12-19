package com.ggm.cafemanagement.controller;

import com.ggm.cafemanagement.domain.dto.UserDto;
import com.ggm.cafemanagement.domain.enums.RoleEnum;
import com.ggm.cafemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class BaseController {

    @Autowired
    private UserService userService;

    @GetMapping({"/", "/login"})
    public String login() {
        return "/login";
    }

    @GetMapping({"/home"})
    public String home() {
        UserDto userDto = userService.findAuthUser();

        if (RoleEnum.MANAGER == userDto.getRole()) {
            return "redirect:/user";
        }
        if (RoleEnum.WAITER == userDto.getRole()) {
            return "redirect:/table/" + userDto.getId();
        }

        return "/login";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

}
