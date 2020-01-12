package com.bill.test.utils;

import com.google.common.annotations.VisibleForTesting;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.utils
 * @Description: TODO
 * @date Date : 2019年12月09日 10:57
 */
public class DateUtils {
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 格式化String时间
     * @param time String类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的Date日期
     */
    public static Date parseStrToDate(String time, String timeFromat) {
        if (time == null || time.equals("")) {
            return null;
        }

        Date date=null;
        try{
            DateFormat dateFormat=new SimpleDateFormat(timeFromat);
            date=dateFormat.parse(time);
        }catch(Exception e){

        }
        return date;
    }

    /**
     * 格式化Date时间
     * @param time Date类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date time, String timeFromat){
        DateFormat dateFormat=new SimpleDateFormat(timeFromat);
        return dateFormat.format(time);
    }

    public static List<String> getMonthListOfDateV2(String minDate, String maxDate) {
        ArrayList<String> result = new ArrayList<String>();
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(DateUtils.parseStrToDate(minDate, "yyyy-MM"));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
        max.setTime(DateUtils.parseStrToDate(maxDate, "yyyy-MM"));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(DateUtils.parseDateToStr((curr.getTime()), "yyyy-MM"));
            curr.add(Calendar.MONTH, 1);
        }
        return result;
    }


    /**
     * 获取随机时分秒
     * @return
     */
    public static String randomOrderTime(){
        StringBuilder sb = new StringBuilder();
        double randomNumber;
        randomNumber = Math.random();
        if (randomNumber >= 0 && randomNumber <= 0.50) {
            Integer hour = ThreadLocalRandom.current().nextInt(8,21);
            Integer minues = ThreadLocalRandom.current().nextInt(0,59);
            Integer seconds = ThreadLocalRandom.current().nextInt(0,59);
            sb.append(hour).append(":").append(minues).append(":").append(seconds);
        } else{
            double topTime = Math.random();
            if (topTime >= 0 && topTime <= 0.50) {
                Integer hour = ThreadLocalRandom.current().nextInt(8,9);
                Integer minues = ThreadLocalRandom.current().nextInt(0,59);
                Integer seconds = ThreadLocalRandom.current().nextInt(0,59);
                sb.append(hour).append(":").append(minues).append(":").append(seconds);
            }else{
                Integer hour = ThreadLocalRandom.current().nextInt(18,20);
                Integer minues = ThreadLocalRandom.current().nextInt(0,59);
                Integer seconds = ThreadLocalRandom.current().nextInt(0,59);
                sb.append(hour).append(":").append(minues).append(":").append(seconds);
            }
        }
        return sb.toString();
    }
}
