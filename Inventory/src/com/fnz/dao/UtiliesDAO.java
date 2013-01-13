package com.fnz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.sqlite.SQLiteConfig;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;


import com.fnz.VO.CategoryVO;
import com.fnz.VO.ItemTypeVO;
import com.fnz.VO.ItemVO;
import com.fnz.common.CommonConstants;
import com.fnz.common.SQLConstants;

public class UtiliesDAO 
{
	
	private static UtiliesDAO utiliesDAO = null;
	public ObservableList<ItemVO> itemList;
	public ObservableList<CategoryVO> categoryList;

	private UtiliesDAO() 
	{
		itemList =FXCollections.observableArrayList();
		try 
		{
			itemList = fetchItemDetails();
			categoryList = fetchCategory();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static synchronized UtiliesDAO getUtiliesDAO() {
	    if (null == utiliesDAO) {
	    	utiliesDAO = new UtiliesDAO();
	    }
	    return utiliesDAO;
	}
	
	
	
	/**
	 * @return the itemList
	 */
	public ObservableList<ItemVO> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(ObservableList<ItemVO> itemList) {
		this.itemList = itemList;
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
	
	public void addItem(ItemVO itemVO) throws Exception 
	{
		SQLiteConfig config = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet resultSet = null;
		Integer latestRow = 0;
		
		String newItemId = CommonConstants.ITEM_ID;
		
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
			pstmt.setString(2, itemVO.getItemName());
			pstmt.setString(3, itemVO.getCategoryId());	
			
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
		for(ItemTypeVO itemTypeVO : itemVO.getListType())
		{
			addItemTypes(itemVO.getCategoryId(),newItemId, itemTypeVO);
		}
	}
	
	public void addItemTypes(String categoryId,String itemId, ItemTypeVO itemTypeVO) throws Exception 
	{
		SQLiteConfig config = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet resultSet = null;
		Integer latestRow = 0;
		
		String newTypeId = CommonConstants.TYPE_ID;
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			pstmt = conn.prepareStatement(SQLConstants.INSERT_ITEMS_TYPES);
			
			
			pstmt1 = conn.prepareStatement(SQLConstants.FETCH_LATEST_ITEMS_TYPE);
			
			
			resultSet = pstmt1.executeQuery();
			
			resultSet.next();
			
			latestRow = resultSet.getInt(1)+1;
			
			if(latestRow <10)
			{
				newTypeId = newTypeId + "00" + latestRow.toString();
			}
			else if(latestRow >=10 && latestRow <100)
			{
				newTypeId = newTypeId + "0" + latestRow.toString();
			}
			else
			{
				newTypeId = newTypeId + latestRow.toString();
			}
			
			
			pstmt.setQueryTimeout(CommonConstants.TIMEOUT);
			
			
			
			pstmt.setString(1, itemId);
			pstmt.setString(2, newTypeId);	
			pstmt.setInt(3, itemTypeVO.getQuantity());
			pstmt.setInt(4, itemTypeVO.getDp());
			pstmt.setInt(5, itemTypeVO.getMrp());
			pstmt.setInt(6, itemTypeVO.getHp());
			pstmt.executeUpdate();
			
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
	
	public ObservableList<CategoryVO> fetchCategory() throws Exception
	{
		SQLiteConfig config = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		ObservableList<CategoryVO> categoryList = FXCollections.observableArrayList();
		
		
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
				CategoryVO categoryVO = new CategoryVO();
				categoryVO.setCategotyId(resultSet.getString(1));
				categoryVO.setCategoryName(resultSet.getString(2));
				categoryList.add(categoryVO);
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
	
	
	public ObservableList<ItemVO> fetchItemDetails() throws Exception
	{
		SQLiteConfig config = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		ObservableList<ItemVO> itemDetailsList = FXCollections.observableArrayList();
		ItemVO itemVO = new ItemVO();
		
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
				itemVO = new ItemVO();
				itemVO.setItemId(resultSet.getString(1));
				itemVO.setItemName(resultSet.getString(2));
				itemVO.setCategoryId(resultSet.getString(3));
				itemDetailsList.add(itemVO);
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
		try
		{
			itemDetailsList = addTypesToList(itemDetailsList);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return itemDetailsList;	
	}
	
	public ObservableList<ItemVO> addTypesToList(ObservableList<ItemVO> itemList) throws Exception
	{
		SQLiteConfig config = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		ItemTypeVO itemTypeVO = new ItemTypeVO();
		ObservableList<ItemTypeVO> typeList = FXCollections.observableArrayList();
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			
			for(ItemVO itemVO : itemList)
			{
				pstmt = conn.prepareStatement(SQLConstants.FETCH_ITEMS_TYPES);
				pstmt.setString(1, itemVO.getCategoryId());
				resultSet = pstmt.executeQuery();
				typeList = FXCollections.observableArrayList();
				while(resultSet.next())
				{
					itemTypeVO = new ItemTypeVO();
					itemTypeVO.setTypeId(resultSet.getString(1));
					itemTypeVO.setType(resultSet.getString(2));
					itemTypeVO.setQuantity(resultSet.getInt(3));
					itemTypeVO.setDp(resultSet.getInt(4));
					itemTypeVO.setMrp(resultSet.getInt(5));
					itemTypeVO.setHp(resultSet.getInt(6));
					itemTypeVO.setCategoryId(itemVO.getCategoryId());
					typeList.add(itemTypeVO);
				}
				itemVO.setListType(typeList);
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
	
	public ObservableList<String> fetchTypes() throws Exception
	{
		SQLiteConfig config = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		ObservableList<String> typeList = FXCollections.observableArrayList();
		
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			pstmt = conn.prepareStatement(SQLConstants.FETCH_TYPE);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next())
			{
				typeList.add(resultSet.getString(1));
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
		return typeList;	
	}
	public void addTypes(CategoryVO categoryVO,String typeName) throws Exception 
	{
		SQLiteConfig config = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet resultSet = null;
		Integer latestRow = 0;
		
		String newTypeId = CommonConstants.TYPE_ID;
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			pstmt = conn.prepareStatement(SQLConstants.INSERT_CATEGORY_TYPES);
			
			
			pstmt1 = conn.prepareStatement(SQLConstants.FETCH_LATEST_CATEGORY_TYPE);
			
			
			resultSet = pstmt1.executeQuery();
			
			resultSet.next();
			
			latestRow = resultSet.getInt(1)+1;
			
			if(latestRow <10)
			{
				newTypeId = newTypeId + "00" + latestRow.toString();
			}
			else if(latestRow >=10 && latestRow <100)
			{
				newTypeId = newTypeId + "0" + latestRow.toString();
			}
			else
			{
				newTypeId = newTypeId + latestRow.toString();
			}
			
			
			pstmt.setQueryTimeout(CommonConstants.TIMEOUT);
			
			
			pstmt.setString(1, newTypeId);	
			pstmt.setString(2, typeName);
			pstmt.setString(3, categoryVO.getCategotyId());
			
			pstmt.executeUpdate();
			
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
