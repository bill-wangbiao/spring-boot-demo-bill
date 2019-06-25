package com.bill.test.datastructure;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.datastructure.array
 * @Description: 数组测试类
 * @date Date : 2019年04月24日 14:54
 */
public class ArrayTest {
    @Test
    public void statement(){
        String[] strArray=new String[3];
        int length = strArray.length;
        System.out.println("数组长度："+length);
        System.out.println("未初始化数据，数组的元素："+strArray[0]);

        for (int i=0;i<length;i++){
            strArray[i]=i*2+1+"";
        }

        int j=0;
        while (true){
            System.out.println("输出元素："+strArray[j]);
            j++;
            if(j>=length){
                break;
            }
        }
    }

    @Test
    public void staticStatement(){
        Integer[] intAttr=new Integer[]{10,12,50,236,17};
        int length = intAttr.length;
        System.out.println("数组长度："+length);

        for(int i=0;i<length;i++){
            System.out.println("数组索引："+i+"，对应的元素："+intAttr[i]);
        }
    }

    /**二维数组**/
    @Test
    public void twoDimensionArray(){
        //此时的数组并不是一个等列数组
        int data[][] = new int[][] {
                {1, 2, 3}, {4, 5}, {6, 7, 8, 9}};
        //如果在进行输出的时候一定要使用双重循环，
        //外部的循环控制输出的行数，而内部的循环控制输出列数
        for(int i = 0; i < data.length; i++) {
            for(int j = 0; j < data[i].length; j++) {
                System.out.print("data[" + i + "][" + j + "]=" + data[i][j] + "、");
            }
            System.out.println();
        }
    }

    @Test
    public void arraycopy(){
        int[] arr=new int[]{1,2,3,4,5};
        System.out.println("输出数组："+ Arrays.toString(arr));

        int[] array=new int[2];
        System.arraycopy(arr,1,array,0,2);
        System.out.println("输出copy的数组："+Arrays.toString(array));

        int[] copyOf = Arrays.copyOf(arr, arr.length + 3);
        System.out.println("输出Arrays.copyOf:"+Arrays.toString(copyOf));
    }
}
