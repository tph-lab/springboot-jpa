package com.yc.biz;

import com.yc.bean.Resfood;

import java.util.List;

public interface ResfoodBiz {

    /**
     * 查找所有的菜
     * @return
     */
    public List<Resfood> findAll();


    /**
     * 根据fid查找某个菜
     * @param fid
     * @return
     */
    public Resfood findByFid(Integer fid);

}
