package com.db.security.domain;

import com.db.security.config.DataSourceContextHolder;
import com.db.security.config.DatasourceType;
import com.db.security.repository.PassportRepository;
import com.db.security.service.PassportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@Controller
@RequestMapping("/passport")
public class PassportController {
    private final PassportService passportService;
    private final DataSourceContextHolder dataSourceContextHolder;

    @GetMapping("")
    private String getAlLPassport(Model model, @RequestParam(value = "role", required = false) DatasourceType role, @RequestParam(value = "username", required = false) String username) {
        dataSourceContextHolder.setBranchContext(role);
        model.addAttribute("role", role);
        model.addAttribute("username", username);
        model.addAttribute("passports", passportService.getAllPassport());
        return "passport";
    }

    @PostMapping("/{id}")
    public String updateEndDate(@PathVariable long id, @ModelAttribute("endDate") LocalDate endDate, @ModelAttribute("keyword") String keyword, Model model) {
//        passportService.updateEnddate(id, endDate);
//        model.addAttribute("keyword", keyword);
//        model.addAttribute("passports", passportService.getListLuuTru_Passport());
        return "passport";
    }
}