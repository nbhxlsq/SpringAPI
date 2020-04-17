package com.bluesky.myspring.controller;

import com.bluesky.myspring.entity.*;
import com.bluesky.myspring.service.port.ICommonService;
import com.bluesky.myspring.service.port.IUService;
//import com.sun.xml.internal.ws.api.message.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/author")
public class CommonController {


    @Autowired
    private  IUService userservice;
    @Autowired
    private ICommonService commonService;

    @PostMapping(path = "addPerson", consumes =MediaType.APPLICATION_JSON_UTF8_VALUE, produces = "application/json;charset=UTF-8")
    public void addPerson(@RequestBody MyUser person) {

        userservice.SaveUser(person);
    }


    @GetMapping( path = "getuser", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public  Object QueryUer(@QueryParam("uid") String uid ){
        MyUser user=   userservice.findById(Long.parseLong(uid));

        return  user;
    }
    //新分支提交
    @RequestMapping( path = "upuser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    public ResultInfo<String> ChangeName(@RequestBody MyUser user)
    {
        ResultInfo<String> result=new ResultInfo<String>();
        int effect= userservice.ModifyUser(user);
        result.issucess=effect>0;
        if(result.issucess){
            result.message="is't OK";
            result.targcode=String.valueOf(effect);
        }
        return  result;
    }

    /**
     * 登录系统
     * @param name
     * @param pass
     * @return
     */
    @PutMapping(path = "loginsys",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultInfo<AuthorUser> LoginSys(@QueryParam("name") String name,@QueryParam("pass")String pass){
        ResultInfo<AuthorUser> result=new ResultInfo<AuthorUser>();
        AuthorUser user=new AuthorUser();
        user.setLoginname(name);
        user.setUpass(pass);
        user.setIsablelogin(true);
      Optional<AuthorUser> data= userservice.GetLoginUser(user);
      result.issucess= data.isPresent();
      if(!result.getIssucess()){
          result.setMessage("当前登录信息不可用");
      }else {
          result.dataview = data.get();
      }
      return  result;
    }

    @PostMapping(path="saveauthoruser", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  ResultInfo<AuthorUser> SaveAuthorUser(@RequestBody  AuthorUser user){
        ResultInfo<AuthorUser> result= userservice.SaveAuthorUser(user);
        return  result;
    }

    /**
     * 根据系统类型获取根菜单目录
     * @param systype
     * @return
     */
    @GetMapping(path="getrootmenu",consumes =MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public  ResultInfo<List<AuthorMenu>> GetSysRootMenu(String systype){
        ResultInfo<List<AuthorMenu>> result=new ResultInfo<List<AuthorMenu>>();
        SysTypeEnum systenum= Enum.valueOf(SysTypeEnum.class,systype);
        result= commonService.GetSysRootMenuByType(systenum.getSysvalue());
        return  result;
    }

    @PostMapping(path = "savemeu", consumes =MediaType.APPLICATION_JSON_UTF8_VALUE, produces =MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultInfo<Boolean> SaveMenu(@RequestBody AuthorMenu menu)
    {
        return  commonService.SaveMenu(menu);
    }
    @PostMapping(path = "vialdmenu", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultInfo<String> VialdMenu(@RequestBody AuthorMenu menu)
    {
        return  commonService.VialdMenu(menu);
    }
}

