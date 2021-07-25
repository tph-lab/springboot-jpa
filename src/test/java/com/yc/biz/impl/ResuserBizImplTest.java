package com.yc.biz.impl;

import com.yc.bean.Resuser;
import com.yc.biz.ResuserBiz;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class ResuserBizImplTest {

    @Autowired
    private ResuserBiz resuserBiz;

    @Test
    void login() {
        Resuser u=new Resuser();
        u.setUsername("a");
        u.setPwd("a");

        Resuser r=resuserBiz.login(u);
        Assert.assertNotNull(r);
    }
}