package com.fnz.service;

import com.fnz.VO.IncomingStockVO;
import com.fnz.VO.ItemVO;
import com.fnz.dao.IncomingStockDAO;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;

public class IncomingStockService
{
	IncomingStockDAO incomingStockDAO;
	public IncomingStockService()
	{
		incomingStockDAO = new IncomingStockDAO();
	}

	public void addIncomingStock(String invoiceNo, String date, ObservableList<ItemVO> listData) throws Exception 
	{
		incomingStockDAO.addIncomingStock(invoiceNo, date, listData);
	}
	public ObservableList<IncomingStockVO> fetchIncomingStockDetails(String categoryId) throws Exception 
	{
		return incomingStockDAO.fetchIncomingStockDetails(categoryId);
	}
}
