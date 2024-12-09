package com.xazhao.stream.param;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 消息
 *
 * @Description Created on 2024/12/09.
 * @Author Zhao.An
 */

@Data
@Accessors(chain = true)
public class Message {

    /**
     * 消息id
     */
    private String messageId;

    /**
     * 消息体
     */
    private String body;
}
