package com.delores.medusa.dao;

import com.delores.medusa.pojo.Employee;

import java.util.List;

/**
 * @author William
 * @date 4/1/21 11:33 PM
 * @description
 */
public interface EmployeeMapper {

    List<Employee> getEmployees();
}
