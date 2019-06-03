package com.bill.test.thread;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.thread
 * @Description: TODO
 * @date Date : 2019年05月17日 18:34
 */
public class CompletableFutureTest {
    @Test
    public void test(){
        CompletableFuture<String> future =
                CompletableFuture.supplyAsync(() -> "hello").thenApply(s -> s + "world").thenApply(String::toUpperCase);
        try {
            System.err.println("----》"+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        CompletableFuture.supplyAsync(
                ()->{return 1;}
        ).thenApply(
                i->i+1
        ).thenApply(
                i->i*i
        ).whenComplete(
                (r,e)->System.err.println(r)
        );
    }
}
