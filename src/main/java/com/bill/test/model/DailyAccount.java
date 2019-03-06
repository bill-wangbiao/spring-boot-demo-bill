package com.bill.test.model;

import java.math.BigDecimal;
import java.util.Date;

public class DailyAccount {
    private Long id;

    private Date prefixDate;

    private Byte prefixWeek;

    private BigDecimal totalGmv;

    private BigDecimal payMoney;

    private BigDecimal totalGrossProfit;

    private Integer onlineSku;

    private Integer movablePinSku;

    private Integer selloutSku;

    private Integer customers;

    private Integer orders;

    private BigDecimal unitPrice;

    private BigDecimal piecePrice;

    private BigDecimal penetrationNumber;

    private BigDecimal grossProfitMargin;

    private Integer upperShelfSku;

    private String reserve;

    private String creator;

    private String updator;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPrefixDate() {
        return prefixDate;
    }

    public void setPrefixDate(Date prefixDate) {
        this.prefixDate = prefixDate;
    }

    public Byte getPrefixWeek() {
        return prefixWeek;
    }

    public void setPrefixWeek(Byte prefixWeek) {
        this.prefixWeek = prefixWeek;
    }

    public BigDecimal getTotalGmv() {
        return totalGmv;
    }

    public void setTotalGmv(BigDecimal totalGmv) {
        this.totalGmv = totalGmv;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public BigDecimal getTotalGrossProfit() {
        return totalGrossProfit;
    }

    public void setTotalGrossProfit(BigDecimal totalGrossProfit) {
        this.totalGrossProfit = totalGrossProfit;
    }

    public Integer getOnlineSku() {
        return onlineSku;
    }

    public void setOnlineSku(Integer onlineSku) {
        this.onlineSku = onlineSku;
    }

    public Integer getMovablePinSku() {
        return movablePinSku;
    }

    public void setMovablePinSku(Integer movablePinSku) {
        this.movablePinSku = movablePinSku;
    }

    public Integer getSelloutSku() {
        return selloutSku;
    }

    public void setSelloutSku(Integer selloutSku) {
        this.selloutSku = selloutSku;
    }

    public Integer getCustomers() {
        return customers;
    }

    public void setCustomers(Integer customers) {
        this.customers = customers;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getPiecePrice() {
        return piecePrice;
    }

    public void setPiecePrice(BigDecimal piecePrice) {
        this.piecePrice = piecePrice;
    }

    public BigDecimal getPenetrationNumber() {
        return penetrationNumber;
    }

    public void setPenetrationNumber(BigDecimal penetrationNumber) {
        this.penetrationNumber = penetrationNumber;
    }

    public BigDecimal getGrossProfitMargin() {
        return grossProfitMargin;
    }

    public void setGrossProfitMargin(BigDecimal grossProfitMargin) {
        this.grossProfitMargin = grossProfitMargin;
    }

    public Integer getUpperShelfSku() {
        return upperShelfSku;
    }

    public void setUpperShelfSku(Integer upperShelfSku) {
        this.upperShelfSku = upperShelfSku;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve == null ? null : reserve.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}