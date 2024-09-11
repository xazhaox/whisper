package com.xazhao.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description Created on 2024/08/12.
 * @Author Zhao.An
 */

@Data
@Entity
@Table(name = "sys_users")
public class Users implements Serializable {

    private static final long serialVersionUID = 373166060830385876L;

    /**
     * 主键ID（自增主键）
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 用户昵称
     */
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 用户代码
     */
    @Column(name = "nick_code")
    private String nickCode;

    /**
     * 密码哈希
     */
    @Column(name = "password_hash")
    private String passwordHash;

    /**
     * 盐值，配合密码使用
     */
    @Column(name = "salt")
    private String salt;

    /**
     * 手机号码
     */
    @Column(name = "telephone")
    private String telephone;

    /**
     * 邮件地址
     */
    @Column(name = "email")
    private String email;

    /**
     * 用户头像URL
     */
    @Column(name = "profile_photo_url")
    private String profilePhotoUrl;

    /**
     * 实名认证状态（TRUE或FALSE）
     */
    @Column(name = "certification")
    private Integer certification;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 身份证号
     */
    @Column(name = "id_card_no")
    private String idCardNo;

    /**
     * 用户角色
     */
    @Column(name = "user_role")
    private String userRole;

    /**
     * 所属机构
     */
    @Column(name = "place_org")
    private String placeOrg;

    /**
     * 所属部门
     */
    @Column(name = "place_dept")
    private String placeDept;

    /**
     * 用户可操做模块
     */
    @Column(name = "duty_block")
    private String dutyBlock;

    /**
     * 用户状态（ACTIVE：活动表示启用，FROZEN：冻结表示停用）
     */
    @Column(name = "state")
    private String state;

    /**
     * 是否逻辑删除，0为未删除，非0为已删除
     */
    @Column(name = "deleted")
    private Long deleted;

    /**
     * 最后登录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 乐观锁版本号
     */
    @Column(name = "lock_version")
    private Long lockVersion;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "gmt_create")
    private Date gmtCreate;

    /**
     * 最后更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "gmt_modified")
    private Date gmtModified;

    /**
     * 备用字段
     */
    @Column(name = "temp")
    private String temp;

}

