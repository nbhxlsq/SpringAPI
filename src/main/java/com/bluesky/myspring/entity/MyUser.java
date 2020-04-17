package com.bluesky.myspring.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@Table(name = "myuser")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class MyUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "email", nullable = true, length = 45)
    private String email;
    @Column(name = "pwd", nullable = false, length = 45)
    private String pwd;
    @Column(name = "nickname", nullable = true, length = 45)
    private String nickName;

}
