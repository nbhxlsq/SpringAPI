package com.bluesky.myspring.service.impl;
import com.bluesky.myspring.entity.AuthorUser;
import com.bluesky.myspring.entity.MyUser;
import com.bluesky.myspring.entity.ResultInfo;
import com.bluesky.myspring.repository.AuthorUserRepository;
import com.bluesky.myspring.repository.UserRepository;
import com.bluesky.myspring.service.port.IUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements IUService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorUserRepository authorUserRepository;
    @Override
    public MyUser findById(Long id) {

        return userRepository.findById(id).orElse(null);
    }


    @Override
    public MyUser SaveUser(MyUser user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public int ModifyUser(MyUser user) {
        //BeanUtils.copyProperties();
        int effect= userRepository.ModifyNickName(user);
        return  effect;
    }

    @Override
    public Optional<AuthorUser> GetLoginUser(AuthorUser user) {
        ExampleMatcher matcher=ExampleMatcher.matching()
                .withMatcher("loginname", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("upass",ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("isablelogin",ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("isadmin");

        Example example=Example.of(user,matcher);

        authorUserRepository.findOne(example);
        Optional<AuthorUser> listuser= authorUserRepository.findOne(example);
      return  listuser;
    }

    private Optional<AuthorUser> VialdAuthorUser(AuthorUser user){
        AuthorUser filter=new AuthorUser();
        filter.setLoginname(user.getLoginname());
        filter.setUemail(user.getUemail());
        filter.setUcode(user.getUcode());
        ExampleMatcher matcher=ExampleMatcher.matchingAny()
                .withMatcher("ucode", ExampleMatcher.GenericPropertyMatchers.ignoreCase().contains())
                .withMatcher("LoginName",ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("uemail",ExampleMatcher.GenericPropertyMatchers.ignoreCase().contains())
                .withIgnorePaths("isadmin");
        Example<AuthorUser> example=Example.of(filter,matcher);
        return  authorUserRepository.findOne(example);
    }

    public ResultInfo<AuthorUser> SaveAuthorUser(AuthorUser user){
        ResultInfo<AuthorUser> result=new ResultInfo<AuthorUser>();
        if(StringUtils.isEmpty(user.getUid())){
            String uid=UUID.randomUUID().toString();
            user.setUid(uid);
            user.setCreateby(uid);
            user.setCreatedate(new Date());
            user.setModifyby(uid);
            user.setModifydate(new Date());
           Optional<AuthorUser> viald= VialdAuthorUser(user);
           if(viald.isPresent()){
               StringBuilder sb=new StringBuilder();
               if(viald.get().getUcode().equals(user.getUcode())){
                   sb.append("身份号重复");
               }
                if(viald.get().getLoginname().equals(user.getLoginname())) {
                    sb.append("登录名称重复");
                }
                if(viald.get().getUemail().equals(user.getUemail())){
                    sb.append("邮箱地址重复");
                }
                result.setIssucess(false);
                result.setMessage(sb.toString());
                return  result;
           }

        }else{
            user.setModifydate(new Date());
        }

        AuthorUser data= authorUserRepository.save(user);
        result.setDataview(data);
        return  result;
    }

}
