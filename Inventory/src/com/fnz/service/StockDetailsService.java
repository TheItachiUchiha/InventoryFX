package com.fnz.service;

import javafx.collections.ObservableList;

import com.fnz.VO.ItemVO;
import com.fnz.dao.StockDetailsDAO;

public class StockDetailsService 
{
	StockDetailsDAO stockDetailsDAO = null;
	
	public StockDetailsService()
	{
		stockDetailsDAO = new StockDetailsDAO();
	}
	public ObservableList<ItemVO> viewStock(String categoryId) throws Exception 
	{
		try
		{
			return stockDetailsDAO.viewStock(categoryId);
		}
		catch (Exception e) 
		{
			throw e;
		}
	}
}
