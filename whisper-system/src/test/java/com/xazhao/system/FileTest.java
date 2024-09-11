package com.xazhao.system;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * @Description Created on 2024/08/09.
 * @Author Zhao.An
 */

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class FileTest {

    @Test
    public void createFileTest() {

        // File directory = createFile("E:/Users/Desktop/temp/test/");
        File file = new File("E:\\Users\\Desktop\\", "temp_001.xlsx");
        boolean mkdir = file.mkdir();
        log.info("{}", mkdir);
    }

    /**
     * 创建文件
     *
     * @param targetFile 目标文件
     * @return 文件
     */
    private static File createFile(String targetFile) {
        File targetFilePath = new File(targetFile);
        if (!targetFilePath.getParentFile().exists()) {
            targetFilePath.getParentFile().mkdirs();
        }
        return targetFilePath;
    }

    /**
     * 递归删除指定文件夹下的所有文件
     *
     * @param directory 文件目录
     */
    private static void deleteDirectoryFile(File directory) {
        try {
            if (directory.isDirectory()) {
                File[] fileArray = directory.listFiles();
                if (fileArray != null) {
                    for (File file : fileArray) {
                        deleteDirectoryFile(file);
                        log.info("删除成功！删除文件：" + file);
                    }
                }
            }
            directory.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
