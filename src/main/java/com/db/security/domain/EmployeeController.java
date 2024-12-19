package com.db.security.domain;

import com.db.security.config.DataSourceContextHolder;
import com.db.security.config.DatasourceType;
import com.db.security.request.EmployeeRequest;
import com.db.security.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DataSourceContextHolder dataSourceContextHolder;
    @GetMapping("")
    public String getLogin(@RequestParam(value = "message", required = false) String message, Model model) {
        if (message != null) {
            model.addAttribute("message", message);
        }
        return "login";
    }

    @PostMapping("")
    public String postLogin(
            Model model,
            @ModelAttribute EmployeeRequest request,
            RedirectAttributes redirectAttributes
    ) {
        dataSourceContextHolder.setBranchContext(DatasourceType.NGUOI_DUNG);

        // Kiểm tra username có thuộc danh sách ['XACTHUC', 'XETDUYET', 'LUUTRU', 'GIAMSAT']
        if (List.of("XACTHUC", "XETDUYET", "LUUTRU", "GIAMSAT").contains(request.getUsername())) {
            String employee = employeeService.getEmployee(request);
            redirectAttributes.addAttribute("username", employee);
            redirectAttributes.addAttribute("role", dataSourceContextHolder.getBranchContext());
            if (dataSourceContextHolder.getBranchContext()==  DatasourceType.NGUOI_DUNG) {
                redirectAttributes.addAttribute("message","No privilege for user");
                return "redirect:/login";
            }
            return "redirect:/user/view-all";
        } else {
            redirectAttributes.addAttribute("message","No privilege for user");
            return "redirect:/login";
        }
    }
}
