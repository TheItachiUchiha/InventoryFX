package com.fnz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.sqlite.SQLiteConfig;

import com.fnz.common.CommonConstants;
import com.fnz.common.SQLConstants;

public class OutgoingStockDAO
{

	public void updateStockDetails(Map<String,Integer> itemQuantityMap, List<String> itemIdList)  throws Exception 
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
			pstmt = conn.prepareStatement(SQLConstants.UPDATE_ITEM_QUANTITY);
			for(int i=0;i<itemIdList.size();i++)
			{
				pstmt.setInt(1, itemQuantityMap.get(itemIdList.get(i)));
				pstmt.setString(2, itemIdList.get(i));
				pstmt.executeUpdate();
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
	}
}
