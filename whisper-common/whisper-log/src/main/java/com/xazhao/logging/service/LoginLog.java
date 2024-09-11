package com.xazhao.logging.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description Created on 2024/08/22.
 * @Author Zhao.An
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "sys_login_log")
public class LoginLog implements Serializable {

    private static final long serialVersionUID = 705970776065348214L;

    /**
     * 主键
     */
    @Id
    @Column(name = "login_id")
    private Long loginId;

    /**
     * 用户编号
     */
    @Column(name = "user_code")
    private String userCode;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 登录状态 登录状态 SUCCESS 成功 FAIL 失败
     */
    @Column(name = "status")
    private String status;

    /**
     * 登录IP地址
     */
    @Column(name = "ip_address")
    private String ipAddress;

    /**
     * 登录地点
     */
    @Column(name = "login_location")
    private String loginLocation;

    /**
     * 浏览器类型
     */
    @Column(name = "browser")
    private String browser;

    /**
     * 操作系统
     */
    @Column(name = "os")
    private String os;

    /**
     * 提示消息
     */
    @Column(name = "msg")
    private String msg;

    /**
     * 引擎类型
     */
    @Column(name = "engine")
    private String engine;

    /**
     * 是否为移动平台
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 平台类型
     */
    @Column(name = "platform")
    private String platform;

    /**
     * 访问时间（创建时间）
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "login_time")
    private Date loginTime;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

}

