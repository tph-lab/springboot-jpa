package com.yc.repository;


import com.yc.bean.Resorder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResorderDao extends JpaRepository<Resorder,Integer> {
}
