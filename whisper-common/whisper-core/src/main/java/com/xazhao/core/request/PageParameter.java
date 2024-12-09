package com.xazhao.core.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description Created on 2024/08/30.
 * @Author Zhao.An
 */

@Setter
@Getter
public class PageParameter extends BaseRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 每页显示数量
     */
    private Integer pageSize;
}
