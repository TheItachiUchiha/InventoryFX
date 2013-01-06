package com.fnz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.fnz.common.CommonConstants;
import com.fnz.common.SQLConstants;

public class StockDetailsDAO 
{
	public Map<String,Integer> viewStock(String categoryId) throws Exception 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		Map<String,Integer> itemQuantity = new HashMap<String,Integer>();
		String itemName ="";
		Integer quantity = 0;
		
		try 
		{
			conn = DriverManager.getConnection(sDbUrl);
			pstmt = conn.prepareStatement(SQLConstants.FETCH_ITEM_QUANTITY);
			pstmt.setString(1, categoryId);
			resultSet = pstmt.executeQuery();
			while(resultSet.next())
			{
				itemName = resultSet.getString(2);
				quantity = resultSet.getInt(3);
				itemQuantity.put(itemName, quantity);
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
		return itemQuantity;
	}

}
