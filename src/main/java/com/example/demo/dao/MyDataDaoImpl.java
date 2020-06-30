package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Inquiry;

@Repository
public class MyDataDaoImpl implements MyDataDao<Inquiry> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	private static final Long serialVersionUID = 1L;
	
	private EntityManager entityManager;
	
	public MyDataDaoImpl() {
		super();
	}
	
	public MyDataDaoImpl(EntityManager manager) {
		this();
		entityManager = manager;
	}
	
	@Override
	public List<Inquiry> getAll(){
		Query query = entityManager.createQuery("from Inquiry");
		@SuppressWarnings("unchecked")
		List<Inquiry> list = query.getResultList();
		entityManager.close();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Inquiry> findByNama(String name){
		return (List<Inquiry>)entityManager.createQuery("from Inquiry where name =" + name).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Inquiry> find(String fstr){
		List<Inquiry> list = null;
		String qstr = "from Inquiry where name = :fname and pass = :fpass";
//		try {
//			
//		}
		
		Query query = entityManager.createQuery(qstr).setParameter("fname",fstr).setParameter("fpass", fstr);
		list = query.getResultList();
		return list;
	}

}
