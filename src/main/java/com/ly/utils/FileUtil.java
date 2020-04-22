package com.ly.utils;

import java.io.File;

public class FileUtil {
	public static int createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            return 0;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            return 1;
        } else {
            return -1;
        }
    }

}
