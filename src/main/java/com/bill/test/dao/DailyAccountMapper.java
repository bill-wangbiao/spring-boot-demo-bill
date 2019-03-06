package com.bill.test.dao;

import com.bill.test.model.DailyAccount;

public interface DailyAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DailyAccount record);

    int insertSelective(DailyAccount record);

    DailyAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DailyAccount record);

    int updateByPrimaryKey(DailyAccount record);
}