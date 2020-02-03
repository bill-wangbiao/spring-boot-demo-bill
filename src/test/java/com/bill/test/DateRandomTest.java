package com.bill.test;

import com.bill.test.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test
 * @Description: TODO
 * @date Date : 2020年01月13日 3:10
 */
@Slf4j
public class DateRandomTest {
    private static Set randomDate(int days, String beginDate, String endDate) {
        Set resultList = new HashSet<Date>();
        try {
            while (true) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date start = format.parse(beginDate);
                Date end = format.parse(endDate);
                if (start.getTime() >= end.getTime()) {
                    return null;
                }
                long date = random(start.getTime(), end.getTime());
                resultList.add(format.format(date));
                if (resultList.size() == days) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);
        }
        return rtn;
    }

    @Test
    public void getDate(){
        int days=10;
        String beginDate="2019-10-01";
        String endDate="2019-10-31";
        Date beginTime = DateUtils.parseStrToDate(beginDate, DateUtils.DATE_FORMAT_YYYY_MM_DD);
        int size=1000;
        // 随机切割总订单数到整个库存周转天数中
        ArrayList<Integer> perCounts = splitAllCountsByRandom(size, days, 1, (int) Math.ceil((size / days) * 2));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginTime);

        // 按天分配
        for (int j = 0; j < days; j++) {
            // 每天分多少单
            for (int k = perCounts.get(j); k >= 1; k--) {
                calendar.set(Calendar.DAY_OF_MONTH, j + 1);
               log.info("输出日期："+calendar.getTime());
            }
        }
    }

    public static ArrayList<Integer> splitAllCountsByRandom(int total, int splitCount, int min, int max) {
//        System.out.println("总单数：	" + total);
//        System.out.println("天数：	" + splitCount);
//        System.out.println("最小单数：	" + min);
//        System.out.println("最大单数：	" + max);

        ArrayList<Integer> al = new ArrayList<Integer>();
        Random random = new Random();

        if ((splitCount & 1) == 1) {//
//            System.out.println("订单天数" + splitCount + "是奇数，单独生成一天的数量");
            int num = 0;
            do {
                num = random.nextInt(max);
//                System.out.println("单个的随机订单数为：" + num);
            } while (num >= max || num <= min);
            total = total - num;
            al.add(num);
        }
        int couples = splitCount >> 1;
        int perCoupleSum = total / couples;

        if ((splitCount & 1) == 1) {
//            System.out.println("处理后剩余的订单数为：" + total);
        }
//        System.out.println("将" + total + "单拆分为" + couples + "对订单，每对总数：" + perCoupleSum);
        for (int i = 0; i < couples; i++) {
            Boolean finish = true;
            int num1 = 0;
            int num2 = 0;
            do {
                num1 = random.nextInt(max);
                num2 = perCoupleSum - num1;
                if (!al.contains(num1) && !al.contains(num2)) {
                    if (i == 0) {
                        num1 = (total - couples * perCoupleSum) + num1;
                    }
                }
            } while (num1 < min || num1 > max || num2 < min || num2 > max);
            al.add(num1);
            al.add(num2);
        }

        int check_num = 0;
        Integer.compare(1, 2);
        al.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });
        for (int x : al) {
            check_num = check_num + x;
        }
        return al;
    }
}
