package com.bill.test.shortUrl;

import java.util.Random;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.shortUrl
 * @Description: TODO 短链单元测试类
 * @date Date : 2019年08月08日 17:56
 */
public class ShortUtlTest {
    public static void main(String[] args){
        String longUrl="https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=%E7%BE%8E%E5%A5%B3&" +
                "step_word=&hs=0&pn=4&spn=0&di=12898600&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&" +
                "istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=3473128871%2C1574804327&os=" +
                "682204536%2C490884976&simid=0%2C0&adpicid=0&lpn=0&ln=1548&fr=&fmq=1565258455687_R&" +
                "fm=result&ic=&s=undefined&hd=&latest=&copyright=&se=&sme=&tab=0&width=&height=&face=undefi" +
                "ned&ist=&jit=&cg=girl&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic27.nipic.com%2F20130314%2" +
                "F11899688_192542628000_2.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bgtrtv_z%26e3Bv54Az" +
                "dH3Fejvp56AzdH3Fzi7wgptAzdH3F8ndcam0_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&force=undefined";

        longUrl="https://www.baidu.com/pic";

        String shortUrl = ShortUrlGenerator.shortUrl(longUrl);
        System.out.println("短链地址："+shortUrl);

    }
}
