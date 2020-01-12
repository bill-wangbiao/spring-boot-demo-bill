package com.bill.test;

import com.bill.test.utils.DateUtils;
import com.bill.test.utils.RandomDataUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test
 * @Description: TODO
 * @date Date : 2020年01月11日 21:23
 */
@Slf4j
public class OrderDateTest {

    @Test
    public void testDate(){
        String dateStr="2019-10";
        Date beginTime = DateUtils.parseStrToDate(dateStr + "-01", DateUtils.DATE_FORMAT_YYYY_MM_DD);
        List<String> list= Lists.newArrayList();
        for(int i=0;i<1500;i++){
            list.add(i+"");
        }
        int days = 30;
        int minCount = 0;
        int maxCount = 0;
        /**订单总数**/
        int size = list.size();
        if (size < 1000) {
            minCount = 25;
            maxCount = 40;
        } else if (size > 1000 && size < 1500) {
            minCount = 30;
            maxCount = 55;
        } else if (size > 1500 && size < 2000) {
            minCount = 35;
            maxCount = 65;
        } else if (size > 2000 && size < 3000) {
            minCount = 60;
            maxCount = 100;
        } else if (size > 3000 && size < 4000) {
            minCount = 65;
            maxCount = 110;
        } else if (size > 4000 && size < 5000) {
            minCount = 100;
            maxCount = 165;
        } else if (size > 5000 && size < 6000) {
            minCount = 150;
            maxCount = 200;
        } else if (size > 6000 && size < 7000) {
            minCount = 200;
            maxCount = 270;
        } else {
            minCount = 250;
            maxCount = 350;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginTime);
        for (int j = 0; j < days; j++) {
            /**根据订单总数量，随机给当月的每一天分配订单**/
            Integer num = RandomDataUtil.getRandom(minCount, maxCount);
            log.info("输出num："+num);
            if (num > list.size()) {
                num = list.size() - 1;
            }
            for (int k = num - 1; k > 1; k--) {
                if (list.isEmpty() && list.size() > num) {
                    break;
                }
                calendar.set(Calendar.DAY_OF_MONTH, j + 1);
                log.info("时间："+calendar.getTime());
            }
        }
    }

}
