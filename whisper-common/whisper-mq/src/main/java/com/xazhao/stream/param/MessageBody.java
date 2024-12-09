package com.xazhao.stream.param;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 消息体
 *
 * @Description Created on 2024/12/09.
 * @Author Zhao.An
 */

@Data
@Accessors(chain = true)
public class MessageBody {

    /**
     * 幂等号
     */
    private String identifier;

    /**
     * 消息体
     */
    private String body;
}
