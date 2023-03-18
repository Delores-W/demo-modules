package com.delores.medusa.controller;

import com.delores.medusa.pojo.Employee;
import com.delores.medusa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author William
 * @date 4/1/21 10:58 PM
 * @description
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public String getEmployees(Map<String, Object> map) {
        List<Employee> employees = employeeService.getEmployees();
        map.put("emps", employees);
        return "employees";
    }
}
