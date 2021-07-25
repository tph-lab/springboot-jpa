package com.yc.bean;


import lombok.Data;

import javax.persistence.Entity;//jpa包下的类
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity     //实体类
@Data
public class Resadmin implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer raid;       //用引用类型
    private String raname;
    private String rapws;

}
