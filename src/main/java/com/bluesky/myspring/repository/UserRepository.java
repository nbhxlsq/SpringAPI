package com.bluesky.myspring.repository;

import com.bluesky.myspring.entity.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import javax.ws.rs.ext.ParamConverter;

@Repository
public interface UserRepository extends JpaRepository<MyUser,Long> {
    /**
     * 更新部字段
     * @param user
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "update myuser u set u.nickname=:#{#user.nickName} where u.id=:#{#user.id}",nativeQuery = true)
    public  int ModifyNickName(@Param("user")MyUser user);
}