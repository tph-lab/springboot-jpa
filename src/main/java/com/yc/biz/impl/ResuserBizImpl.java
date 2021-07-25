package com.yc.biz.impl;

import com.yc.bean.Resuser;
import com.yc.biz.ResuserBiz;
import com.yc.repository.ResuserDao;
import com.yc.utils.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class ResuserBizImpl implements ResuserBiz {

    @Autowired
    private ResuserDao resuserDao;

    @Override
    @Transactional(readOnly = true)
    public Resuser login(Resuser user) {
        //因为数据库的密码是加了密，所以需要加了密再查询
        user.setPwd(Encrypt.md5(user.getPwd()));   //业务层完成对原始密码的加密
        Example<Resuser> example=Example.of(user);
        Optional<Resuser> optional=resuserDao.findOne(example);
        return optional.get();
    }
}
