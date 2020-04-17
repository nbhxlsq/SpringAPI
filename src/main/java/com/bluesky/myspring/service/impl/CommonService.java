package com.bluesky.myspring.service.impl;

import com.bluesky.myspring.entity.AuthorMenu;
import com.bluesky.myspring.entity.ResultInfo;
import com.bluesky.myspring.entity.ShareDataInfo;
import com.bluesky.myspring.repository.AuthorMenuRepository;
import com.bluesky.myspring.service.port.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommonService implements ICommonService {

    @Autowired
    AuthorMenuRepository repository;
    @Override
    public ResultInfo<List<AuthorMenu>> GetSysRootMenuByType(Integer type) {
        AuthorMenu filter=new AuthorMenu();
        filter.setMtype(type);
        filter.setMprdid(null);
        filter.setMisable(true);
        Example<AuthorMenu> example=Example.of(filter);
       List<AuthorMenu> listdata= repository.findAll(example);
       ResultInfo<List<AuthorMenu>> result=new ResultInfo<List<AuthorMenu>>();
       result.issucess=!listdata.isEmpty();
       if(result.issucess){
           result.setDataview(listdata);
       }
        return result;
    }

    @Override
    public ResultInfo<List<AuthorMenu>> GetChildrenMenu(String prdid) {
        ResultInfo<List<AuthorMenu>> result=new ResultInfo<List<AuthorMenu>>();
        AuthorMenu filter=new AuthorMenu();
        filter.setMprdid(prdid);
        filter.setMisable(true);
       Example<AuthorMenu> example=Example.of(filter);
       List<AuthorMenu> listdata=  repository.findAll(example);
       result.setIssucess(!listdata.isEmpty());
       if(result.getIssucess()){
           result.setDataview(listdata);
       }
        return result;
    }

    @Override
    public ResultInfo<Boolean> SaveMenu(AuthorMenu menu) {
        ResultInfo<Boolean> result=new ResultInfo<Boolean>();
        if(StringUtils.isEmpty(menu.getMid())){
            String tmd=UUID.randomUUID().toString();
            menu.setMid(UUID.randomUUID().toString());
            menu.setCreateby(tmd);
            menu.setModifyby(tmd);
            menu.setCreatedate(new Date());
            menu.setModifydate(new Date());
        }else{
            menu.setModifydate(new Date());
        }
        AuthorMenu data= repository.save(menu);
        result.setIssucess(true);
        return  result;

    }

    @Override
    public ResultInfo<Boolean> RemoveMenu(String mid) {
        ResultInfo<Boolean> result=new ResultInfo<Boolean>();
       repository.deleteById(mid);
        result.setIssucess(true);
        return  result;
    }

    @Override
    public ResultInfo<Boolean> LockOrUnLockMenu(String mid,Boolean isfreeze) {
        ResultInfo<Boolean> result=new ResultInfo<Boolean>();
        AuthorMenu menu= repository.getOne(mid);
        menu.setMisable(!isfreeze);
        menu.setModifydate(new Date());
        repository.save(menu);
        result.setIssucess(true);
        return result;
    }

    /**
     * 验证当前菜单 是否有效
     * @param menu
     * @return
     */
    @Override
    public ResultInfo<String> VialdMenu(AuthorMenu menu) {
        ResultInfo<String> result=new ResultInfo<String>();
        ExampleMatcher matcher=ExampleMatcher.matchingAll()
                .withMatcher("mtype",ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("mname",ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("mprdid",ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnorePaths("misable");
        Example<AuthorMenu> example=Example.of(menu,matcher);
       List<AuthorMenu> data= repository.findAll(example);
       result.setIssucess(data.isEmpty());
       if(!result.getIssucess()){
           result.setMessage("当前菜单不可用");
       }
       return  result;
    }
}
