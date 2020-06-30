package com.example.demo.dao;

import java.io.Serializable;
import java.util.List;

//import com.example.demo.entity.Inquiry;

public interface MyDataDao<T> extends Serializable {
	public List<T> getAll();
	
	public List<T> findByNama(String name);
	public List<T> find(String fstr);
}
