package com.supermarket.utils;

import android.text.TextUtils;

/**
 * Created by chenjunyu on 2016-01-13.
 *
 * 截取 字符串
 */
public class InterceptStringUtils {

    /**
     *  截取 标签 长度
     * @param tag
     * @return
     */
    public static String interceptTag(String tag){
        if(TextUtils.isEmpty(tag)){
            return "";
        }
        if(tag.length() > 4){
            tag = tag.substring(0,4) + "...";
        }
        return tag;
    }

    /**
     * 截取最多显示5个字
     * @param tag
     * @return
     */
    public static String interceptTag5(String tag){
        if(TextUtils.isEmpty(tag)){
            return "";
        }
        if(tag.length() > 5){
            tag = tag.substring(0,5) + "...";
        }
        return tag;
    }
}
