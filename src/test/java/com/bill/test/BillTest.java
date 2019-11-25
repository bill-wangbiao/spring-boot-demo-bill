package com.bill.test;

import com.alibaba.fastjson.JSONObject;
import com.bill.test.utils.RandomValue;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test
 * @Description: 单元测试类
 * @date Date : 2019年03月18日 22:33
 */
@Slf4j
public class BillTest {
    @Test
    public void subStr(){
//        String branchCode="XS420000";
//        if(branchCode.contains("XS")){
//            String s = branchCode.substring(2, branchCode.length());
//            log.info("输出s:"+s);
//            String substring = branchCode.substring(2);
//            log.info("输出substring："+substring);
//        }

        int flag=1;
        int bit= 1<<1;
        log.info("输出bit:"+bit);
        flag |=bit;
        log.info("输出："+flag);
    }

    @Test
    public void getRandom(){
        int min=1;
        int max=2;
        try {
            for(int i=0;i<50;i++){
                Random random = new Random();
                int s = random.nextInt(max) % (max - min) + min;
                System.out.println("输出s:"+s);
            }
        }catch (Exception e){
            log.error("获取随机数出现异常，异常信息为："+e.getMessage());
        }
    }

    @Test
    public void format(){
        String date = "2016-08-15T16:00:00.000Z";
        date = date.replace("Z", " UTC");
        System.out.println(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date d = null;
        try {
            d = format.parse(date);
            log.info("输出："+d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void formatEnglish(){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        //Europe/London  Asia/Shanghai
        format1.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        Date now = new Date();
        log.info("北京时间："+now);
        log.info("输出："+format1.format(new Date()));
    }

    @Test
    public void parse(){
        Map<String,Object> map=new HashMap<>();
        map.put("id",1L);
        map.put("age","10");
        map.put("name","赵美丽");
        Memeber memeber = JSONObject.parseObject(JSONObject.toJSONString(map), Memeber.class);
        String s = JSONObject.toJSONString(memeber);
        log.info("输出："+s);

        String json="{\"id\":1,\"name\":\"赵美丽\"}";
        Memeber memeber1 = JSONObject.parseObject(json, Memeber.class);
        log.info("输出实体："+memeber1.getAge().equals("null"));
    }

    @Test
    public void isEmpty(){
        String str="";
        boolean b = str == null || str.length() == 0;
        log.info("输出b:"+b);
    }

    @Test
    public void increase() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String start="2017-04-01";
        Date dt = sdf.parse(start);
        Date startDate=dt;
        while (true){
            String format = sdf.format(startDate);
            log.info("开始时间："+format);
            Calendar right = Calendar.getInstance();
            right.setTime(startDate);
            right.add(Calendar.MONTH, 1);
            Date endDate = right.getTime();
            log.info("结束时间："+sdf.format(endDate));
            startDate=endDate;
            if(endDate.after(sdf.parse("2017-11-20"))){
                break;
            }
        }
    }

    @Test
    public void subString(){
        String a="hah";
        log.info(a.substring(0,3));
    }

    @Test
    public void testRandom(){
        long time=System.currentTimeMillis();
        final int pool=100000;
        final int start=5;
        final int end=8;
        ExecutorService executorService = Executors.newFixedThreadPool(pool);
        try {
            for(int i=0;i<pool;i++){
                executorService.execute(new MyRunnable(i,start,end));
            }
            executorService.shutdown();
            while (true){
                if(executorService.isTerminated()){
                    log.info("-----------------多线程测试完成，耗时："+(System.currentTimeMillis()-time)+"毫秒");
                    break;
                }
            }
        } catch (Exception e) {
            log.error("多线程测试异常，",e);
        }finally {
            log.info("执行到了finally语句");
        }
    }

    class MyRunnable implements Runnable{
        private int id;
        private int start;
        private int end;
        public MyRunnable(int id,int start,int end){
            this.id=id;
            this.start=start;
            this.end=end;
        }
        @Override
        public void run() {
            int value=0;
            int r = RandomValue.getNum(0, 100);
            if (r < 5) {
                value=RandomValue.getNum(18, 30);
            } else if (r < 80) {
                value=RandomValue.getNum(45, 90);
            } else {
                value=RandomValue.getNum(30, 45);
            }

            if(value>90){
                log.error("#############线程安全出问题###########");
            }
//            log.info("输出当前线程："+Thread.currentThread().getName()+",随机获取的结果："+value);
        }
    }

    @Test
    public void sub(){
        String m="41040119780610546x张春15000901604";
        String newPhone_number=new StringBuilder(new StringBuilder(m).reverse().substring(0, 11)).reverse().toString();
        log.info("newPhone_number："+newPhone_number);

    }

    @Test
    public void contains(){
        String m="hello";
        String n="hello";
        log.info("是否包含："+m.contains(n));
    }

    @Test
    public void split(){
        String s="5861|魏初开|WCK|18390074851";
        String[] split = s.split("|");
        for(String s1:split){
            log.info("s1:"+s1);
        }
        String substring = s.substring(s.lastIndexOf("|")+1, s.length());
        log.info("输出substring:"+substring);
        log.info("输出substring的长度:"+substring.length());
    }

    @Test
    public void toRandomSub(){
        String s="18910782907";
        String substring = s.substring(3, 8);
        String replace = s.replace(substring, RandomValue.getNum(10000, 99999) + "");
        log.info("replace:"+replace);
    }

}
