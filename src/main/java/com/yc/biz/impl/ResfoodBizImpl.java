package com.yc.biz.impl;

import com.yc.bean.Resfood;
import com.yc.biz.ResfoodBiz;
import com.yc.repository.ResfoodDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
//事务注解最好加在业务层上
@Transactional  //springboot中直接使用@Transactional即可开启事务
public class ResfoodBizImpl implements ResfoodBiz {

    @Autowired
    private ResfoodDao resfoodDao;

    @Override//查询，采用只读事务
    @Transactional(readOnly = true)
    public List<Resfood> findAll(){
        return resfoodDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Resfood findByFid(Integer fid) {
        Resfood rf=new Resfood();
        rf.setFid(fid);
        Example<Resfood> example=Example.of(rf);
        //optional可以为空，也可以有值
        Optional<Resfood> opt=resfoodDao.findOne(example);
        /**
         *  public T get() {
         *         if (value == null) {
         *             throw new NoSuchElementException("No value present");
         *         }
         *         return value;
         *     }
         */
        return opt.get();
    }


}
