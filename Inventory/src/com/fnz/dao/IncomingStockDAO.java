package com.fnz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.sqlite.SQLiteConfig;

import com.fnz.common.CommonConstants;
import com.fnz.common.SQLConstants;

public class IncomingStockDAO 
{
	public void addIncomingStock(String invoiceNo, String date, String supplier) throws Exception 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		SQLiteConfig config = null;
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			pstmt = conn.prepareStatement(SQLConstants.INSERT_INCOMING_STOCK);
			
			
			pstmt.setString(1, invoiceNo);
			pstmt.setString(2, date);
			pstmt.setString(3, supplier);
			
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
			if(resultSet != null)
			{
				resultSet.close();
			}
		}
	}
	
	public void addIncomingStockDetails(String invoiceNo, String itemId, Integer quantity) throws Exception 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		SQLiteConfig config = null;
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			pstmt = conn.prepareStatement(SQLConstants.INSERT_INCOMING_STOCK_DETAILS);
			
			
			pstmt.setString(1, invoiceNo);
			pstmt.setString(2, itemId);
			pstmt.setInt(3, quantity);
			
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
			if(resultSet != null)
			{
				resultSet.close();
			}
		}
	}

}
