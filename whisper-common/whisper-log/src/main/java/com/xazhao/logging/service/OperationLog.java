package com.xazhao.logging.service;

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
 * @Description Created on 2024/08/22.
 * @Author Zhao.An
 */

@Data
@Entity
@Table(name = "sys_operation_log")
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 126568113895773748L;

    /**
     * 日志主键
     */
    @Id
    @Column(name = "log_id")
    private Long logId;

    /**
     * 模块标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @Column(name = "business_type")
    private Integer businessType;

    /**
     * 方法名称
     */
    @Column(name = "method")
    private String method;

    /**
     * 请求方式
     */
    @Column(name = "request_method")
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    @Column(name = "operator_type")
    private Integer operatorType;

    /**
     * 操作人员
     */
    @Column(name = "operation_name")
    private String operationName;

    /**
     * 部门名称
     */
    @Column(name = "dept_name")
    private String deptName;

    /**
     * 请求URL
     */
    @Column(name = "operation_url")
    private String operationUrl;

    /**
     * 主机地址
     */
    @Column(name = "operation_ip")
    private String operationIp;

    /**
     * 操作地点
     */
    @Column(name = "operation_location")
    private String operationLocation;

    /**
     * 请求参数
     */
    @Column(name = "operation_param")
    private String operationParam;

    /**
     * 返回参数
     */
    @Column(name = "json_result")
    private String jsonResult;

    /**
     * 操作状态（0正常 1异常）
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 错误消息
     */
    @Column(name = "error_msg")
    private String errorMsg;

    /**
     * 操作时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "operation_time")
    private Date operationTime;

}

