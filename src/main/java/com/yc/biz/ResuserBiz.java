package com.yc.biz;

import com.yc.bean.Resuser;

public interface ResuserBiz {

    /**
     * 登录业务
     * @param user
     * @return
     */
    public Resuser login(Resuser user);
}
