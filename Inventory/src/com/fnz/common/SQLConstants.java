package com.fnz.common;

public class SQLConstants
{
	
	public static final String CREATE_CATEGORY_TABLE = "CREATE TABLE if not exists CATEGORY_TABLE (CATEGORY_ID text PRIMARY KEY, CATEGORY_NAME TEXT UNIQUE NOT NULL)";
	
	public static final String CREATE_ITEM_TABLE = "CREATE TABLE if not exists ITEMS_TABLE (ITEM_ID text UNIQUE NOT NULL,ITEM_NAME text,CATEGORY_ID TEXT)";
			
	
	public static final String CREATE_CATEGORY_TYPES = "CREATE TABLE if not exists CATEGORY_TYPES (TYPE_ID text PRIMARY KEY,TYPE TEXT, CATEGORY_ID TEXT,"+
			"FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORY_TABLE(CATEGORY_ID))" ;

	
	public static final String CREATE_ITEM_TYPE_TABLE =	"CREATE TABLE if not exists ITEMS_TYPES_TABLE (ITEM_ID text, TYPE_ID TEXT,  QUANTITY INTEGER, D_PRICE INTEGER, MRP INTEGER, H_PRICE INTEGER,"+
			"PRIMARY KEY(ITEM_ID, TYPE_ID),"+
			"FOREIGN KEY(ITEM_ID) REFERENCES ITEMS_TABLE(ITEM_ID)," +
			"FOREIGN KEY(TYPE_ID) REFERENCES CATEGORY_TYPES(TYPE_ID))";
	
	public static final String CREATE_INCOMING_STOCK = "CREATE TABLE if not exists INCOMING_STOCK (INVOICE_ID TEXT PRIMARY KEY, DATE_OF_DELIVERY text,"+
	"SUPPLIER TEXT)";
			
	
	public static final String CREATE_INCOMING_STOCK_DETAILS = "CREATE TABLE if not exists INCOMING_STOCK_DETAILS (INVOICE_ID TEXT, ITEM_ID text, TYPE_ID text, QUANTITY INTEGER," +
			" PRIMARY KEY(INVOICE_ID,ITEM_ID,TYPE_ID), FOREIGN KEY(TYPE_ID) REFERENCES CATEGORY_TYPES(TYPE_ID)," +
			" FOREIGN KEY(ITEM_ID) REFERENCES ITEMS_TABLE(ITEM_ID))";
	
	
	public static final String INSERT_CATEGORY_1 = "INSERT OR IGNORE INTO CATEGORY_TABLE (CATEGORY_ID, CATEGORY_NAME)";
	
	public static final String INSERT_ITEM = "INSERT INTO ITEMS_TABLE values (?,?,?)";
	
	public static final String INSERT_ITEMS_TYPES = "INSERT INTO ITEMS_TYPES_TABLE values (?,?,?,?,?,?)";

	public static final String INSERT_INCOMING_STOCK = "INSERT INTO INCOMING_STOCK values (?,?,?)";
	
	public static final String INSERT_CATEGORY_TYPES = "INSERT INTO CATEGORY_TYPES values (?,?,?)";
	
	public static final String INSERT_INCOMING_STOCK_DETAILS = "INSERT INTO INCOMING_STOCK_DETAILS values (?,?,?)"; 
	
	public static final String FETCH_LATEST_CATEGORY = "SELECT max(rowid) as row from CATEGORY_TABLE";
	
	public static final String FETCH_LATEST_ITEM = "SELECT max(rowid) as row from ITEMS_TABLE";
	
	public static final String FETCH_LATEST_CATEGORY_TYPE = "SELECT max(rowid) as row from CATEGORY_TYPES";
	
	public static final String FETCH_ITEM_FROM_CATEGORY = "SELECT ITEM_ID, ITEM_NAME from ITEMS_TABLE where CATEGORY_ID =? ";
	
	public static final String UPDATE_ITEM_QUANTITY = "UPDATE ITEMS_TABLE SET QUANTITY = ? where ITEM_ID =? ";
	
	public static final String FETCH_CATEGORY = "SELECT CATEGORY_ID,CATEGORY_NAME FROM CATEGORY_TABLE";
	
	public static final String FETCH_ITEMS = "SELECT ITEM_ID, ITEM_NAME,CATEGORY_ID FROM ITEMS_TABLE";
	
	public static final String FETCH_ITEMS_TYPES = "SELECT TYPE_ID,QUANTITY,D_PRICE,MRP,H_PRICE FROM ITEMS_TYPES_TABLE WHERE ITEM_ID =" ;
	
	public static final String FETCH_ITEMS_TYPES_2 = "AND TYPE_ID IN ";
	
	public static final String FETCH_TYPE_FROM_CATEGORY = "SELECT TYPE_ID, TYPE FROM CATEGORY_TYPES WHERE CATEGORY_ID = ?";
	
	public static final String DELETE_CATEGORY_TYPE = "DELETE FROM CATEGORY_TYPES WHERE TYPE_ID = ?";
	
	public static final String EDIT_CATEGORY_TYPE = "UPDATE CATEGORY_TYPES SET TYPE = ? WHERE TYPE_ID = ?";
	
	public static final String DELETE_ITEMS = "DELETE FROM ITEMS_TABLE where ITEM_ID = ?";
	
	public static final String DELETE_CATEGORY = "DELETE FROM CATEGORY_TABLE where CATEGORY_ID = ?";
	
	public static final String FETCH_ITEMS_FROM_CATEGORY = "SELECT ITEM_ID,ITEM_NAME FROM ITEMS_TABLE WHERE CATEGORY_ID = ?";
	
	public static final String UPDATE_ADD_ITEMS_TYPES_1 = "UPDATE ITEMS_TYPES_TABLE SET QUANTITY = QUANTITY + ";
	public static final String UPDATE_ADD_ITEMS_TYPES_2 = " WHERE ITEM_ID ='";
	public static final String UPDATE_ADD_ITEMS_TYPES_3 = "' AND TYPE_ID ='";
	public static final String UPDATE_ADD_ITEMS_TYPES_4 = "';";
	
	
	public static final String UPDATE_DEL_ITEMS_TYPES_1 = "UPDATE ITEMS_TYPES_TABLE SET QUANTITY = QUANTITY - ";
	public static final String UPDATE_DEL_ITEMS_TYPES_2 = " WHERE ITEM_ID ='";
	public static final String UPDATE_DEL_ITEMS_TYPES_3 = "' AND TYPE_ID ='";
	public static final String UPDATE_DEL_ITEMS_TYPES_4 = "';";
	
	
}
