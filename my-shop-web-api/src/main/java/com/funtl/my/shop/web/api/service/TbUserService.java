package com.funtl.my.shop.web.api.service;

import com.funtl.my.shop.domain.TbUser;

public interface TbUserService {

    /**
     * 登录
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);
}
