package com.lianfu.mapper;

import com.lianfu.pojo.Orders;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    public int addOrder(Orders order);
    public Orders findOrderByUUid(String uuid);
    public int editOrderState(Orders orders);
    public int edithight(int money);
}
