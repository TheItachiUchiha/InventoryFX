package com.fnz.dao;



import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
		//File dir=new File("/C:/"+"/Inventory");
		
		File dir=new File("Data");
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
			stmt.execute(SQLConstants.CREATE_CATEGORY_TYPES);
			stmt.executeUpdate( SQLConstants.CREATE_ITEM_TABLE );
			stmt.executeUpdate(SQLConstants.CREATE_ITEM_TYPE_TABLE);
			stmt.executeUpdate( SQLConstants.CREATE_INCOMING_STOCK );
			stmt.executeUpdate(SQLConstants.CREATE_INCOMING_STOCK_DETAILS);
			stmt.executeUpdate(SQLConstants.CREATE_OUTGOING_STOCK_DETAILS);
			stmt.execute(SQLConstants.INDEX_CATEGORY_TYPES);
			stmt.execute(SQLConstants.INDEX_INCOMING_STOCK);
			stmt.execute(SQLConstants.INDEX_ITEM_TABLE);
			stmt.execute(SQLConstants.INDEX_OUTGOING_STOCK_DETAILS);
			
			ObservableList<String> newCategoryList =FXCollections.observableArrayList();
	    	newCategoryList.addAll(CommonConstants.CATEGORY_PREMIUM_WHISKY,CommonConstants.CATEGORY_REGULAR_WHISKY,CommonConstants.CATEGORY_PREMIUM_VODKA,CommonConstants.CATEGORY_REGULAR_VODKA,
	    			CommonConstants.CATEGORY_BRANDY,CommonConstants.CATEGORY_GIN,CommonConstants.CATEGORY_PREMIUM_RUM,
	    			CommonConstants.CATEGORY_REGULAR_RUM,CommonConstants.CATEGORY_BEER,CommonConstants.CATEGORY_WINE,
	    			CommonConstants.CATEGORY_PREMIUM_SCOTCH,CommonConstants.CATEGORY_REGULAR_SCOTCH,CommonConstants.CATEGORY_BEVRAGES);
	    	
	        addCategory(newCategoryList);
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
	
	public void addCategory(ObservableList<String> categoryList) throws Exception 
	{
		SQLiteConfig config = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet resultSet = null;
		Integer latestRow = 0;
		String query="";
		
		String newCategoryId = CommonConstants.CATEGORY_ID;
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			
			
			pstmt1 = conn.prepareStatement(SQLConstants.FETCH_LATEST_CATEGORY);
			
			resultSet = pstmt1.executeQuery();
			
			resultSet.next();
			
			latestRow = resultSet.getInt(1)+1;
			
			
			
			
			for(int i = 0;i<categoryList.size();i++)
			{
				newCategoryId = CommonConstants.CATEGORY_ID;
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
				latestRow++;
				
				query = query + " SELECT '"  + newCategoryId + "'"+CommonConstants.COMMA +"'"+ categoryList.get(i) + "' UNION ALL";
				
			}
			
			query = query.substring(0, query.length()-9);
			query = query + ";";
			
			pstmt = conn.prepareStatement(SQLConstants.INSERT_CATEGORY_1+query);
			
			
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
