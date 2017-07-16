package com.yanglin.demo2.utils;

import android.os.Environment;

import java.io.File;

/**
 * 作者 Administrator
 * 时间   2017/7/15 0015 15:39.
 * 描述   文件相关的工具类
 */

public class FileUtils {

    /**
     * 获取图片的缓存目录
     *
     * @return 图片的缓存目录
     */
    public static File getCacheDir(String ROOT_DIR) {

        String dir = null;
        if (isMounted()) {//检查sd卡是否存在并装好
            dir = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            dir = Environment.getDataDirectory().getAbsolutePath();
        }
        //检查缓存目录是否存在
        File abRootDir = new File(dir, ROOT_DIR);
        if (!abRootDir.exists()) {
            if (!abRootDir.mkdirs()) {
                abRootDir = new File(dir);
            }
            //            DebugLog.w(b?"目录创建成功":"目录创建失败");
        }
        //创建分目录
        //        if(abRootDir.listFiles().length > 50){
        //
        //        }
        return abRootDir;
    }
    /**
     * 判断sd卡是否装好
     *
     * @return
     */
    public static boolean isMounted() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }
}
