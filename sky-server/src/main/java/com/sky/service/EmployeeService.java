package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeEditPasswordDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

import java.util.List;

public interface EmployeeService {

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     *
     * @param employeeDTO
     * @return
     */
    void addNewEmployee(EmployeeDTO employeeDTO);

    /**
     * 员工分页查询
     *
     * @param employeePageQueryDTO
     * @return
     */
    PageResult EmployeePageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 编辑员工信息
     *
     * @param employeeDTO
     */
    void editEmployeeInfo(EmployeeDTO employeeDTO);

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    Employee getEmployeeById(Long id);


    /**
     * 启用或禁用员工账号
     *
     * @param status
     * @param id
     */
    void enableOrDisableEmployeeAccounts(Integer status, Long id);

    /**
     * 修改密码
     *
     * @param employeeEditPasswordDTO
     */
    void editPassword(EmployeeEditPasswordDTO employeeEditPasswordDTO);
}
