package com.yc.repository;

import com.yc.bean.Resorder;
import com.yc.enums.OrderStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.sql.Timestamp;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ResorderDaoTest {

    @Autowired
    private ResorderDao dao;

    @Test
    public void addResorder(){
        Resorder ro=new Resorder();
        ro.setAddress("湖南");
        ro.setDeliverytime(new Timestamp(new Date().getTime()));
        ro.setOrdertime(new Timestamp(new Date().getTime()));
        ro.setPs("quick");
        ro.setTel("17872138218");
        //枚举
        ro.setStatus(OrderStatusEnum.NEW.getCode());
        ro.setUserid(2);
        ro=dao.save(ro);
        //jpa自动返回一个被jpa托管的对象
        System.out.println(ro.getRoid());
    }
}