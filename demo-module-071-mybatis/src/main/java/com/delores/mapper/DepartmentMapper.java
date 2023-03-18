package com.delores.mapper;

import com.delores.pojo.Department;

/**
 * @author William
 * @date 3/28/21 6:13 PM
 * @description
 */
public interface DepartmentMapper {

    Department getDepartment(long id);

    Department getDepartmentPlus(long id);
}
