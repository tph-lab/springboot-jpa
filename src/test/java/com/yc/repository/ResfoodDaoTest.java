package com.yc.repository;

import com.yc.bean.Resfood;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Supplier;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ResfoodDaoTest {

    @Autowired
    private ResfoodDao dao;

    //按某个条件查询
    @Test
    public void findResfoodByFid(){
        Resfood rf=new Resfood();
        rf.setFid(12);
        //
        Example<Resfood> example=Example.of(rf);
        Optional<Resfood> opt=dao.findOne(example);  //optional
        opt.orElseGet(new Supplier<Resfood>() {
            @Override
            public Resfood get() {
                return new Resfood();
            }
        });
        Assert.assertNotNull(opt.get());
    }

    @Test
    public void addResfood(){
       Resfood rf=new Resfood();
       rf.setFname("辣椒炒肉");
       rf.setFphoto("500026.jpg");
       rf.setDetail("good");
        BigDecimal np=new BigDecimal(30.0);
        rf.setNormprice(np);
        rf.setRealprice(new BigDecimal(40.0));
        dao.save(rf);
    }

}