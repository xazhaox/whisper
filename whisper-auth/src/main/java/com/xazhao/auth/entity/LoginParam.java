package com.xazhao.auth.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @Description Created on 2024/08/20.
 * @Author Zhao.An
 */

@Data
public class LoginParam {

    @NotBlank(message = "用户名不能为空")
    private String userCode;

    @NotBlank(message = "密码不能为空")
    @Size(min = 8, max = 16, message = "请输入长度在8-16位的密码")
    private String password;
}
