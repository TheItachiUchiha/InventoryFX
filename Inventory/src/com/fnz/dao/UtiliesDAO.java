package com.fnz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import com.fnz.common.CommonConstants;
import com.fnz.common.SQLConstants;

public class UtiliesDAO 
{
	public void addCategory(String categoryName) throws Exception 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet resultSet = null;
		Integer latestRow = 0;
		
		String newCategoryId = CommonConstants.CATEGORY_ID;
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			conn = DriverManager.getConnection(sDbUrl);
			pstmt = conn.prepareStatement(SQLConstants.INSERT_CATEGORY);
			
			pstmt1 = conn.prepareStatement(SQLConstants.FETCH_LATEST_CATEGORY);
			
			
			resultSet = pstmt1.executeQuery();
			
			resultSet.next();
			
			latestRow = resultSet.getInt(1)+1;
			
			if(latestRow <10)
			{
				newCategoryId = newCategoryId + "00" + latestRow.toString();
			}
			else if(latestRow >=10 && latestRow <100)
			{
				newCategoryId = newCategoryId + "0" + latestRow.toString();
			}
			else
			{
				newCategoryId = newCategoryId + latestRow.toString();
			}
			
			pstmt.setQueryTimeout(CommonConstants.TIMEOUT);
			
			
			pstmt.setString(1, newCategoryId);
			pstmt.setString(2, categoryName);
			
			pstmt.execute();
			
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
			if(pstmt1 != null )
			{
				pstmt1.close();
			}
			if(resultSet != null)
			{
				resultSet.close();
			}
		}
	}
	
	public void addItem(String itemName, String categoryId) throws Exception 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet resultSet = null;
		Integer latestRow = 0;
		
		String newItemId = CommonConstants.CATEGORY_ID;
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			conn = DriverManager.getConnection(sDbUrl);
			pstmt = conn.prepareStatement(SQLConstants.INSERT_ITEM);
			
			pstmt1 = conn.prepareStatement(SQLConstants.FETCH_LATEST_ITEM);
			
			
			resultSet = pstmt1.executeQuery();
			
			resultSet.next();
			
			latestRow = resultSet.getInt(1)+1;
			
			if(latestRow <10)
			{
				newItemId = newItemId + "00" + latestRow.toString();
			}
			else if(latestRow >=10 && latestRow <100)
			{
				newItemId = newItemId + "0" + latestRow.toString();
			}
			else
			{
				newItemId = newItemId + latestRow.toString();
			}
			
			pstmt.setQueryTimeout(CommonConstants.TIMEOUT);
			
			
			pstmt.setString(1, newItemId);
			pstmt.setString(2, itemName);
			pstmt.setString(3, categoryId);
			pstmt.setInt(4, CommonConstants.ZERO);
			pstmt.execute();
			
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
			if(pstmt1 != null )
			{
				pstmt1.close();
			}
			if(resultSet != null)
			{
				resultSet.close();
			}
		}
	}
}
