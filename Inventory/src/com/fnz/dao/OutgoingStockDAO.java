package com.fnz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import org.sqlite.SQLiteConfig;

import com.fnz.VO.ItemTypeVO;
import com.fnz.VO.ItemVO;
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
	
	public void deleteOutgoingStock(String invoiceNo, String date, ObservableList<ItemVO> listData) throws Exception 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
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
			pstmt = conn.prepareStatement(SQLConstants.INSERT_INCOMING_STOCK);
			statement = conn.createStatement();
			
			
			for(ItemVO itemVO : listData)
			{
				ObservableMap<String, ItemTypeVO> map = FXCollections.observableHashMap();
				map=itemVO.getListType();
				Set<String> keySet = map.keySet();
				for(Iterator<String> iter=keySet.iterator();iter.hasNext();)
				{
					ItemTypeVO itemTypeVO = new ItemTypeVO();
					itemTypeVO = map.get(iter.next());
					statement.addBatch(SQLConstants.UPDATE_DEL_ITEMS_TYPES_1 + itemTypeVO.getQuantity() + SQLConstants.UPDATE_DEL_ITEMS_TYPES_2 +
							itemVO.getItemId() + SQLConstants.UPDATE_DEL_ITEMS_TYPES_3 + itemTypeVO.getTypeId() + SQLConstants.UPDATE_DEL_ITEMS_TYPES_4);
				}
			}
			
			statement.executeBatch();
			conn.commit();
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
