package com.delores.mapper;

import com.delores.pojo.Employee;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author William
 * @date 3/27/21 2:24 AM
 * @description
 */
public interface EmployeeMapper {

    @Select("select * from employee where id = #{id}")
//    @Select("select * from delores_schema.employee where id = #{id}")
    Employee getEmployee(long id);

    Employee findOne(Employee employee);

    Employee getEmployeeBy(Employee employee);

    List<Employee> getEmployees();

    List<Employee> getEmployeesByDept();

    List<Employee> getEmployeeWithDept();

    Employee getEmployeeByName(@Param("id") long id, @Param("lastName") String lastName);

    String getEmail(long id);

    boolean addOne(Employee employee);

    boolean updateOne(Employee employee);

    Integer deleteOne(Employee employee);

//    增删改的返回值，可以直接使用 boolean Integer Long , 不用在sql映射文件中指定resultType !!!!
}
