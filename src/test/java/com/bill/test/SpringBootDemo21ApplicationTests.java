package com.bill.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alibaba.fastjson.JSONObject;
import com.bill.test.es.EsOrderDetail;
import com.bill.test.es.repository.EsOrderDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bill.test.controller.IndexController;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringBootDemo21ApplicationTests {
	@Autowired
	EsOrderDetailRepository esOrderDetailRepository;

	private MockMvc mvc;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.standaloneSetup(new IndexController()).build();
	}

	@Test
	public void contextLoads() throws Exception {
		RequestBuilder request = get("/index");
		System.out.println("index****************************"+mvc.perform(request).andReturn().getResponse().getContentAsString());
		mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("hello world"));
		
		request = get("/index/get").param("name", "无境");
		String result=mvc.perform(request).andReturn().getResponse().getContentAsString();
		System.out.println("get****************************"+result);
		mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("{\"name\":\"无境\",\"title\":\"hello world\"}"));
	}

	@Test
	public void findByProductCode(){
		String productCode="150411";
		//TODO 查询不到数据我擦
		EsOrderDetail dto = esOrderDetailRepository.getByProductCodeIs(productCode);
		if(dto==null){
			log.info("未查询到数据");
		}else {
			log.info("输出dto:"+ JSONObject.toJSONString(dto));
		}

		/**
		 * 创建查询体
		 */
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("product_code", productCode).boost(2.0f);
		builder.must(termQueryBuilder);
		Iterable<EsOrderDetail> iterable = esOrderDetailRepository.search(builder);
		for(EsOrderDetail e:iterable){
			log.info("输出："+JSONObject.toJSONString(e));
		}
	}



}
