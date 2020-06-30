package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Inquiry;

@Repository
public interface InquiryDao extends JpaRepository<Inquiry,Long>{
	
//	public List<Inquiry> findByNameAndPassword(String name,String password);
	
	public List<Inquiry> findByNameAndPass(String name,String pass);
	
	public List<Inquiry> findByName(String name);
	
//	@Query(value = "select * from inquiry where name = :name && password = :password")              // (3)
//	List<Inquiry> findBySearch(@Param("name") String name,@Param("password") String password);
	
	
//	@Query("SELECT * FROM Inquiry i Where name = i.name && password = i.password")
//    List<Inquiry> findAllOrderById();
	
	

}
