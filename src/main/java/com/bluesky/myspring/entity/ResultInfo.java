package com.bluesky.myspring.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.io.Serializable;

@Setter
@Getter
public class ResultInfo<T> implements Serializable {
    /**
     * 是否成功
     */
    public   Boolean issucess;
    /**
     * 返馈消息
     */
    public   String message;
    /**
     * 附加代码
     */
    public   String targcode;
    /**
     * 业务数据
     */
    public  T dataview;
}
