package com.bill.test.es;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.es
 * @Description: EsOrderDetail操作对象
 * @date Date : 2019年11月02日 21:09
 */
@Data
@ToString
@Document(indexName = "saas_order_detail", type = "orderDetail")
public class EsOrderDetail implements Serializable {
    /**
     * 主键
     */
    @JsonProperty("id")
    private Long id;

    /**
     * 0 未上报 1 已上报
     */
    @JsonProperty("upload")
    private Byte upload;

    /**
     * 优惠金额
     */
    @JsonProperty("discount_amount")
    private Float discountAmount;

    /**
     * 实收金额
     */
    @JsonProperty("actual_amount")
    private Float actualAmount;

    /**
     * 折扣(百分比)
     */
    @JsonProperty("discount")
    private Float discount;

    /**
     * 同步数据原表的ID
     */
    @JsonProperty("origin_id")
    private String originId;

    /**
     * 应收金额
     */
    @JsonProperty("receivable_amount")
    private Float receivableAmount;

    /**
     * 商品编号
     */
    @JsonProperty("product_code")
    private String productCode;

    /**
     * 商品成本总价
     */
    @JsonProperty("product_cost_amount")
    private Float productCostAmount;

    /**
     * 是否是处方: 1-是 0-不是
     */
    @JsonProperty("prescription_yn")
    private Byte prescriptionYn;

    /**
     * 修改时间
     */
    @JsonProperty("update_time")
    private Date updateTime;

    /**
     * 修改人
     */
    @JsonProperty("update_user")
    private String updateUser;

    /**
     * 逻辑删除 1 有效 0 删除
     */
    @JsonProperty("yn")
    private Byte yn;

    /**
     * 明细积分
     */
    @JsonProperty("integral")
    private Float integral;

    /**
     * 毛利金额
     */
    @JsonProperty("gross_profit_amount")
    private Float grossProfitAmount;

    /**
     * 商品id
     */
    @JsonProperty("product_id")
    private Long productId;

    /**
     * 是否线上 0:否 1:是
     */
    @JsonProperty("is_online")
    private Byte isOnline;

    /**
     * 供应商名称
     */
    @JsonProperty("provider_name")
    private String providerName;

    /**
     * 机构编号
     */
    @JsonProperty("organSign")
    private String organSign;

    /**
     * 促销类型: 0-正常1--满折，2--满送，3--会员日，4--限量特价
     */
    @JsonProperty("promotion_type")
    private Integer promotionType;

    /**
     * 批号
     */
    @JsonProperty("batch_no")
    private String batchNo;

    /**
     * 创建时间
     */
    @JsonProperty("create_time")
    private Date createTime;

    /**
     * 商品产地
     */
    @JsonProperty("product_origin_address")
    private String productOriginAddress;

    /**
     * 是否含麻: 1-是 0-否
     */
    @JsonProperty("bast_yn")
    private Byte bastYn;

    /**
     * 含麻id
     */
    @JsonProperty("bast_id")
    private Long bastId;

    /**
     * 商品数量
     */
    @JsonProperty("product_num")
    private Integer productNum;

    /**
     * 处方id
     */
    @JsonProperty("prescription_id")
    private Long prescriptionId;

    /**
     * 生产厂家
     */
    @JsonProperty("product_manufacturer")
    private String productManufacturer;

    /**
     * 商品名称
     */
    @JsonProperty("product_name")
    private String productName;

    /**
     * 零售价
     */
    @JsonProperty("product_retail_price")
    private Float productRetailPrice;

    /**
     * 商品单位
     */
    @JsonProperty("product_unit")
    private Integer productUnit;

    /**
     * 实收单价金额
     */
    @JsonProperty("actual_unit_amount")
    private Float actualUnitAmount;

    /**
     * 会员价
     */
    @JsonProperty("product_member_price")
    private Float productMemberPrice;

    /**
     * 商品成本单价
     */
    @JsonProperty("product_unit_cost_amount")
    private Float productUnitCostAmount;

    /**
     * 商品规格
     */
    @JsonProperty("product_specifications")
    private String productSpecifications;

    /**
     * 可退数量
     */
    @JsonProperty("refundable_number")
    private Float refundableNumber;

    /**
     * 创建人
     */
    @JsonProperty("create_user")
    private String createUser;

    /**
     * 订单表id
     */
    @JsonProperty("order_id")
    private String orderId;
}
