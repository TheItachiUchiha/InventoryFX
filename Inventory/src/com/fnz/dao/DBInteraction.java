package com.fnz.dao;



import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.sqlite.SQLiteConfig;

import com.fnz.common.CommonConstants;
import com.fnz.common.SQLConstants;

public class DBInteraction 
{
	public void createDB() throws Exception
	{
		Properties connectionProperties = null;
		SQLiteConfig config = null;
		
		Connection conn = null;
		Statement stmt = null;
		
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		
		
		//create Folder to store DB
		File dir=new File("/C:/"+"/Inventory");
		if(!dir.exists())
		{
			dir.mkdir();
		}
		
		//Creating tables if they don't exist
		
		try 
		{
			
			connectionProperties = new Properties();
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			connectionProperties = config.toProperties();
			conn = DriverManager.getConnection(sDbUrl,connectionProperties);
			stmt = conn.createStatement();
			
			stmt.setQueryTimeout(CommonConstants.TIMEOUT);
			stmt.execute(SQLConstants.CREATE_CATEGORY_TABLE);
			stmt.executeUpdate( SQLConstants.CREATE_ITEM_TABLE );
			stmt.executeUpdate(SQLConstants.CREATE_ITEM_TYPE_TABLE);
			stmt.executeUpdate( SQLConstants.CREATE_INCOMING_STOCK );
			stmt.executeUpdate(SQLConstants.CREATE_INCOMING_STOCK_DETAILS);
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
			if(stmt != null )
			{
				stmt.close();
			}
		
		}
	}
}
