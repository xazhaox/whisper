package com.xazhao.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description Created on 2024/08/22.
 * @Author Zhao.An
 */

public class StreamUtils {

    public static byte[] readInputStream2ByteArray(InputStream inStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        return outStream.toByteArray();
    }
}
