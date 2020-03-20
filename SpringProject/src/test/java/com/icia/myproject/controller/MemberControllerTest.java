package com.icia.myproject.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations =
{"file:src/main/webapp/WEB-INF/spring/**/*.xml"}
		)


@Slf4j
@WebAppConfiguration
public class MemberControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mock;

	@Before
	public void setup() {
		System.out.println("setup");
		this.mock =MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void memberJoin() throws Exception{
		System.out.println("회원가입");
		log.info(mock.perform(MockMvcRequestBuilders.post("/Login")
				
				.param("id", "qwerty123258")
				.param("pw", "qwerty123")
				
				).andReturn().toString());
	}

}
