package com.fnz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.sqlite.SQLiteConfig;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;


import com.fnz.common.CommonConstants;
import com.fnz.common.SQLConstants;

public class UtiliesDAO 
{
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
	
	public void addItem(String itemName, String categoryId, Integer qDP, Integer qMRP, Integer qHP, Integer pDP, Integer pMRP, Integer pHP, Integer nDP, Integer nMRP, Integer nHP) throws Exception 
	{
		SQLiteConfig config = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet resultSet = null;
		Integer latestRow = 0;
		
		String newItemId = CommonConstants.ITEM_ID;
		String isQuad = "N";
		String isPint = "N";
		String isNip = "N";
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
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
			pstmt.setInt(5, qDP);
			pstmt.setInt(6, qMRP);
			pstmt.setInt(7, qHP);
			pstmt.setInt(5, pDP);
			pstmt.setInt(6, pMRP);
			pstmt.setInt(7, pHP);
			pstmt.setInt(5, nDP);
			pstmt.setInt(6, nMRP);
			pstmt.setInt(7, nHP);
			
			
			
			
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
	public ObservableMap<String,String> fetchCategoryDetails() throws Exception
	{
		SQLiteConfig config = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		ObservableMap<String,String> categoryMap = FXCollections.observableHashMap();
		
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			pstmt = conn.prepareStatement(SQLConstants.FETCH_CATEGORY);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next())
			{
				categoryMap.put(resultSet.getString(2), resultSet.getString(1));
			}
		}
		catch(Exception e)
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
		return categoryMap;	
	}
	
	public ObservableList<String> fetchCategory() throws Exception
	{
		SQLiteConfig config = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		ObservableList<String> categoryList = FXCollections.observableArrayList();
		
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			pstmt = conn.prepareStatement(SQLConstants.FETCH_CATEGORY);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next())
			{
				categoryList.add(resultSet.getString(2));
			}
		}
		catch(Exception e)
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
		return categoryList;	
	}
	
	
	public ObservableMap<String,String> fetchItemDetails() throws Exception
	{
		SQLiteConfig config = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		ObservableMap<String,String> itemMap = FXCollections.observableHashMap();
		
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			pstmt = conn.prepareStatement(SQLConstants.FETCH_ITEMS);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next())
			{
				itemMap.put(resultSet.getString(2), resultSet.getString(1));
			}
		}
		catch(Exception e)
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
		return itemMap;	
	}
	
	
	public ObservableList<String> fetchItem() throws Exception
	{
		SQLiteConfig config = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		ObservableList<String> itemList = FXCollections.observableArrayList();
		
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			pstmt = conn.prepareStatement(SQLConstants.FETCH_ITEMS);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next())
			{
				itemList.add(resultSet.getString(2));
			}
		}
		catch(Exception e)
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
		return itemList;	
	}
	
	
	public void deleteCategory(String categoryId) throws Exception
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
			pstmt = conn.prepareStatement(SQLConstants.DELETE_CATEGORY);
			
			pstmt.setString(1, categoryId);
			pstmt.execute();
		}
		catch(Exception e)
		{
			throw e;
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
	
	
	public void deleteItem(String itemId) throws Exception
	{
		SQLiteConfig config = null;
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);

			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			pstmt = conn.prepareStatement(SQLConstants.DELETE_ITEMS);
			
			pstmt.setString(1, itemId);
			pstmt.execute();
		}
		catch(Exception e)
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
