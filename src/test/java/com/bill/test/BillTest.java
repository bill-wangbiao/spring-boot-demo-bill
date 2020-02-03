package com.bill.test;

import com.alibaba.fastjson.JSONObject;
import com.bill.test.utils.DateUtils;
import com.bill.test.utils.RandomValue;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.assertj.core.util.DateUtil;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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

    @Test
    public void randomPhone(){
        String phone="133"+RandomValue.getNum(10000000, 99999999);
        log.info("phone number ："+phone);
    }

    @Test
    public void testDate(){
        String start="2019-11-01";
        String end="2020-10-02";
        List<String> monthListOfDateV2 = DateUtils.getMonthListOfDateV2(start, end);
        log.info("输出内容："+JSONObject.toJSONString(monthListOfDateV2));
        boolean b=true;
//        while (false){
//
//            monthListOfDateV2.parallelStream().forEach(n->{
//                if(n.equals("2019-11")){
//                    return;
//                }
//                log.info("执行return之后的逻辑。。。。"+n);
//            });
//            log.info("for循环外的执行逻辑。。。。");
//        }
//        log.info("while循环外的执行逻辑。。。。");
    }

    @Test
    public void convert() throws UnsupportedEncodingException {
        String message=new String("我是王彪".getBytes());
        log.info("输出message:"+message);
        String str = new String(message.getBytes(),  Charset.forName("GBK"));
        log.info("输出str:"+str);
    }

    @Test
    public void getYear() {
        log.info("输出当前年份:"+new Date().getYear());

        log.info("输出年份："+Calendar.getInstance().get(Calendar.YEAR));
    }

    @Test
    public void divide() {
        log.info("输出："+1502/3);
        log.info("输出："+1502%3);
    }

    @Test
    public void random(){
        for(int i=0;i<100;i++){
            log.info("random:"+ThreadLocalRandom.current().nextInt(1, 4));
        }
    }

    @Test
    public void sort(){
        List<Memeber> list= Lists.newArrayList();
        Memeber m1=new Memeber();
        m1.setId(10L);
        list.add(m1);
        Memeber m2=new Memeber();
        m2.setId(15L);
        list.add(m2);
        List<Memeber> memeberList = list.stream().sorted(Comparator.comparing(Memeber::getId)).collect(Collectors.toList());
        log.info("输出："+memeberList.get(0).getId());
        log.info("输出："+memeberList.get(1).getId());

        for(int i=memeberList.size()-1;i>=0;i--){
            log.info("输出aa："+memeberList.get(i).getId());
        }
    }

    @Test
    public void randomTime(){
        for(int i=0;i<100;i++){
            log.info("输出日志："+DateUtils.randomOrderTime());
        }
    }

    @Test
    public void sub1(){
        String idCard="412822199003211171";
        Integer substring = Integer.parseInt(idCard.substring(16, 17));
        log.info("输出："+substring);
        if(substring%2==0){
            log.info("女");
        }else{
            log.info("男");
        }
    }

    @Test
    public void getRandomTime(){
        Date date=new Date();
        log.info("输出原时间："+date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        //会员时分秒
        c.add(Calendar.DATE, ThreadLocalRandom.current().nextInt(1, 10));
        // 时
        c.set(Calendar.HOUR_OF_DAY, ThreadLocalRandom.current().nextInt(0, 24));
        // 分
        c.set(Calendar.MINUTE, ThreadLocalRandom.current().nextInt(0, 60));
        // 秒
        c.set(Calendar.SECOND, ThreadLocalRandom.current().nextInt(0, 60));
        // 毫秒
        c.set(Calendar.MILLISECOND, ThreadLocalRandom.current().nextInt(0, 1000));
        Date time = c.getTime();
        log.info("输出随机时间："+time);

        log.info("输出时间间隔之间的某个时间："+new Date(ThreadLocalRandom.current().nextLong(date.getTime(), time.getTime())));
    }

    @Test
    public void testDateList(){
        String start = "2017-04-01";
        String end = "2020-03-01";
        List<String> listMonth = getMonthListOfDateV2(start, end);
        listMonth.forEach(t -> {
            log.info("输出日期："+t);
        });
    }



    public  List<String> getMonthListOfDateV2(String minDate, String maxDate) {
        ArrayList<String> result = new ArrayList<String>();
        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();
        min.setTime(parseStrToDate(minDate, "yyyy-MM"));
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
        max.setTime(parseStrToDate(maxDate, "yyyy-MM"));
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(parseDateToStr((curr.getTime()), "yyyy-MM"));
            curr.add(Calendar.MONTH, 1);
        }
        return result;
    }

    public  Date parseStrToDate(String time, String timeFromat) {
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

    public static String parseDateToStr(Date time, String timeFromat){
        DateFormat dateFormat=new SimpleDateFormat(timeFromat);
        return dateFormat.format(time);
    }

    @Test
    public void equals(){
        byte aa=11;
        log.info("输出是否相等："+(aa==11));
    }

    @Test
    public void randomAge(){
        int age=0;
        int ageRandom = ThreadLocalRandom.current().nextInt(0, 100);
        log.info("输出ageRandom:"+ageRandom);
        if (ageRandom <= 5) {
            age=ThreadLocalRandom.current().nextInt(18, 30);
        } else if (ageRandom <= 20) {
            age=ThreadLocalRandom.current().nextInt(30, 45);
        } else {
            age=ThreadLocalRandom.current().nextInt(45, 91);
        }
        log.info("输出age:"+age);
    }

    @Test
    public void testRandompay(){
        int a=0;
        int b=0;
        int c=0;
        int d=0;
        for(int i=0;i<1000;i++){
            Integer r1 = RandomUtils.nextInt(0,100);
            Integer r2=RandomUtils.nextInt(0,100-r1);
            Integer r3=RandomUtils.nextInt(0,100-r1-r2);
            Integer random = RandomUtils.nextInt(0,100);
            if(random<r1){
                a++;
            }else if (random<r1+r2){
                b++;
            }else if (random<r1+r2+r3){
                c++;
            }else {
                d++;
            }
        }
        log.info("a:"+a+",b:"+b+",c:"+c+",d:"+d);

    }

    @Test
    public void getHour(){
        Date date=new Date();
        log.info("输出hour："+date.getHours());
    }

    @Test
    public void testRandomTotal(){
        // 设置随机数个数，最小数为0（分割随机数本身）最大不能大于随机数
        // 这个长度为设定的长度减一，如获取5个随机数，将len设置为4
        int len = 2;
        // 要拆分的数
        int sources = 486;
        Random random = new Random();
        // 先获取到随机数分割的个数，提升效率
        List<Double> r = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            double v = random.nextDouble();
            r.add(v);
        }
        // 从小到大排序，便于后续获取随机数
        r.sort(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o1 < o2 ? -1 : 1;
            }
        });
        // 用上边的随机数，来取得len份拆分之后的数
        List<Integer> out = new ArrayList<>();
        // 记录前一个随机数
        int last = 0;
        for (int i = 0; i < len; i++) {
            int c = (int) (r.get(i) * sources);
            int i1 = c - last;
            out.add(i1);
            last = c;
        }
        // 最后一个随机数
        out.add(sources - last);
        for(int i=0;i<out.size();i++){
            log.info("输出："+out.get(i));
        }
    }


    @Test
    public void testRandomMemberTime(){
        int totalRandom=10000;
        int j=0;

        int timeLimit=ThreadLocalRandom.current().nextInt(0,50);
        log.info("限制值："+timeLimit);
        for (int i = 0; i < totalRandom; i++) {
            //TODO 0-50%的随机概率 给到会员注册时间，
            //TODO  注册时间是药店实施时间的0-10分钟随机
            int timeRandom=ThreadLocalRandom.current().nextInt(0,100);
            if(timeRandom<timeLimit){
                j++;
            }
        }
        log.info("输出j:"+j);
    }

    @Test
    public void testBigdecimal(){
        String s = new BigDecimal(1000).divide(new BigDecimal(33), 2, BigDecimal.ROUND_HALF_DOWN).toString();
        log.info("输出s:"+s);
    }



}
