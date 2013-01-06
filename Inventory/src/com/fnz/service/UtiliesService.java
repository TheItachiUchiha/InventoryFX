package com.fnz.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.fnz.dao.UtiliesDAO;

public class UtiliesService 
{
	UtiliesDAO utiliesDAO;
	public UtiliesService()
	{
		utiliesDAO = new UtiliesDAO();
	}
	
	
	public void addCategory(String categoryName) throws Exception 
	{
		utiliesDAO.addCategory(categoryName);
	}
	public void addItem(String itemName, String categoryId) throws Exception 
	{
		utiliesDAO.addItem(itemName, categoryId);
	}
	
	public ObservableList<String> fetchCategory() throws Exception
	{
		ObservableList<String> list = FXCollections.observableArrayList();
		list = utiliesDAO.fetchCategory();
		return list;
	}
}
