package com.ggm.cafemanagement.controller;

import com.ggm.cafemanagement.domain.dto.AssignTableDto;
import com.ggm.cafemanagement.domain.dto.CafeTableDto;
import com.ggm.cafemanagement.domain.enums.TableStatusEnum;
import com.ggm.cafemanagement.service.TableService;
import com.ggm.cafemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/table")
public class TableController {

    @Autowired
    private TableService tableService;

    @Autowired
    private UserService userService;

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("newTable", new CafeTableDto());
        model.addAttribute("tableStatuses", TableStatusEnum.values());
        return "/create-table";
    }

    @GetMapping("/assign/form")
    public String assignForm(Model model) {
        model.addAttribute("newAssignTable", new AssignTableDto());
        model.addAttribute("tables", tableService.findAllFree());
        model.addAttribute("waiters", userService.findAllWaiters());
        return "/assign-table";
    }

    @GetMapping
    public String allTables(Model model) {
        model.addAttribute("allTables", tableService.findAll());
        return "/table";
    }

    @GetMapping("/{userId}")
    public String allTablesByUserId(@PathVariable Long userId, Model model) {
        model.addAttribute("allTables", tableService.findAllByUserId(userId));
        return "/table";
    }

    @PostMapping("/create")
    public String createTable(@ModelAttribute("newTable") @Valid CafeTableDto cafeTableDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tableStatuses", TableStatusEnum.values());
            return "/create-table";
        }
        tableService.save(cafeTableDto);
        return "redirect:/home";
    }

    @PostMapping("/assign")
    public String assignTable(@ModelAttribute("newAssignTable") @Valid AssignTableDto assignTableDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tables", tableService.findAllFree());
            model.addAttribute("waiters", userService.findAllWaiters());
            return "/assign-table";
        }

        tableService.assign(assignTableDto.getTableId(), assignTableDto.getUserId());
        return "redirect:/home";
    }

}
