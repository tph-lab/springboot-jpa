package com.yc.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//在生成的json字符串中排除空属性
public class JsonModel implements Serializable {

    private Integer code;
    private String msg;
    private Object obj;
    private String url;



}
