package com.fnz.common;

public class SQLConstants
{
	
	public static final String CREATE_CATEGORY_TABLE = "CREATE TABLE if not exists CATEGORY_TABLE (CATEGORY_ID text PRIMARY KEY, CATEGORY_NAME TEXT UNIQUE NOT NULL)";
	
	public static final String CREATE_ITEM_TABLE = "CREATE TABLE if not exists ITEMS_TABLE (ITEM_ID text PRIMARY KEY NOT NULL,ITEM_NAME text,CATEGORY_ID TEXT,QUANTITY INTEGER,"+
			"FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORY_TABLE(CATEGORY_ID))";
	
	public static final String CREATE_INCOMING_STOCK = "CREATE TABLE if not exists INCOMING_STOCK (INVOICE_ID TEXT PRIMARY KEY, DATE_OF_DELIVERY text,"+
	"SUPPLIER TEXT)";
			
	
	public static final String CREATE_INCOMING_STOCK_DETAILS = "CREATE TABLE if not exists INCOMING_STOCK_DETAILS (INVOICE_ID TEXT PRIMARY KEY, ITEM_ID text, QUANTITY INTEGER," +
			"FOREIGN KEY(ITEM_ID) REFERENCES ITEMS_TABLE(ITEM_ID))";
	
	
	public static final String INSERT_CATEGORY = "INSERT INTO CATEGORY_TABLE values (?,?)";
	
	public static final String INSERT_ITEM = "INSERT INTO ITEMS_TABLE values (?,?,?,?)";

	public static final String INSERT_INCOMING_STOCK = "INSERT INTO INCOMING_STOCK values (?,?,?)";
	
	public static final String INSERT_INCOMING_STOCK_DETAILS = "INSERT INTO INCOMING_STOCK_DETAILS values (?,?)"; 
	
	public static final String FETCH_LATEST_CATEGORY = "SELECT max(rowid) as row from CATEGORY_TABLE";
	
	public static final String FETCH_LATEST_ITEM = "SELECT max(rowid) as row from ITEMS_TABLE";
	
	public static final String FETCH_ITEM_QUANTITY = "SELECT ITEM_ID, ITEM_NAME, QUANTITY from ITEMS_TABLE where CATEGORY_ID =? ";
	
	public static final String UPDATE_ITEM_QUANTITY = "UPDATE ITEMS_TABLE SET QUANTITY = ? where ITEM_ID =? ";
	
	public static final String FETCH_CATEGORY = "SELECT CATEGORY_ID,CATEGORY_NAME FROM CATEGORY_TABLE";
	
	public static final String FETCH_ITEMS = "SELECT ITEM_ID,ITEM_NAME FROM ITEMS_TABLE";
	
	public static final String DELETE_ITEMS = "DELETE FROM ITEMS_TABLE where ITEM_ID = ?";
	
	public static final String DELETE_CATEGORY = "DELETE FROM CATEGORY_TABLE where CATEGORY_ID = ?";
	
}
