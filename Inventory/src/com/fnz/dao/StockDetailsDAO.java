package com.fnz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.sqlite.SQLiteConfig;

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
		ItemVO itemVO = new ItemVO();
		ObservableList<ItemVO> listStock = FXCollections.observableArrayList();
		
		try 
		{
			
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			pstmt = conn.prepareStatement(SQLConstants.FETCH_ITEM_QUANTITY);
			pstmt.setString(1, categoryId);
			resultSet = pstmt.executeQuery();
			while(resultSet.next())
			{
				itemVO = new ItemVO();
				itemVO.setItemId(resultSet.getString(1));
				itemVO.setItemName(resultSet.getString(2));
				itemVO.setCategoryId(resultSet.getString(3));
				listStock.add(itemVO);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
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
		return listStock;
	}

}
