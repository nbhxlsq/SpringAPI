package com.bluesky.myspring.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
//import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//import static javax.persistence.TemporalType.*;

@Entity
@Setter
@Getter
@Table(name = "AuthorUser")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class AuthorUser implements Serializable {
    @Id
    @Column(name = "UID", nullable = false, unique = true)
    private  String uid;
    /**
     * 用户姓名
     */
    @Column(name="UName",  nullable = false)
    private  String uname;
    /**
     *  微分证号
     */
    @Column(name="UCode",nullable = false,length = 18)
    private String ucode;
    /**
     * 出生日期
     */
    @Column(name = "UBirthday",nullable = true)
    @Temporal(value = TemporalType.DATE)
    private Date ubirthday;
    /**
     * 登录密码
     */
    @Column(name = "UPass",nullable = true)
    private String upass;
    /**
     * 邮箱
     */
    @Column(name = "UEmail",nullable = false)
    private  String uemail;
    /**
     * 系统登录名称
     */
    @Column(name = "LoginName", nullable = true)
    private  String loginname;
    /**
     * 是否允许登录
     */
    @Column(name = "IsAbleLogin",nullable = true )
    private  boolean isablelogin;
    /**
     * 是否超级管理员（原则只允许一个）
     */
    @Column(name = "IsAdmin",nullable = false)
    private  boolean isadmin;
    /**
     *
     */
    @Column(name = "CreateBy" ,nullable = false)
    private  String createby;
    /**
     *
     */
    @Column(name = "CreateDate",nullable = false)
    private  Date createdate;
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
