//package com.yc.biz.impl;
//
//import com.yc.bean.Resorder;
//import com.yc.biz.ResfoodBiz;
//import com.yc.biz.ResorderBiz;
//import com.yc.enums.OrderStatusEnum;
//import com.yc.vo.CartItem;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.sql.Timestamp;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ResorderBizImplTest {
//
//    @Autowired
//    private ResfoodBiz resfoodBiz;
//    @Autowired
//    private ResorderBiz resorderBiz;
//
//    @Test
//    void completeOrder() {
//        Resorder o=new Resorder();
//        o.setUserid(1);
//        o.setStatus(OrderStatusEnum.NEW.getCode());
//        o.setTel("17872138218");
//        o.setPs("快");
//        o.setAddress("湖南工学院");
//        o.setOrdertime(new Timestamp(new Date().getTime()));
//
//        //购物车
//        Map<Integer, CartItem> cart=new HashMap<>();
//
//        Integer fid1=11;
//        CartItem ci1=new CartItem();
//        ci1.setFood(resfoodBiz.findByFid(fid1));
//        ci1.setNum(1);
//
//
//        Integer fid2=12;
//        CartItem ci2=new CartItem();
//        ci2.setFood(resfoodBiz.findByFid(fid2));
//        ci2.setNum(1);
//
//        cart.put(fid1,ci1);
//        cart.put(fid2,ci2);
//        resorderBiz.completeOrder(o,cart);
//    }
//}