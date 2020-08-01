package com.imooc3.service.Impl.center;


import com.imooc3.enums.Sex;
import com.imooc3.mapper.UsersMapper;
import com.imooc3.pojo.Users;
import com.imooc3.pojo.bo.UserBO;
import com.imooc3.pojo.bo.center.CenterUserBO;
import com.imooc3.service.UserService;
import com.imooc3.service.center.CenterUserService;
import com.imooc3.utils.DateUtil;
import com.imooc3.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;


@Service
public class CenterUserServiceImpl implements CenterUserService {
    @Autowired
    public UsersMapper usersMapper;

    @Transactional(propagation= Propagation.SUPPORTS)
    @Override
    public Users queryUserInfo(String userId) {
        Users user = usersMapper.selectByPrimaryKey(userId);
        user.setPassword(null);
        return user;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public Users updateUserInfo(String userId, CenterUserBO centerUserBO) {

        Users updateUser = new Users();
        BeanUtils.copyProperties(centerUserBO, updateUser);
        updateUser.setId(userId);
        updateUser.setUpdatedTime(new Date());

        usersMapper.updateByPrimaryKeySelective(updateUser);


        return queryUserInfo(userId);
    }

    @Transactional(propagation =  Propagation.REQUIRED)
    @Override
    public Users updateUserFace(String userId, String faceUrl) {
        Users updateUser = new Users();
        updateUser.setId(userId);
        updateUser.setFace(faceUrl);
        updateUser.setUpdatedTime(new Date());
        //更新
        usersMapper.updateByPrimaryKeySelective(updateUser);

        //更新完以后把更新完的数据查询后返回出来
        return queryUserInfo(userId);
    }
}
