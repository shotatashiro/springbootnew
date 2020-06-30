package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.InquiryDao;
import com.example.demo.entity.Inquiry;


@Service
public class InquiryServiceImpl implements InquiryService {
	
	private InquiryDao dao;
	
	
	@Autowired
	public InquiryServiceImpl(InquiryDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Inquiry> findAll(){
		return dao.findAll();
	}

	@Override
	public void save(Inquiry inquiry) {
		dao.save(inquiry);
	}
	
	@Override
	public Inquiry findById(Long id) {
		return dao.findById(id).get();
	}
	
	@Override
	public void deleteById(Long id) {
		dao.deleteById(id);
	}
	
	
//	@Override
//	public List<Inquiry> findByNameAndPassword(String name,String password) {
//		return dao.findByNameAndPassword(name,password);
//	}
	
	@Override
	public List<Inquiry> findByNameAndPass(String name,String pass) {
		return dao.findByNameAndPass(name,pass);
	}
	
	
	@Override
	public List<Inquiry> findByName(String name){
		return dao.findByName(name);
	}
	
//	@Override
//	public List<Inquiry> findBySearch(String name, String password){
//		return dao.findBySearch(name, password);
//	}
	
//	public void scan() {
//
//		  // 検索条件をエンティティにセット
//		  Inquiry inquiry = new Inquiry();
//		  inquiry.setName("taro");
//		  inquiry.setStandardType(StandardType.D);
//
//		  // 検索条件からExampleインスタンスを生成
//		  Example<Item> example = Example.of(probe);
//
//		  List<Item> items = repository.findAll(example);
//		  items.forEach(System.out::println);
//		}
}
