package com.bill.test.es.repository;

import com.bill.test.es.EsOrderDetail;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.es.repository
 * @Description: EsOrderDetailRepository
 * @date Date : 2019年11月02日 21:56
 */
@Component
public interface EsOrderDetailRepository extends ElasticsearchRepository<EsOrderDetail, String> {
    //TODO 查询不到数据

    EsOrderDetail findByProductCode(String productCode);

    EsOrderDetail getDistinctByProductCode(String productCode);

    List<EsOrderDetail> getAllByProductCode(String productCode);

    EsOrderDetail getByProductCodeIs(String productCode);
}
