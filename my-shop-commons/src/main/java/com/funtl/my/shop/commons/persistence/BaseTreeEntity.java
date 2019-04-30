package com.funtl.my.shop.commons.persistence;

import lombok.Data;

import java.io.Serializable;
@Data
public abstract class BaseTreeEntity<T extends BaseEntity> extends BaseEntity implements Serializable {

    private Boolean isParent;
    private T parent;


}
