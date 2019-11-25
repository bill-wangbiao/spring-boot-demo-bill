package com.bill.test.es.service;

import com.bill.test.es.EsOrderDetail;
import com.bill.test.es.repository.EsOrderDetailRepository;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.es.service
 * @Description: EsOrderDetailService
 * @date Date : 2019年11月02日 22:02
 */
@Service
@Slf4j
public class EsOrderDetailService {
    @Autowired
    EsOrderDetailRepository esOrderDetailRepository;

    public List<EsOrderDetail> findByProductCode(String productCode){
        return Lists.newArrayList(esOrderDetailRepository.findByProductCode(productCode));
    }
}
