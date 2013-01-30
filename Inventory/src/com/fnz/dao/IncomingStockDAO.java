package com.fnz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import org.sqlite.SQLiteConfig;

import com.fnz.VO.IncomingStockVO;
import com.fnz.VO.ItemTypeVO;
import com.fnz.VO.ItemVO;
import com.fnz.common.CommonConstants;
import com.fnz.common.SQLConstants;

public class IncomingStockDAO 
{
	public void addIncomingStock(String invoiceNo, String date, ObservableList<ItemVO> listData) throws Exception 
	{
		Connection conn = null;
		ResultSet resultSet = null;
		SQLiteConfig config = null;
		java.sql.Statement statement = null;
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			statement = conn.createStatement();
			
			statement.addBatch(SQLConstants.INSERT_INCOMING_STOCK_1+invoiceNo+SQLConstants.INSERT_INCOMING_STOCK_2+date+SQLConstants.INSERT_INCOMING_STOCK_2
					+""+SQLConstants.INSERT_INCOMING_STOCK_3);
			for(ItemVO itemVO : listData)
			{
				ObservableMap<String, ItemTypeVO> map = FXCollections.observableHashMap();
				map=itemVO.getListType();
				Set<String> keySet = map.keySet();
				for(Iterator<String> iter=keySet.iterator();iter.hasNext();)
				{
					ItemTypeVO itemTypeVO = new ItemTypeVO();
					itemTypeVO = map.get(iter.next());
					statement.addBatch(SQLConstants.UPDATE_ADD_ITEMS_TYPES_1 + itemTypeVO.getQuantity()*CommonConstants.CASE_SIZE + SQLConstants.UPDATE_ADD_ITEMS_TYPES_2 +
							itemVO.getItemId() + SQLConstants.UPDATE_ADD_ITEMS_TYPES_3 + itemTypeVO.getTypeId() + SQLConstants.UPDATE_ADD_ITEMS_TYPES_4);
					statement.addBatch(SQLConstants.INSERT_INCOMING_STOCK_DETAILS_1+invoiceNo+SQLConstants.INSERT_INCOMING_STOCK_DETAILS_2+
							itemTypeVO.getItemId()+SQLConstants.INSERT_INCOMING_STOCK_DETAILS_2+itemTypeVO.getTypeId()+SQLConstants.INSERT_INCOMING_STOCK_DETAILS_3+
							itemTypeVO.getQuantity()+SQLConstants.INSERT_INCOMING_STOCK_DETAILS_4);
				}
			}
			
			statement.executeBatch();
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
			if(statement != null )
			{
				statement.close();
			}
			if(resultSet != null)
			{
				resultSet.close();
			}
		}
	}
	
	

	
	public ObservableList<IncomingStockVO> fetchIncomingStockDetails(String categoryId) throws Exception 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		SQLiteConfig config = null;
		ObservableList<IncomingStockVO> listIncoming = FXCollections.observableArrayList();
		
		
		Class.forName(CommonConstants.DRIVERNAME);
		
		String sDbUrl = CommonConstants.sJdbc + ":" + CommonConstants.DB_LOCATION + CommonConstants.sTempDb;
		
		try 
		{
			config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			conn = DriverManager.getConnection(sDbUrl, config.toProperties());
			pstmt = conn.prepareStatement(SQLConstants.FETCH_INCOMING_DETAILS);
			
			
			pstmt.setString(1, categoryId);
			
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next())
			{
				IncomingStockVO incomingStockVO = new IncomingStockVO();
				incomingStockVO.setInvoiceId(resultSet.getString(1));
				incomingStockVO.setDate(resultSet.getString(2));
				incomingStockVO.setItemName(resultSet.getString(3));
				incomingStockVO.setTypeName(resultSet.getString(4));
				incomingStockVO.setQuantity(resultSet.getInt(5));
				listIncoming.add(incomingStockVO);
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
		return listIncoming;
	}
}
