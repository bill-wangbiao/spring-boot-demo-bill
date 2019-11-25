package com.bill.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.bill.test.es.EsOrderDetail;
import com.bill.test.es.service.EsOrderDetailService;
import com.bill.test.utils.ESUtils;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.controller
 * @Description: ES控制器
 * @date Date : 2019年11月02日 21:05
 */
@RestController
@RequestMapping(value = "/es")
@Slf4j
public class EsController {
    @Resource
    ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    EsOrderDetailService esOrderDetailService;

    @RequestMapping(value = "/list")
    public String list() {
        String value="";
        BoolQueryBuilder builder = QueryBuilders.boolQuery()
                .filter(QueryBuilders.rangeQuery("create_time").gte("2019-01-31").lt("2019-02-01").timeZone("+08:00"))
                .must(QueryBuilders.termsQuery("organSign", "SYNC59025"));

        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        queryBuilder.withQuery(builder);
        List<EsOrderDetail> list = ESUtils.elasticsearchScroll(elasticsearchTemplate, EsOrderDetail.class, queryBuilder);
        if(CollectionUtils.isEmpty(list)){
            return "无查询结果";
        }
        value= JSONObject.toJSONString(list);
        return value;
    }

    @RequestMapping(value = "/findByProductCode")
    public String findByProductCode(String productCode) {
        List<EsOrderDetail> list = esOrderDetailService.findByProductCode(productCode);
        if(CollectionUtils.isEmpty(list)){
            log.info("未查询到数据");
            return "未查询到数据";
        }
        String value=JSONObject.toJSONString(list);
        log.info("查询到的数据是："+value);
        return value;
    }
}
