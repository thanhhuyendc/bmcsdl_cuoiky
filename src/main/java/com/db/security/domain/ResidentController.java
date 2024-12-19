package com.db.security.domain;

import com.db.security.config.DataSourceContextHolder;
import com.db.security.config.DatasourceType;
import com.db.security.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/resident")
@RequiredArgsConstructor
public class ResidentController {
    private final ResidentService residentService;
    private final DataSourceContextHolder dataSourceContextHolder;
    @GetMapping("")
    public String getAllResident(Model model, @RequestParam(value = "role", required = false) DatasourceType role, @RequestParam(value = "username", required = false) String username) {
        dataSourceContextHolder.setBranchContext(role);
        model.addAttribute("role", role);
        model.addAttribute("username", username);
        model.addAttribute("residents", residentService.getAllResident());
        return "resident";
    }
}