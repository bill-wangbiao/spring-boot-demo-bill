package com.bill.test.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ScrolledPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.utils
 * @Description: ES工具类
 * @date Date : 2019年11月02日 20:47
 */
@Slf4j
public class ESUtils {
    public static final long DEFAULT_SCROLL_TIMEOUT = 30 * 60 * 1000;
    public static final int DEFAULT_SCROLL_SIZE = 5000;

    /**
     *  从ES中分页查询数据
     * @param elasticsearchTemplate
     * @param clazz
     * @param searchQuery
     * @param <S>
     * @return
     */
    public static <S> List<S> elasticsearchScroll(ElasticsearchTemplate elasticsearchTemplate, Class<S> clazz, NativeSearchQueryBuilder searchQuery){
        /**查询DEFAULT_SCROLL_SIZE条数据**/
        searchQuery.withPageable(PageRequest.of(0, DEFAULT_SCROLL_SIZE));
        return elasticsearchScroll(elasticsearchTemplate,clazz,DEFAULT_SCROLL_TIMEOUT,searchQuery.build());
    }

    /**
     * 从ES中分页查询数据
     * @param elasticsearchTemplate
     * @param clazz
     * @param timeout
     * @param searchQuery
     * @param <S>
     * @return
     */
    public static <S> List<S> elasticsearchScroll(ElasticsearchTemplate elasticsearchTemplate, Class<S> clazz, long timeout, SearchQuery searchQuery){
        List<S> resultList = new ArrayList<>();
        ScrolledPage<S> scroll = (ScrolledPage<S>) elasticsearchTemplate.startScroll(timeout, searchQuery, clazz);
        scroll.getTotalElements();
        while (scroll.hasContent()) {
            for (S dto : scroll.getContent()) {
                resultList.add(dto);
            }
            log.info("elasticsearchScroll查询出来数据的数量:{}",resultList.size());
            scroll = (ScrolledPage<S>) elasticsearchTemplate.continueScroll(scroll.getScrollId(), timeout, clazz);
        }
        elasticsearchTemplate.clearScroll(scroll.getScrollId());
        return resultList;
    }

}
