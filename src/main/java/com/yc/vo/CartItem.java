package com.yc.vo;

import com.yc.bean.Resfood;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CartItem implements Serializable {

    private Resfood food;
    private int num;
    private BigDecimal smallCount;

    public BigDecimal getSmallCount(){
        this.smallCount=food.getRealprice().multiply(new BigDecimal(num));
        return smallCount;
    }


}
