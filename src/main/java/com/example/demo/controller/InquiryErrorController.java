package com.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InquiryErrorController implements ErrorController{
	
	
	@RequestMapping("error")
	public String handleError() {
		return "error/404";
	}
	
	
	@Override
	public String getErrorPath() {
        return "/error";
	}

	
}
