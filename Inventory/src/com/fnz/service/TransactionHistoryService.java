package com.fnz.service;

import javafx.collections.ObservableList;

import com.fnz.VO.StockVO;
import com.fnz.dao.TransactionHistoryDAO;

public class TransactionHistoryService {
	TransactionHistoryDAO transactionHistoryDAO;
	
	public TransactionHistoryService()
	{
		transactionHistoryDAO = new TransactionHistoryDAO();
	}
	
	public Boolean deletePurchaseFromDate(ObservableList<StockVO> itemList) throws ClassNotFoundException
	{
		return transactionHistoryDAO.DeletePurchaseFromDate(itemList);
	}
}
