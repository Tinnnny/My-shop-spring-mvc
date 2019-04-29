package com.funtl.my.shop.web.admin.service.impl;

import com.funtl.my.shop.commons.dto.BaseResult;
import com.funtl.my.shop.commons.validator.BeanValidator;
import com.funtl.my.shop.domain.TbContentCategory;
import com.funtl.my.shop.web.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.funtl.my.shop.web.admin.dao.TbContentCategoryDao;
import com.funtl.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.stereotype.Service;

import java.util.Date;


//第二个实现是为了注入，因为在ContentCategoryController里的service只有接口没有实现，所以这里要实现一下
@Service
public class TbContentCategoryServiceImpl extends AbstractBaseTreeServiceImpl <TbContentCategory,TbContentCategoryDao> implements TbContentCategoryService{


    @Override
    public BaseResult save(TbContentCategory entity) {
        String validator = BeanValidator.validator(entity);
        //验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        //验证通过
        else {
            TbContentCategory parent = entity.getParent();
            if (parent ==null || parent.getId() ==null){
                //代表根目录
                parent.setId(0L);

            }
            entity.setUpdated(new Date());
            if (entity.getId()==null){
                entity.setCreated(new Date());
                entity.setIsParent(false);
                if (parent.getId()!=0L){
                    //判断当前新增的节点有没有父节点
                    TbContentCategory currentCategoryParent = getById(parent.getId());
                    if (currentCategoryParent!=null){
                        //为父级节点设置isparent
                        currentCategoryParent.setIsParent(true);
                        update(currentCategoryParent);
                    }
                }
//                父级节点为0，表示为根目录
                else {
                    //根目录一定是父级目录
                    entity.setIsParent(true);
                }


                dao.insert(entity);
            }
            //修改
            else {
                update(entity);
            }

            return BaseResult.success("保存信息成功！");
        }
    }
}
