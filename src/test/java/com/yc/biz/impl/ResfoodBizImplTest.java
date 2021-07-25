package com.yc.biz.impl;

import com.yc.bean.Resfood;
import com.yc.biz.ResfoodBiz;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class ResfoodBizImplTest {

    @Autowired
    private ResfoodBiz resfoodBiz;

    @Test
    void findAll() {
        List<Resfood> list=resfoodBiz.findAll();
        System.out.println(list);
    }


    @Test
    void findById() {
        Resfood f=resfoodBiz.findByFid(13);
        Assert.assertNotNull(f);
    }
}