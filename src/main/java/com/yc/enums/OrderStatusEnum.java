package com.yc.enums;


public enum OrderStatusEnum {


     NEW("新订单",0),
     FINISHED("已完结",1),
     CANCEL("已取消",2);



     private String message;
     private Integer code;

     private OrderStatusEnum(String message, int code) {
     }

     public String getMessage() {
          return message;
     }

     public Integer getCode() {
          return code;
     }
}

