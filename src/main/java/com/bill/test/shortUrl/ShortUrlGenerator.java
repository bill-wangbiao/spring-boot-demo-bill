package com.bill.test.shortUrl;

import java.util.Random;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.shortUrl
 * @Description: TODO 短链生成工具类
 * @date Date : 2019年08月08日 17:07
 */
public class ShortUrlGenerator {
    private static final String domin="http://www.baidu.com/";

    /**
     *短链生成方法
     * @param url
     * @return
     */
    public static String shortUrl(String url) {
        /**可以自定义生成 MD5 加密字符传前的混合 KEY**/
        String key = "wangbiao";
        /**要使用生成 URL 的字符**/
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};
        /**对传入网址进行 MD5 加密**/
        String sMD5EncryptResult = (Encript.md5(key + url));
        String hex = sMD5EncryptResult;
        String[] resUrl = new String[4];
        /**得到 4组短链接字符串**/
        for (int i = 0; i < 4; i++) {
            /**把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算**/
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);
            /**这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界**/
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            /**循环获得每组6位的字符串**/
            for (int j = 0; j < 6; j++) {
                /**把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引(具体需要看chars数组的长度   以防下标溢出，注意起点为0)**/
                long index = 0x0000003D & lHexLong;
                /**把取得的字符相加**/
                outChars += chars[(int) index];
                /**每次循环按位右移 5 位**/
                lHexLong = lHexLong >> 5;
            }
            /**把字符串存入对应索引的输出数组**/
            System.out.println("短链字符串："+outChars);
            resUrl[i] = outChars;
        }
        int length = resUrl.length;
        if(length>0 && length<5){
            int i = new Random().nextInt(4);
            return domin+resUrl[i];
        }
        return "";
    }
}
