package com.yc.repository;

import com.yc.bean.Resfood;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository<Resfood,Integer>     第一个参数:操作的实体类           第二个参数：主键对应的属性类型
public interface ResfoodDao extends JpaRepository<Resfood,Integer> {

}
