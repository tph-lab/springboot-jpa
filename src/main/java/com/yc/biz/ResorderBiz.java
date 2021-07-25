package com.yc.biz;

import com.yc.bean.Resorder;
import com.yc.vo.CartItem;

import java.util.Map;

public interface ResorderBiz {

    /**
     * 完成订单
     * @param resorder
     * @param shopCart
     * @return
     */
    public Integer completeOrder(Resorder resorder, Map<Integer, CartItem> shopCart);

}
