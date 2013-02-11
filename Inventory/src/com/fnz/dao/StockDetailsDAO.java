package com.fnz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import org.sqlite.SQLiteConfig;

import com.fnz.VO.CategoryTypeVO;
import com.fnz.VO.ItemTypeVO;
import com.fnz.VO.ItemVO;
import com.fnz.common.CommonConstants;
import com.fnz.common.SQLConstants;

public class StockDetailsDAO 
{
	public ObservableList<ItemVO> viewStock(String categoryId) throws Exception 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		SQLiteConfig config = null;
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		//ItemVO itemVO = new ItemVO();
		String temp = "(";
		String query;
		ObservableList<ItemVO> listItemVOs = FXCollections.observableArrayList();
		ObservableList<CategoryTypeVO> types = FXCollections.observableArrayList();
		ObservableMap<String,ItemTypeVO> map = FXCollections.observableHashMap();
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
		
			types = UtiliesDAO.getUtiliesDAO().fetchTypes(categoryId);
			listItemVOs = UtiliesDAO.getUtiliesDAO().fetchItemsFromCategory(categoryId);
			
			for(ItemVO itemVO : listItemVOs)
			{
				map = FXCollections.observableHashMap();
				temp = "(";
				for(CategoryTypeVO categoryTypeVO : types)
				{
					temp = temp + "'"+categoryTypeVO.getTypeId() + "',";
				}
				temp = temp.substring(0, temp.length()-1);
				temp = temp + ")";
				query = SQLConstants.FETCH_ITEMS_TYPES + " '" + itemVO.getItemId()+"' "+SQLConstants.FETCH_ITEMS_TYPES_2 + temp;
				pstmt = conn.prepareStatement(query);
				resultSet = pstmt.executeQuery();
				while(resultSet.next())
				{
					ItemTypeVO itemTypeVO = new ItemTypeVO();
					itemTypeVO.setItemId(itemVO.getItemId());
					itemTypeVO.setTypeId(resultSet.getString(1));
					itemTypeVO.setQuantity(resultSet.getInt(2));
					itemTypeVO.setDp(resultSet.getInt(3));
					itemTypeVO.setMrp(resultSet.getInt(4));
					itemTypeVO.setHp(resultSet.getInt(5));
					map.put(itemTypeVO.getTypeId(),itemTypeVO);
				}
				itemVO.setListType(map);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally
		{
			if(conn !=null )
			{
				conn.close();
			}
			if(pstmt != null )
			{
				pstmt.close();
			}
			if(resultSet != null)
			{
				resultSet.close();
			}
		}
			
		return listItemVOs;
	}

}
