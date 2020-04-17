package com.bluesky.myspring.service.port;

import com.bluesky.myspring.entity.AuthorUser;
import com.bluesky.myspring.entity.MyUser;

import com.bluesky.myspring.entity.ResultInfo;
import org.hibernate.criterion.Example;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

//@Component
public interface IUService {

    public MyUser findById(Long id);

    public MyUser SaveUser(MyUser user);


    public int ModifyUser(MyUser user);

    public Optional<AuthorUser> GetLoginUser(AuthorUser user);

    public ResultInfo<AuthorUser> SaveAuthorUser(AuthorUser user);
}

