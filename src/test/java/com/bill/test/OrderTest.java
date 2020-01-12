package com.bill.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test
 * @Description: TODO
 * @date Date : 2019年12月31日 20:44
 */
public class OrderTest {
    public static void main(String[] args) {
        List<Product> list = new ArrayList<>();
        //list安照商品数量由低到高排序
        list.add(new Product(10, "A"));
        list.add(new Product(11, "B"));
        list.add(new Product(56, "C"));
        list.add(new Product(3, "D"));
        list.add(new Product(99, "E"));
        list.add(new Product(7, "F"));
        list.add(new Product(20, "G"));
        list.add(new Product(67, "H"));
        list.add(new Product(88, "I"));
        list.add(new Product(8, "J"));
        list.sort(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o1.getNum()-o2.getNum();
            }
        });
        //订单数量
        int orderNum=100;
        int pSum=0;
        for(Product p :list){
            pSum+=p.getNum();
        }
        System.out.println(pSum);
        List<Order> orderList=new ArrayList<>();
        for(int i=0;i<orderNum;i++){
            Order order=new Order();
            List<Product> productList=new ArrayList<Product>();
            order.setList(productList);
            orderList.add(order);
        }
        int start=0;
        for(int i=0;i<list.size();i++){
            Product product = list.get(i);
            int whileCount=0;
            while(product.getNum()>0){
                //循环两遍不能放则放弃该商品，防止死循环
                if(whileCount>2){
                    System.out.println("放弃+"+product.getNum());
                    break;
                }
                for(;start<orderNum;start++){
                    if(product.getNum()>0) {
                        Order order = orderList.get(start);
                        order.add(product);
                    }else{
                        break;
                    }
                }
                if(start==orderNum){
                    start=0;
                }
                whileCount++;
            }
        }

        int sum=0;
        for(Order o:orderList){
            for(Product p:o.getList()){
                System.out.println(o.getList().size()+" "+o.orderProductNum()+" "+p.getName());
            }

            sum+=o.orderProductNum();
        }
        System.out.println(sum);
    }

}

class Order {
    List<Product> list;
    boolean ifAdd=true;
    public int orderProductNum(){
        int i=0;
        for(Product p:list){
            i+=p.getNum();
        }
        return i;
    }
    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }
    public boolean add(Product newP){
        double allPrice=0;
        if(ifAdd==false){
            return false;
        }
        for(Product p:list){
            //统计所有订单总金额
            allPrice+=p.num*1;
            //如果订单总金额>客单价两倍 则不放了
            if(allPrice>=10000){
                ifAdd=false;
                return false;
            }
        }
        int countProduct=0;
        Product oldProduct=null;
        //找到不重复的商品
        for(Product p:list){
            if(!p.getName().equals(newP.getName())){
                countProduct+=1;
            }else {
                oldProduct=p;
            }
        }
        //设置大于两个商品停止 则最多三个商品不重复商品
        if(countProduct>2){
            ifAdd=false;
            return false;
        }
        //如果是新增商品,10块钱以下的随机扣除1-5中的随机
        if(oldProduct==null){
            Product p=new Product(2,newP.getName());
            newP.setNum(newP.getNum()-2);
            list.add(p);
        }else {
            oldProduct.setNum(oldProduct.getNum()+2);
            newP.setNum(newP.getNum()-2);
        }
        return true;
    }
}

class Product {
    public Product(int num, String name) {
        this.num = num;
        this.name = name;
    }

    int num;
    String name;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
