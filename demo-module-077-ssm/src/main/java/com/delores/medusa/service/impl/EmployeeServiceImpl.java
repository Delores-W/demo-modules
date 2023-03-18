package com.delores.medusa.service.impl;

import com.delores.medusa.dao.EmployeeMapper;
import com.delores.medusa.pojo.Employee;
import com.delores.medusa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author William
 * @date 4/1/21 11:31 PM
 * @description
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> getEmployees() {
        return employeeMapper.getEmployees();
    }
}
