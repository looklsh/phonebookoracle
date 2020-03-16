package com.example.phonebook.dao;

import java.util.List;

import com.example.phonebook.vo.PhoneBookVO;



public interface PhoneBookDAO {
	
	public List<PhoneBookVO> getList();
	
	public PhoneBookVO get(Long id);
	
	public List<PhoneBookVO> search(String name);
	
	public boolean insert(PhoneBookVO phonebookVO);
	
	public boolean update(PhoneBookVO phonebookVO);
	
	public boolean delete(Long id);
	
}//interface

