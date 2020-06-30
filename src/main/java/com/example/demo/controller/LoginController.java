package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {


  @RequestMapping(value = "/login", method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("isError",false);
	    return "login/loginForm";
	}
	
	@RequestMapping(value = "/login-error", method = RequestMethod.GET)
	public String loginError(Model model) {

		 model.addAttribute("isError",true);
		 return "login/loginForm";
  }
  
  @RequestMapping(value ="/success", method = RequestMethod.POST)
    public String loginsuccess(){
        
        return "redirect:/inquiry/list";
    }
  
  @RequestMapping(value ="/logout", method = RequestMethod.POST)
  	public String logout(Model model) {
	  model.addAttribute("isError",false);
	  return "redirect:/iquiry/form";
  }
  
}