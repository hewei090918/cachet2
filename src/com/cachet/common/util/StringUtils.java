package com.cachet.common.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {


    public static String formatDate(Date date, boolean withSeconds) {
        DateFormat formatWithSeconds = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat formatWithoutSeconds = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (date == null)
            return "";
        else if (withSeconds)
            return formatWithSeconds.format(date);
        else
            return formatWithoutSeconds.format(date);
    }

    /**
     * 返回字符长的length长度，折叠
     * @param str
     * @param length
     * @return
     */
    public static String subStringStr(String str,Integer length){
        String val = "";
        if(str.length()>length){
            val = str.substring(0,length)+"...";
        }else{
            val = str;
        }
        return val;
    }

    public static boolean isNullOrEmpty(String text){
        if (text == null)
            return  true;
        text = text.trim();
        if (text.equals(""))
            return true;
        else
            return false;
    }

    public static boolean isNotNullOrEmpty(String text){
        if(isNullOrEmpty(text))
            return false;
        else
            return true;
    }

}
