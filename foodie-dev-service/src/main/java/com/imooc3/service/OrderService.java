package com.imooc3.service;

import com.imooc3.pojo.UserAddress;
import com.imooc3.pojo.bo.AddressBO;
import com.imooc3.pojo.bo.SubmitOrderBO;

import java.util.List;

public interface OrderService {

    /**
     * 用于创建订单相关信息
     * @param submitOrderBO
     */
    public String createOrder(SubmitOrderBO submitOrderBO);

    /**
     * 修改订单状态
     * @param orderId
     * @param orderStatus
     */
    public void updateOrderStatus(String orderId, Integer orderStatus);
}
