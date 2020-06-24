package com.liaohongwang.xuxingzuo.fengyinguanli.utils;

/**
 * Created by xuxingzuo on 2017/11/2.
 */

public class Utils {
    public static void runOnBackgroundThread(Runnable runnable){
        new Thread(runnable).start();
    }
    private static final int MIN_CLICK_DELAY_TIME = 250;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }
    public static String jiequshuizi(String str){
        str=str.trim();
        String str2="";
        if(str != null && !"".equals(str)){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)>=48 && str.charAt(i)<=57){
                    str2+=str.charAt(i);
                }
            }
        }
        return str2;
    }
}
