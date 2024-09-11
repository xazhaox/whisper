package com.xazhao.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description Created on 2024/08/21.
 * @Author Zhao.An
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginUser {

    private String token;
}
