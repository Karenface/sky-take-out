package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeEditPasswordDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.EmployeeMapper;
import com.sky.result.PageResult;
import com.sky.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getEmployeeByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        password = DigestUtils.md5DigestAsHex(password.getBytes()); // 前端明文传过来的密码进行MD5加密，然后比对
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus().equals(StatusConstant.DISABLE)) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 新增员工
     *
     * @param employeeDTO
     */
    @Override
    public void addNewEmployee(EmployeeDTO employeeDTO) {

        // 创建emp实体对象，接受并传入mapper层
        Employee employee = new Employee();

        // 对象属性拷贝
        BeanUtils.copyProperties(employeeDTO, employee);// 参数一：被拷贝对象 参数二：拷贝对象

        // 默认状态：启用
        employee.setStatus(StatusConstant.ENABLE);
        // 默认密码：123456
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        employeeMapper.addNewEmployee(employee);

    }

    /**
     * 员工分页查询
     *
     * @param employeePageQueryDTO
     * @return
     */
    @Override
    public PageResult EmployeePageQuery(EmployeePageQueryDTO employeePageQueryDTO) {

        // 开始分页查询
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());

        // 调用mapper层返回分页查询结果
        Page<Employee> page = employeeMapper.EmployeePageQuery(employeePageQueryDTO);

        // 获取总记录数和数据
        long total = page.getTotal();
        List<Employee> employeeList = page.getResult();

        return new PageResult(total, employeeList);
    }

    /**
     * 根据id查询员工信息
     *
     * @param id
     * @return
     */
    @Override
    public Employee getEmployeeById(Long id) {

        return employeeMapper.getEmployeeById(id);

    }

    /**
     * 编辑员工信息
     *
     * @param employeeDTO
     */
    @Override
    public void editEmployeeInfo(EmployeeDTO employeeDTO) {

        Employee employee = new Employee();

        BeanUtils.copyProperties(employeeDTO, employee);

        employeeMapper.update(employee);

    }

    /**
     * 启用或禁用员工账号
     *
     * @param status
     * @param id
     */
    @Override
    public void enableOrDisableEmployeeAccounts(Integer status, Long id) {

        Employee employee = Employee.builder()
                .status(status)
                .id(id)
                .build();

        employeeMapper.update(employee);
    }

    /**
     * 修改密码
     *
     * @param employeeEditPasswordDTO
     */
    @Override
    public void editPassword(EmployeeEditPasswordDTO employeeEditPasswordDTO) {

        // 获取当前员工
        Long empId = BaseContext.getCurrentId();
        Employee employee = employeeMapper.getEmployeeById(empId);

        // 获取前端传入的加密后的密码
        String newPassword = DigestUtils.md5DigestAsHex(employeeEditPasswordDTO.getNewPassword().getBytes());
        String oldPassword = DigestUtils.md5DigestAsHex(employeeEditPasswordDTO.getOldPassword().getBytes());

        if (oldPassword.equals(employee.getPassword())) {
            if (newPassword.isEmpty() || !newPassword.equals(employee.getPassword())) {
                employee.setPassword(newPassword);
                employeeMapper.update(employee);
            }
        }

    }

}
