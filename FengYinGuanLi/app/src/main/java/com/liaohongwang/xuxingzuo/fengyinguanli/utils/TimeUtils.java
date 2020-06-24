package com.liaohongwang.xuxingzuo.fengyinguanli.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

	public static String getTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}
}
