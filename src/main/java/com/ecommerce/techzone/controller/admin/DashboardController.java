package com.ecommerce.techzone.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class DashboardController {
    @GetMapping("dashboard")
    public String viewDashboard(){
        return "admin/admin_dashboard";
    }


}
