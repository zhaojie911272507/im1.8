package com.imooc3.service.Impl;



import com.imooc3.enums.Sex;
import com.imooc3.mapper.UsersMapper;
import com.imooc3.pojo.Users;
import com.imooc3.pojo.io.UserBO;
import com.imooc3.service.UserService;

import com.imooc3.utils.DateUtil;
import com.imooc3.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UsersMapper usersMapper;
    @Autowired
    private Sid sid;

    private static final String USER_FACE="http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Transactional(propagation= Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {

        Example userExample = new Example(Users.class);
        Example.Criteria userCrteria = userExample.createCriteria();

        userCrteria.andEqualTo("username",username);
        Users result = usersMapper.selectOneByExample(userExample);/*用Users用于接收*/
        return result == null ? false : true;
    }


    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public Users createUser(UserBO userBO) {

        String userId = sid.nextShort();/*生成全局id*/

        Users user = new Users();
        user.setId(userId);
        user.setUsername(userBO.getUsername());
        try {
            user.setPassword(MD5Utils.getMD5Str(userBO.getPassword()));/*这里要加密,在common层加入md5加密类*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*默认用户昵称同用户名*/
        user.setNickname(userBO.getUsername());
        /*默认头像*/
        user.setFace(USER_FACE);
        // 默认生日
        user.setBirthday(DateUtil.stringToDate("1900-01-01"));/*String装换为date类型*/
        // 默认性别为 保密   /*不推荐直接写死，这里要在common里新建枚举*/
        user.setSex(Sex.secret.type);

        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());

        usersMapper.insert(user);
        return user;
    }


}
