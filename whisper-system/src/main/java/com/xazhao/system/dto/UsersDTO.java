package com.xazhao.system.dto;

import com.xazhao.system.entity.Users;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description Created on 2024/08/12.
 * @Author Zhao.An
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UsersDTO extends Users {

    private static final long serialVersionUID = 1L;

}
