package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Inquiry;

public interface InquiryService {

	
	public List<Inquiry> findAll();

	public void save(Inquiry inquiry);
	
	public Inquiry findById(Long id);
	
	public void deleteById(Long id);
	
//	public List<Inquiry> findByNameAndPassword(String name,String password);
	
	public List<Inquiry> findByNameAndPass(String name,String pass);
	
	public List<Inquiry> findByName(String name);
	
//	  public List<Inquiry> findBySearch(String name, String password);
	
	
	
	
	

}
