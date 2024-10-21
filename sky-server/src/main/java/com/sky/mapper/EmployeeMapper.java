package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * 新增员工
     *
     * @param employee
     */
    void addNewEmployee(Employee employee);


    /**
     * 根据用户名查询员工
     *
     * @param username
     * @return
     */
    Employee getEmployeeByUsername(String username);

    /**
     * 分页查询员工信息
     *
     * @param employeePageQueryDTO
     * @return
     */
    Page<Employee> EmployeePageQuery(EmployeePageQueryDTO employeePageQueryDTO);


    /**
     * 编辑员工信息
     *
     * @param employee
     */
    void update(Employee employee);

    /**
     * 根据id查询员工信息
     *
     * @param id
     * @return
     */
    Employee getEmployeeById(Long id);

}
