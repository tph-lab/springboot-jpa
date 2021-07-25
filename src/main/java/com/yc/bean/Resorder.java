package com.yc.bean;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
public class Resorder {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer roid;
    private Integer userid;
    private String address;
    private String tel;
    private Timestamp ordertime;
    private Timestamp deliverytime;     //Po中的用的却是Timestamp
    private String ps;
    private Integer status;

    @Transient
    private String delivertimeString;   //VO中界面的参数类型只能是字符串

    public Timestamp getDeliveryTime()  {
        Date d=null;
        try{
            if(delivertimeString!=null){
                DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm");
                d=df.parse(delivertimeString);
                deliverytime=new Timestamp(d.getTime());
            }else{
                d=new Date();
            }
            deliverytime=new Timestamp(d.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }
        return deliverytime;
    }
}
