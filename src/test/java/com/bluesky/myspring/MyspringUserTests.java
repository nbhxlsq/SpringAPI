package com.bluesky.myspring;

import com.bluesky.myspring.controller.CommonController;
import com.bluesky.myspring.entity.MyUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes =MyspringApplication.class, webEnvironment =SpringBootTest.WebEnvironment.MOCK )
@WebAppConfiguration
public class MyspringUserTests {
    @Autowired
    CommonController usercontroller;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup( usercontroller).build();
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


}

