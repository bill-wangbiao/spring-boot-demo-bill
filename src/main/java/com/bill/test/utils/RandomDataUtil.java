package com.bill.test.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * 生成随机集合工具类
 * @author liuyuchen
 */
public class RandomDataUtil {

    private static final Logger logger = LoggerFactory.getLogger(RandomDataUtil.class);

    public final static int TWO=2;


    /**
     * 生成Set
     * @param totalCount
     * @return
     */
    private Set<Integer> getSetALL(Integer totalCount){
        Set<Integer> set = new HashSet<Integer>();
        for(int i=0;i<totalCount;i++){
            set.add(i);
        }
        return set;
    }

    /**
     * 获得区间随机数
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(int min, int max){
        try {
            Random random = new Random();
            int s = random.nextInt(max) % (max - min) + min;
            return s;
        }catch (Exception e){
            logger.error("获取随机数出现异常，异常信息为："+e.getMessage());
            return 1;
        }
    }

    /**
     * 获得日期月份差数
     * @param date
     * @return
     * @throws ParseException
     */
    public static int getMonthSpace(Date date){
        int result = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date);
        c2.setTime(new Date());
        int subYeah = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        if(subYeah >= 1){
            result =  (subYeah * 12) + result;
        }
        return result == 0 ? 1 : Math.abs(result);
    }


    public static int getDaySpace(Date date1, Date date2){
        long day1 = date1.getTime();
        long day2 = date2.getTime();
        return  (int) ((day1 - day2)/(1000 * 60 * 60 * 24));
    }

    public static int  getRandomNums(int n){
        int m = (int) (Math.random()*9*Math.pow(10, n-1) + (long)Math.pow(10,n-1));
        return m;
    }

    /**
     * 获取随机时间
     */
    @SuppressWarnings("deprecation")
    public static Date getRandomTime(Date startTime, Date finalTime) {
        Calendar calStartTime = Calendar.getInstance();
        calStartTime.setTime(startTime);
        Calendar calFinalTime = Calendar.getInstance();
        calFinalTime.setTime(finalTime);
        int interval = (int) ((calFinalTime.getTimeInMillis() - calStartTime.getTimeInMillis()) / 1000L);
        int randDiff = (int) (Math.random() * interval);
        calStartTime.add(Calendar.SECOND, randDiff);
        return calStartTime.getTime();
    }

    /**
     * 小数点区间
     * @param min
     * @param max
     * @return
     * @throws Exception
     */
    public static double rangeDouble(final double min, final double max) throws Exception {
        if (max < min) {
            throw new Exception("min < max");
        }
        if (min == max) {
            return min;
        }
        double next = min + ((max - min) * new Random().nextDouble());
        BigDecimal bg = new BigDecimal(next).setScale(2, BigDecimal.ROUND_DOWN);
        return  bg.doubleValue();
    }

}