package com.fnz.service;


import com.fnz.VO.ItemVO;
import com.fnz.VO.StockVO;
import com.fnz.dao.IncomingStockDAO;

import javafx.collections.ObservableList;


public class IncomingStockService
{
	IncomingStockDAO incomingStockDAO;
	public IncomingStockService()
	{
		incomingStockDAO = new IncomingStockDAO();
	}

	public String addIncomingStock(String invoiceNo, String date, ObservableList<ItemVO> listData) throws Exception 
	{
		return incomingStockDAO.addIncomingStock(invoiceNo, date, listData);
	}
	public ObservableList<StockVO> fetchIncomingStockDetails(String initialDate, String finalDate) throws Exception 
	{
		return incomingStockDAO.fetchIncomingStockDetails(initialDate, finalDate);
	}
}
