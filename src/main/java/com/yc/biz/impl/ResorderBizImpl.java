package com.yc.biz.impl;

import com.yc.bean.Resorder;
import com.yc.bean.Resorderitem;
import com.yc.biz.ResorderBiz;
import com.yc.enums.OrderStatusEnum;
import com.yc.repository.ResorderDao;
import com.yc.repository.ResorderitemDao;
import com.yc.vo.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Map;

@Service
@Transactional
public class ResorderBizImpl implements ResorderBiz {

    @Autowired
    private ResorderDao resorderDao;

    @Autowired
    private ResorderitemDao resorderitemDao;


    @Override
    public Integer completeOrder(Resorder resorder, Map<Integer, CartItem> shopCart) {

        resorder.setStatus(OrderStatusEnum.NEW.getCode());
        resorder.setOrdertime(new Timestamp(System.currentTimeMillis()));
        resorder.setDeliverytime(resorder.getDeliveryTime());


       //添加数据的时候，会自动将生成的id存入对象中
        Resorder orderResult=resorderDao.save(resorder);
        if(shopCart!=null && shopCart.size()>0){
            //键值对的强循环
            for(Map.Entry<Integer,CartItem> entry:shopCart.entrySet()){
                Resorderitem ri=new Resorderitem();
                ri.setRoid(orderResult.getRoid());
                ri.setNum(entry.getValue().getNum());
                ri.setFid(entry.getKey());
                ri.setDealprice(entry.getValue().getFood().getRealprice());
                resorderitemDao.save(ri);
            }
        }
        //可以得到自动生成的主键
        return orderResult.getRoid();
    }
}
