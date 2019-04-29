package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.dto.PageInfo;

import com.funtl.my.shop.commons.validator.BeanValidator;
import com.funtl.my.shop.domain.TbContent;

import com.funtl.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.funtl.my.shop.web.admin.dao.TbContentDao;
import com.funtl.my.shop.web.admin.service.TbContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbContentServiceImpl extends AbstractBaseServiceImpl<TbContent ,TbContentDao> implements TbContentService {


    @Override
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator(tbContent);
        //验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);
        }

        else {
            tbContent.setUpdated(new Date());
            //新增
            if (tbContent.getId() == null) {

                tbContent.setCreated(new Date());
                dao.insert(tbContent);
            }
            //编辑用户
            else {
                update(tbContent);
            }
            return BaseResult.success("保存信息成功！");
        }
    }

}


