package com.sky.dto;

import lombok.Data;


@Data
public class EmployeeEditPasswordDTO {

    private Integer empId;
    private String newPassword;
    private String oldPassword;

}
