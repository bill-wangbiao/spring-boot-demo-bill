package com.bill.test.io;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.io
 * @Description: TODO
 * @date Date : 2019年11月11日 2:07
 */
@Slf4j
public class ReadFile {
    public final static String path = "C:\\Users\\AW\\Desktop\\phonenumber.txt";
    @Test
    public void readTxt(){
        List<String> list= Lists.newArrayList();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path)), "UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
                list.add(lineTxt);
            }
            br.close();
        } catch (Exception e) {
            log.error("read errors :" + e);
        }
        log.info("读取到的手机号数量："+list.size());
    }
}
