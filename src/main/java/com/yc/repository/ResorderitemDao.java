package com.yc.repository;

import com.yc.bean.Resorder;
import com.yc.bean.Resorderitem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResorderitemDao extends JpaRepository<Resorderitem,Integer> {
}
