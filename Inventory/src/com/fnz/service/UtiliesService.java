package com.fnz.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import com.fnz.VO.CategoryTypeVO;
import com.fnz.VO.CategoryVO;
import com.fnz.VO.ItemTypeVO;
import com.fnz.VO.ItemVO;
import com.fnz.dao.UtiliesDAO;

public class UtiliesService 
{
	UtiliesDAO utiliesDAO;
	public UtiliesService()
	{
		utiliesDAO = UtiliesDAO.getUtiliesDAO();
	}
	
	
	public void addCategory(ObservableList<String> categoryList) throws Exception 
	{
		utiliesDAO.addCategory(categoryList);
	}
	
	public void addItem(ItemVO itemVO) throws Exception
	{
		try {
			utiliesDAO.addItem(itemVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
	}
	
	public ObservableMap<String,String> fetchCategoryDetails() throws Exception
	{
		return utiliesDAO.fetchCategoryDetails();
	}
	public ObservableList<CategoryVO> fetchCategory() throws Exception
	{
		ObservableList<CategoryVO> list = FXCollections.observableArrayList();
		list = utiliesDAO.fetchCategory();
		return list;
	}
	
	public ObservableList<ItemVO> fetchItemDetails() throws Exception
	{
		return utiliesDAO.fetchItemDetails();
	}
	
	public ObservableList<String> fetchItem() throws Exception
	{
		return utiliesDAO.fetchItem();
	}
	
	public void deleteItem(String itemId) throws Exception
	{
		utiliesDAO.deleteItem(itemId);
	}
	public void deleteCategory(String categoryId) throws Exception
	{
		try
		{
			utiliesDAO.deleteCategory(categoryId);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	public ObservableList<CategoryTypeVO> fetchTypes(String categoryId) throws Exception
	{
		return utiliesDAO.fetchTypes(categoryId);
	}
	public void addTypes(CategoryVO categoryVO,String typeName) throws Exception 
	{
		utiliesDAO.addTypes(categoryVO, typeName);
	}
	
	public void deleteCategoryTypes(CategoryTypeVO deleteCategoryTypes) throws Exception 
	{
		utiliesDAO.deleteCategoryTypes(deleteCategoryTypes);
	}
	public void editCategoryTypes(CategoryTypeVO editCategoryTypes, String newTypeName) throws Exception 
	{
		utiliesDAO.editCategoryTypes(editCategoryTypes, newTypeName);
	}
	public ObservableList<ItemVO> fetchItemsFromCategory(String categoryId) throws Exception
	{
		return utiliesDAO.fetchItemsFromCategory(categoryId);
	}
	public void addItemTypes(ItemTypeVO itemTypeVO) throws Exception 
	{
		utiliesDAO.addItemTypes(itemTypeVO);
	}
}
