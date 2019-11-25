package com.bill.test.datastructure;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.datastructure
 * @Description: TODO
 * @date Date : 2019年11月10日 15:54
 */
@Slf4j
public class StringTest {

    @Test
    public void contains(){
        String aa="中华人民共和国";
        String bb="共和国";
        log.info("输出包含关系："+aa.contains(bb)+",反过来结果："+bb.contains(aa));
        log.info("输出截取之后的数据："+aa.substring(0,3));
    }

    @Test
    public void sub(){
        String l="1142754";
        String memberId="";
        int length = l.length();
        if(length>10){
            memberId=l.replace(l.substring(0,8), ThreadLocalRandom.current().nextInt(10000000,99999999)+"");
        }else {
            memberId=ThreadLocalRandom.current().nextInt(100000000,999999999)+"";
        }
        log.info("输出memberId："+memberId);
    }

    @Test
    public void nextInt(){
        Date date=new Date();
        log.info("输出："+date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, ThreadLocalRandom.current().nextInt(1, 9));
        Date create_time = c.getTime();
        log.info("输出之后的日期："+create_time);
    }

    @Test
    public void subStr(){
        String aa="*-2018-02".substring(2);
        log.info("输出："+aa);
        String[] split = aa.split("-");
        Integer year=Integer.valueOf(split[0]);
        Integer month = Integer.valueOf(split[1]);
        String dateStr="";
        if(month-1<=0){
            dateStr=(year-1)+"-"+12;
        }else {
            dateStr=year+"-"+((month-1)<10?"0"+(month-1):(month-1));
        }
        log.info(dateStr);

    }

}
