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


import com.fnz.VO.CategoryTypeVO;
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
			//itemList = fetchItemDetails();
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
	}
	
	public void addItemTypes(ItemTypeVO itemTypeVO) throws Exception 
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
			//pstmt = conn.prepareStatement(SQLConstants.CHECK_ITEMS_TYPES);
			
	
			pstmt = conn.prepareStatement(SQLConstants.INSERT_ITEMS_TYPES);
			
			
			pstmt.setString(1, itemTypeVO.getItemId());
			pstmt.setString(2, itemTypeVO.getTypeId());	
			pstmt.setInt(3, itemTypeVO.getQuantity());
			pstmt.setInt(4, itemTypeVO.getDp());
			pstmt.setInt(5, itemTypeVO.getMrp());
			pstmt.setInt(6, itemTypeVO.getHp());
			pstmt.execute();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
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
	
	public ItemTypeVO fetchItemtypeDetails(String itemId, String typeId) throws Exception 
	{
		SQLiteConfig config = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		ItemTypeVO itemTypeVO = null;
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			pstmt = conn.prepareStatement(SQLConstants.CHECK_ITEMS_TYPES);
			
			pstmt.setString(1, itemId);
			pstmt.setString(2, typeId);
	
			resultSet = pstmt.executeQuery();
			
			if(resultSet.next())
			{
				itemTypeVO = new ItemTypeVO();
				itemTypeVO.setItemId(itemId);
				itemTypeVO.setTypeId(typeId);
				itemTypeVO.setMrp(resultSet.getInt("MRP"));
				itemTypeVO.setDp(resultSet.getInt("D_PRICE"));
				itemTypeVO.setHp(resultSet.getInt("H_PRICE"));
				itemTypeVO.setQuantity(resultSet.getInt("QUANTITY"));
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
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
		return itemTypeVO;
	}
	
	public void updateItemTypes(ItemTypeVO itemTypeVO) throws Exception 
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
			//pstmt = conn.prepareStatement(SQLConstants.CHECK_ITEMS_TYPES);
			
	
			pstmt = conn.prepareStatement(SQLConstants.UPDATE_ITEMS_TYPES);
			
			
			
			pstmt.setInt(1, itemTypeVO.getDp());
			pstmt.setInt(2, itemTypeVO.getMrp());
			pstmt.setInt(3, itemTypeVO.getHp());
			pstmt.setString(4, itemTypeVO.getItemId());
			pstmt.setString(5, itemTypeVO.getTypeId());	
			
			pstmt.execute();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
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
		ObservableMap<String,ItemTypeVO> typeMap = FXCollections.observableHashMap();
		
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
				typeMap = FXCollections.observableHashMap();
				while(resultSet.next())
				{
					itemTypeVO = new ItemTypeVO();
					itemTypeVO.setItemId(itemVO.getItemId());
					itemTypeVO.setTypeId(resultSet.getString(1));
					itemTypeVO.setQuantity(resultSet.getInt(2));
					itemTypeVO.setDp(resultSet.getInt(3));
					itemTypeVO.setMrp(resultSet.getInt(4));
					itemTypeVO.setHp(resultSet.getInt(5));
					typeMap.put(itemTypeVO.getTypeId(),itemTypeVO);
				}
				itemVO.setListType(typeMap);
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
	
	public ObservableList<CategoryTypeVO> fetchTypes(String categoryId) throws Exception
	{
		SQLiteConfig config = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		ObservableList<CategoryTypeVO> typeList = FXCollections.observableArrayList();
		
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			pstmt = conn.prepareStatement(SQLConstants.FETCH_TYPE_FROM_CATEGORY);
			pstmt.setString(1, categoryId);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next())
			{
				CategoryTypeVO categoryTypeVO = new CategoryTypeVO();
				categoryTypeVO.setTypeId(resultSet.getString(1));
				categoryTypeVO.setTypeName(resultSet.getString(2));
				categoryTypeVO.setCategoryId(categoryId);
				typeList.add(categoryTypeVO);
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
	
	public ObservableList<ItemVO> fetchItemsFromCategory(String categoryId) throws Exception
	{
		SQLiteConfig config = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		ObservableList<ItemVO> itemList = FXCollections.observableArrayList();
		ItemVO itemVO = new ItemVO();
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			pstmt = conn.prepareStatement(SQLConstants.FETCH_ITEM_FROM_CATEGORY);
			pstmt.setString(1, categoryId);
			resultSet = pstmt.executeQuery();
			while(resultSet.next())
			{
				itemVO = new ItemVO();
				itemVO.setItemId(resultSet.getString(1));
				itemVO.setItemName(resultSet.getString(2));
				itemVO.setCategoryId(categoryId);
				itemList.add(itemVO);
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
		return itemList;
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
	
	
	
	
	public void deleteCategoryTypes(CategoryTypeVO deleteCategoryTypes) throws Exception 
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
			pstmt = conn.prepareStatement(SQLConstants.DELETE_CATEGORY_TYPE);
			pstmt.setString(1, deleteCategoryTypes.getTypeId());
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
		
		public void editCategoryTypes(CategoryTypeVO editCategoryTypes, String newTypeName) throws Exception 
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
				pstmt = conn.prepareStatement(SQLConstants.EDIT_CATEGORY_TYPE);
				pstmt.setString(1, newTypeName);
				pstmt.setString(2, editCategoryTypes.getTypeId());
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
				if(resultSet != null)
				{
					resultSet.close();
				}
			}
	}
}
