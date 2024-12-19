package com.db.security.domain;

import com.db.security.config.DataSourceContextHolder;
import com.db.security.config.DatasourceType;
import com.db.security.mapper.AuditMapper;
import com.db.security.model.UserRequestEntity;
import com.db.security.model.enum_type.Gender;
import com.db.security.repository.AuditRepository;
import com.db.security.request.PassportRequest;
import com.db.security.request.UserRequest;
import com.db.security.request.UserSearchRequest;
import com.db.security.response.AuditResponse;
import com.db.security.response.ResidentResponse;
import com.db.security.response.UserResponse;
import com.db.security.service.PassportService;
import com.db.security.service.ResidentService;
import com.db.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;
import java.time.LocalDate;
import java.util.UUID;
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final ResidentService residentService;
    private final PassportService passportService;
    private  final AuditRepository auditRepository;
    private final DataSourceContextHolder dataSourceContextHolder;
    private final AuditMapper auditMapper;

    @GetMapping("")
    public String showForm(Model model) {
        dataSourceContextHolder.setBranchContext(DatasourceType.NGUOI_DUNG);
        model.addAttribute("locations", userService.getListLocation());
        return "user_request_form";
    }

    @PostMapping("")
    public String submitForm(@ModelAttribute UserRequest request,
                             @RequestParam(required = false) Long residentID, // Thêm residentID
                             Model model) {
        dataSourceContextHolder.setBranchContext(DatasourceType.NGUOI_DUNG);
        if(residentID == null) {
            ResidentResponse residentResponse = residentService.findOneResident(request.getCCCD());
            if(residentResponse == null) {
                model.addAttribute("isSuccess", false);
                model.addAttribute("message", "Resident not found");
                return "user_request_form";
            }
            residentID = residentResponse.getId();
            List<String> passportIDs = passportService.findPassportIDsByResidentId(residentID);
            if (!passportIDs.isEmpty()) {
                StringBuilder message = new StringBuilder("CCCD đã được sử dụng cho passport ");
                for (int i = 0; i < passportIDs.size(); i++) {
                    message.append(passportIDs.get(i));
                    if (i < passportIDs.size() - 1) {
                        message.append(", ");
                    }
                }
                message.append(". Bạn muốn tạo tiếp hay quay lại?");
                model.addAttribute("message", message.toString());
                model.addAttribute("residentID", residentID);
                String gender;
                if(request.getGender() == Gender.Male){
                    gender = "Male";
                } else if (request.getGender() == Gender.Female) {
                    gender = "Female";
                }else {
                    gender = "None";
                }
                model.addAttribute("request", request);
                model.addAttribute("gender", gender);
                model.addAttribute("locations", userService.getListLocation());
                return "user_request_form";
            }
        }

        PassportRequest passportRequest = new PassportRequest();
        passportRequest.setResidentId(residentID);
        String passportID = UUID.randomUUID().toString().replace("-", "").substring(0, 7);
        passportRequest.setPassportID(passportID);
        passportRequest.setStartDate(LocalDate.now());
        passportRequest.setEndDate(LocalDate.now().plusYears(2));
        passportRequest.setIsXD(0);
        long ID =  passportService.addPassport(passportRequest);
        if(ID< 0){
            model.addAttribute("message", "Lỗi khi đăng ký");
            return "user_request_form";
        }
        request.setPassportId(passportID);
        userService.addUser(request);
        model.addAttribute("isSuccess", true);
        model.addAttribute("message", "Your passportID: " + passportID);
        return "user_request_form";
    }

    @GetMapping("/search")
    public String searchUser(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        dataSourceContextHolder.setBranchContext(DatasourceType.NGUOI_DUNG);
        if (!Objects.isNull(keyword)) {
            UserResponse userResponse = userService.getUser(keyword);
            if(userResponse == null) {
                userResponse = new UserResponse();
                userResponse.setCCCD("");
                userResponse.setFullName("");
                userResponse.setGender(Gender.None);
                userResponse.setPhone("");
                userResponse.setEmail("");
                userResponse.setIsApprove(-2);
                userResponse.setIsRejected(-2);
                userResponse.setIsAuthenticated(-2);
                userResponse.setNote("");
            }
            model.addAttribute("user", userResponse);

        } else {
            UserResponse userResponse = new UserResponse();
            userResponse.setCCCD("");
            userResponse.setFullName("");
            userResponse.setGender(Gender.None);
            userResponse.setPhone("");
            userResponse.setEmail("");
            userResponse.setIsApprove(-2);
            userResponse.setIsRejected(-2);
            userResponse.setIsAuthenticated(-2);
            userResponse.setNote("");
            model.addAttribute("user",userResponse);
        }
        return "user_details";
    }
    @GetMapping("/view-all")
    public String getAllUser(Model model, @RequestParam(value = "role", required = false) DatasourceType role, @RequestParam(value = "username", required = false) String username) {
        dataSourceContextHolder.setBranchContext(role);
        model.addAttribute("role", role);
        model.addAttribute("username", username);
        if(dataSourceContextHolder.getBranchContext() == DatasourceType.LUU_TRU){
            model.addAttribute("passports", passportService.getAllPassport());
            return "passport";
        }
        if(dataSourceContextHolder.getBranchContext() == DatasourceType.GIAM_SAT){
            List<AuditResponse> audits = auditRepository.findAll().stream().map(auditMapper::toResponse).toList();
            model.addAttribute("audits", audits);
            return "giamsat";
        }
        model.addAttribute("users", userService.getAllUser());
        return "list_user";
    }

    @GetMapping("/xt-approve")
    public String xacThucApprove(RedirectAttributes redirectAttributes, @RequestParam(value = "role", required = false) DatasourceType role, @RequestParam(value = "username", required = false) String username, @RequestParam(value = "id", required = false) Integer id) {
        dataSourceContextHolder.setBranchContext(role);
        redirectAttributes.addAttribute("role", role);
        redirectAttributes.addAttribute("username", username);
        DatasourceType datasourceType = dataSourceContextHolder.getBranchContext();
        if (datasourceType == DatasourceType.XAC_THUC) {
            userService.Approve(id);
        }else if (datasourceType == DatasourceType.XET_DUYET) {
            userService.Authen(id);
        }
        return "redirect:/user/view-all";
    }
    @PostMapping("/xt-reject")
    public String xacThucReject(RedirectAttributes redirectAttributes,
                                @RequestParam(value = "role", required = false) String role,
                                @RequestParam(value = "username", required = false) String username,
                                @RequestParam(value = "id", required = false) Integer id,
                                @RequestParam(value = "note", required = false) String note) {
        System.out.println("id: " + id);
        System.out.println("username: " + username);
        System.out.println("role: " + role);
        System.out.println("note: " + note);

        // Bạn có thể giữ lại các logic sau
        if ("XAC_THUC".equals(role)) {
            dataSourceContextHolder.setBranchContext(DatasourceType.XAC_THUC);
            userService.NoApprove(id,note);
        } else if ("XET_DUYET".equals(role)) {
            dataSourceContextHolder.setBranchContext(DatasourceType.XET_DUYET);
            userService.NoAuthen(id,note);
        }
        return "redirect:/user/view-all";
    }

}