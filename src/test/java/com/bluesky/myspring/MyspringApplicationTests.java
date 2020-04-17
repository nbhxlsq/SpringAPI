package com.bluesky.myspring;


import com.bluesky.myspring.controller.HelloController;
import com.bluesky.myspring.controller.CommonController;
import com.bluesky.myspring.entity.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.http.MediaType;

import java.sql.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MyspringApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@WebAppConfiguration
public class MyspringApplicationTests {
    @Autowired
    CommonController commonController;
    private MockMvc mvc;
    //private MockRestServiceServer mockServer;
    @Before
    public void setup()  {
        mvc = MockMvcBuilders.standaloneSetup(
                new HelloController(),
                commonController
        ).build();

        //mockServer = MockRestServiceServer.createServer(helperClass.getRestTemplate());
    }

    @Test
    public void getHello() throws Exception {
       String strresult= mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World")))
                .andReturn().getResponse().getContentAsString();
        Assert.assertTrue("测试成功",strresult.length()>0);
    }

    @Test
    public  void TestUpUser() throws Exception {
                long lid=2;
                MyUser user=new MyUser();
                user.setId(lid);
                user.setEmail("dafewfe");
                user.setPwd("uytreww");
                user.setNickName("TMD 我又回来了3");
                ObjectMapper objmapper=new ObjectMapper();
                String jsonstr= objmapper.writeValueAsString(user);
                String strresult= mvc.perform(MockMvcRequestBuilders.post("/author/upuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonstr))
                        .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
                Assert.assertTrue("TMD",strresult.length()>0);
    }

    @Test
    public  void TestSaveAuthoruser() throws  Exception{
        AuthorUser user=new AuthorUser();
        user.setIsablelogin(true);
        user.setIsadmin(true);
        user.setUpass("123");
        user.setUcode("510531198721341681");
        user.setUname("超级管理员");
        user.setLoginname("admin");
        user.setUemail("admin@qq.com");
        user.setUbirthday(Date.valueOf("1986-02-05"));
        ObjectMapper objmapper=new ObjectMapper();
        String jsonstr= objmapper.writeValueAsString(user);
        String strresult= mvc.perform(MockMvcRequestBuilders.post("/author/saveauthoruser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonstr))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assert.assertTrue("TMD",strresult.length()>0);
    }

    @Test
    public  void TestLoginsys() throws  Exception
    {
        ObjectMapper objmapper=new ObjectMapper();
        String strresult= mvc.perform(MockMvcRequestBuilders.put("/author/loginsys?loginname=admin&upass=123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assert.assertTrue("TMD",strresult.length()>0);
    }
    @Test
public void  TestSaveMenu() throws Exception {
    AuthorMenu menu=new AuthorMenu();
    menu.setMisable(true);
    menu.setMprdid(null);
    menu.setMtype(SysTypeEnum.BusWebSys.getSysvalue());
    menu.setMleve(0);
    menu.setMname("百度");
    menu.setMurl("www.baidu.com");
    ObjectMapper objmapper=new ObjectMapper();
    String jsonstr= objmapper.writeValueAsString(menu);
    String strresult= mvc.perform(MockMvcRequestBuilders.post("/author/savemeu")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonstr))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
    Assert.assertTrue("TMD",strresult.length()>0);
}
@Test
public  void TestVialdMenu() throws Exception {
    AuthorMenu menu=new AuthorMenu();
    menu.setMisable(true);
    menu.setMprdid(null);
    menu.setMtype(SysTypeEnum.BusWebSys.getSysvalue());
    menu.setMleve(0);
    menu.setMname("百度");
    menu.setMurl("www.baidu.com");
    ObjectMapper objmapper=new ObjectMapper();
    String jsonstr= objmapper.writeValueAsString(menu);
    String strresult= mvc.perform(MockMvcRequestBuilders.post("/author/vialdmenu")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonstr))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
ObjectMapper desobj=new ObjectMapper();
desobj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
ResultInfo<String> result= desobj.readValue(strresult, ResultInfo.class);
    Assert.assertFalse("菜单已被占用",result.getIssucess()==false);
}
//    @Test
//    public void TestQueryUser() throws Exception {

//               String result = mvc.perform(MockMvcRequestBuilders.get("/author/getuser?uid=1")
//                      .contentType(MediaType.APPLICATION_JSON_VALUE))
//                       .andExpect(status().isOk())
////                        .andExpect((ResultMatcher) jsonPath("$.nickName").value("first data"))
//                       .andReturn().getResponse().getContentAsString();
//                    MyUser user = (MyUser) new ObjectMapper().readValue(result, MyUser.class);

//       Assert.assertTrue("Error message", user!=null);
      //  System.out.println(result);
//    }

    /*
    @Test
    public void whenGetInfoSuccess()throws Exception{
        String result =  mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
    @Test
    public void whenPostSuccess() throws Exception{
        Date date = new Date();
        String content = "{\"username\":\"ff\",\"password\":null,\"birthday\":"+date.getTime()+"}";
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenUploadSuccess() throws Exception{
        String result = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
                .file(new MockMultipartFile("file","test.txt","multipart/form-data","hello upload".getBytes("UTF-8"))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
*/
}
