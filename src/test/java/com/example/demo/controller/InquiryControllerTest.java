package com.example.demo.controller;

//import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.service.InquiryService;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;











@ExtendWith(SpringExtension.class)
class InquiryControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	InquiryService service;
	
	@InjectMocks
	InquiryController inquiryController;
	
	@BeforeEach
	public void set() {
		MockitoAnnotations.initMocks(this);
	    this.mockMvc = MockMvcBuilders.standaloneSetup(inquiryController).build();
	}
	
	

	@Test
	@DisplayName("新規問い合わせページにアクセス")
	void 新規問い合わせページにGETでアクセス() throws Exception{
		this.mockMvc.perform(get("/inquiry/form"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("headLine","入力してください"))
		.andExpect(view().name("form"));
	}
	
	@Test
	@DisplayName("確認画面から新規問い合わせページに戻る")
	void 新規問い合わせページにPOSTでアクセス() throws Exception{
		this.mockMvc.perform(post("/inquiry/form").param("name", "test").param("pass","pass").param("email", "email@example.com").param("content", "テスト投稿"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("headLine","入力してください"))
		.andExpect(view().name("form"));
	}
	
	@Test
	@DisplayName("確認ページにアクセス")
	void 入力確認ページにPOSTでアクセス() throws Exception{
		this.mockMvc.perform(post("/inquiry/confirm"))
		
	}
	
	

}
