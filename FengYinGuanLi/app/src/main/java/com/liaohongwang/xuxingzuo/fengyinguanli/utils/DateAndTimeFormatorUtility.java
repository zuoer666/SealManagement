package com.liaohongwang.xuxingzuo.fengyinguanli.utils;

import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lenovo on 16/3/8.
 */
public class DateAndTimeFormatorUtility {

    /*
    时间与字符串的转化
     */
    public final static int DATEANDTIMECOMM=0;
    public final static int DATEANDTIMECOMMCN=1;
    public final static int DATEANDTIMENOSEC=2;
    public final static int DATEANDTIMENOSECCN=3;
    public final static int DATECOMM=4;
    public final static int DATECOMMCN=5;
    public final static int DATECOMMNOMONTH=6;
    public final static int DATECOMMNOMONTHCN=7;
    public final static int TIMENODATE=8;
    public final static int TIMENODATECN=9;


    public  static  String DateTimeToString(Date _date,int _dateStyle){
        SimpleDateFormat format=setStyle(_dateStyle);
        String dateResult="";
        dateResult=format.format(_date);
        return dateResult;
    }
    public  static  String CalendarToString(Calendar _calendar,int _dateStyle){
        return DateAndTimeFormatorUtility.DateTimeToString(_calendar.getTime(),_dateStyle);
    }
    public  static  Date StringToDateTime(String _strDate,int _dateStyle) throws Exception {
        SimpleDateFormat format=setStyle(_dateStyle);
        Date resultDate=format.parse(_strDate);
        return resultDate;
    }
    public  static  String StringToStrDateTimePicker(DatePicker datePicker,TimePicker timePicker,int _dateStyle) throws Exception {
        String dateStr=datePicker.getYear()+"-"+datePicker.getMonth()+"-"+datePicker.getDayOfMonth();
        String timeStr=timePicker.getCurrentHour()+":"+timePicker.getCurrentMinute();
        String result=dateStr+" "+timeStr;

        SimpleDateFormat format=setStyle(DATEANDTIMENOSEC);
        Date resultDate=format.parse(result);
        return DateTimeToString(resultDate,_dateStyle);
    }

    private  static SimpleDateFormat setStyle(int _dateStyle){
        SimpleDateFormat format=null;
        switch (_dateStyle) {
            case DATEANDTIMECOMM:
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
            case DATEANDTIMECOMMCN:
                format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
                break;
            case DATEANDTIMENOSEC:
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                break;
            case DATEANDTIMENOSECCN:
                format = new SimpleDateFormat("yyyy年MM月dd HH时mm分");
                break;
            case DATECOMM:
                format = new SimpleDateFormat("yyyy-MM-dd");
                break;
            case DATECOMMCN:
                format = new SimpleDateFormat("yyyy年MM月dd日");
                break;
            case DATECOMMNOMONTH:
                format = new SimpleDateFormat("yyyy-MM");
                break;
            case DATECOMMNOMONTHCN:
                format = new SimpleDateFormat("yyyy年MM月");
                break;
            case TIMENODATE:
                format = new SimpleDateFormat("HH:mm");
                break;
            case TIMENODATECN:
                format = new SimpleDateFormat("HH时mm分");
                break;

            default://
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
        }
        return format;
    }

}
