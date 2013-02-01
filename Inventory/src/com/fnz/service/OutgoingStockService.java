package com.fnz.service;

import javafx.collections.ObservableList;

import com.fnz.VO.ItemVO;
import com.fnz.VO.StockVO;
import com.fnz.dao.OutgoingStockDAO;

public class OutgoingStockService 
{
		OutgoingStockDAO outgoingStockDAO;
		public OutgoingStockService()
		{
			outgoingStockDAO = new OutgoingStockDAO();
		}
		public void deleteOutgoingStock(String invoiceNo, String date, ObservableList<ItemVO> listData) throws Exception 
		{
			outgoingStockDAO.deleteOutgoingStock(date, listData);
		}
		public ObservableList<StockVO> fetchOutgoingStockDetails(String initialDate, String finalDate) throws Exception 
		{
			return outgoingStockDAO.fetchOutgoingStockDetails(initialDate, finalDate);
		}
}
