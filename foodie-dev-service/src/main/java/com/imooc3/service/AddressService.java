package com.imooc3.service;

import com.imooc3.pojo.Carousel;
import com.imooc3.pojo.UserAddress;
import com.imooc3.pojo.bo.AddressBO;

import java.util.List;

public interface AddressService {
    /**
     *根据用户id查询用户的收货地址列表
     * @param UserId
     * @return
     */
    public List<UserAddress> queryAll(String UserId);

    /**
     * 用户新增地址
     * @param addressBO
     */
    public void addNewUserAddress(AddressBO addressBO);

    /**
     * 用户修改地址
     * @param addressBO
     */
    public void updateUserAddress(AddressBO addressBO);

    /**
     * 根据用户id和地址id删除对应地址
     * @param userId
     * @param addressId
     */
    public void deleteUserAddress(String userId ,String addressId);

    /**
     * 修改默认地址
     * @param userId
     * @param addressId
     */
    public void updateUserAddressToBeDefault(String userId, String addressId);

    /**
     * 根据用户id和地址id，查询具体的用户地址对象信息
     * @param userId
     * @param addressId
     * @return
     */
    public UserAddress queryUserAddress(String userId, String addressId);
}
