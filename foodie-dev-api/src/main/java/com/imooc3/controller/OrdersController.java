package com.imooc3.controller;


import com.imooc3.enums.OrderStatusEnum;
import com.imooc3.enums.PayMethod;
import com.imooc3.pojo.bo.SubmitOrderBO;
import com.imooc3.service.OrderService;
import com.imooc3.utils.IMOOCJSONResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Api(value = "订单相关", tags = {"订单相关Api接口"})
@RestController
@RequestMapping("orders")
public class OrdersController extends BaseController{

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public IMOOCJSONResult create(@RequestBody SubmitOrderBO submitOrderBO,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {


        if(submitOrderBO.getPayMethod() != PayMethod.WEIXIN.type
           && submitOrderBO.getPayMethod() != PayMethod.ALIPAY.type){
            return IMOOCJSONResult.errorMsg("支付方式不支持");
        }

        System.out.println(submitOrderBO.toString());

        //1.创建订单
         String orderId = orderService.createOrder(submitOrderBO);
        // 2. 创建订单以后，移除购物车中已结算（已提交）的商品
        /**
         * 1001
         * 2002 -> 用户购买
         * 3003 -> 用户购买
         * 4004
         */
        // TODO 整合redis之后，完善购物车中的已结算商品清除，并且同步到前端的cookie
        // CookieUtils.setCookie(request, response, FOODIE_SHOPCART, "", true);

        //3.想支付中心发送当前订单，用于保存支付中心的订单
        return IMOOCJSONResult.ok(orderId);
    }

    @PostMapping("notifyMerchantOrderPaid")
    public int notifyMerchantOrderPaid(String merchantOrderId) {
        orderService.updateOrderStatus(merchantOrderId , OrderStatusEnum.WAIT_DELIVER.type);
        return HttpStatus.OK.value();
    }
}