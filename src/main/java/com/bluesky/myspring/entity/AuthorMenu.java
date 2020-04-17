package com.bluesky.myspring.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "AuthorMenu")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class AuthorMenu implements Serializable {
    /**
     * 菜单ID
     */
    @Id
    @Column(name = "MID", nullable = false)
    private  String mid;
    /**
     * 菜单类型0管理系统菜单
     */
    @Column(name = "MType",nullable = false)
    private Integer mtype;
    /**
     * 菜单级数
     */
    @Column(name = "MLeve",nullable = false)
    private Integer mleve;
    /**
     * 菜单名称
     */
    @Column(name = "MName",nullable = false)
    private String mname;
    /**
     * 菜单路径
     */
    @Column(name = "MUrl",nullable = true)
    private String murl;
    /**
     * 菜单父级
     */
    @Column(name = "MPrdid", nullable = true)
    private String mprdid;
    /**
     * 是否可用
     */
    @Column(name = "MIsable",nullable = false)
    private Boolean misable;
    /**
     *
     */
    @Column(name = "CreateBy" ,nullable = false)
    private  String createby;
    /**
     *
     */
    @Column(name = "CreateDate",nullable = false)
    private Date createdate;
    /**
     *
     */
    @Column(name = "ModifyBy",nullable = false)
    private  String modifyby;
    /**
     *
     */
    @Column(name = "ModifyDate", nullable = false)
    private  Date modifydate;

}
