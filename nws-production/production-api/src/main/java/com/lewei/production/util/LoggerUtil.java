package com.lewei.production.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by qwz on 2017/1/6 1:00.
 * Project lewei-parent
 * Package com.wittur.util
 *
 * @Doc 自定义日志工具
 */
public class LoggerUtil {

    /**
     * @category 获取try-catch中的异常内容
     * @param e Exception
     * @return  异常内容
     * */
    public static String getException(Exception e) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        e.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
            out.close();
        } catch (Exception ex) {

        }
        return ret;
    }
}
