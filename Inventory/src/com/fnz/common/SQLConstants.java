package com.fnz.common;

public class SQLConstants
{
	
	public static final String CREATE_CATEGORY_TABLE = "CREATE TABLE if not exists CATEGORY_TABLE (CATEGORY_ID text PRIMARY KEY, CATEGORY_NAME TEXT UNIQUE NOT NULL)";
	
	public static final String CREATE_ITEM_TABLE = "CREATE TABLE if not exists ITEMS_TABLE (ITEM_ID text UNIQUE NOT NULL,ITEM_NAME text UNIQUE NOT NULL,CATEGORY_ID TEXT)";
	
	public static final String INDEX_ITEM_TABLE = "CREATE INDEX IF NOT EXISTS INDEX_ITEMS_TABLE ON ITEMS_TABLE (CATEGORY_ID)";
			
	
	public static final String CREATE_CATEGORY_TYPES = "CREATE TABLE if not exists CATEGORY_TYPES (TYPE_ID text PRIMARY KEY,TYPE TEXT UNIQUE NOT NULL, CATEGORY_ID TEXT,"+
			"FOREIGN KEY(CATEGORY_ID) REFERENCES CATEGORY_TABLE(CATEGORY_ID))" ;
	
	public static final String INDEX_CATEGORY_TYPES = "CREATE INDEX IF NOT EXISTS INDEX_CATEGORY_TYPES ON CATEGORY_TYPES (CATEGORY_ID)";

	
	public static final String CREATE_ITEM_TYPE_TABLE =	"CREATE TABLE if not exists ITEMS_TYPES_TABLE (ITEM_ID text, TYPE_ID TEXT,  QUANTITY INTEGER, D_PRICE INTEGER, MRP INTEGER, H_PRICE INTEGER,"+
			"PRIMARY KEY(ITEM_ID, TYPE_ID),"+
			"FOREIGN KEY(ITEM_ID) REFERENCES ITEMS_TABLE(ITEM_ID)," +
			"FOREIGN KEY(TYPE_ID) REFERENCES CATEGORY_TYPES(TYPE_ID))";
	
	public static final String CREATE_INCOMING_STOCK = "CREATE TABLE if not exists INCOMING_STOCK (INVOICE_ID TEXT PRIMARY KEY, DATE_OF_DELIVERY DATETIME,"+
	"SUPPLIER TEXT)";
			
	public static final String INDEX_INCOMING_STOCK = "CREATE INDEX IF NOT EXISTS INDEX_INCOMING_STOCK ON INCOMING_STOCK (DATE_OF_DELIVERY)";
	
	
	public static final String CREATE_INCOMING_STOCK_DETAILS = "CREATE TABLE if not exists INCOMING_STOCK_DETAILS (INVOICE_ID TEXT,DATE_OF_DELIVERY DATETIME, ITEM_ID text, TYPE_ID text, QUANTITY INTEGER," +
			" PRIMARY KEY(INVOICE_ID,ITEM_ID,TYPE_ID), FOREIGN KEY(TYPE_ID) REFERENCES CATEGORY_TYPES(TYPE_ID)," +
			" FOREIGN KEY(ITEM_ID) REFERENCES ITEMS_TABLE(ITEM_ID))";
	
	
	/*public static final String CREATE_INCOMING_STOCK_DETAILS = "CREATE TABLE if not exists INCOMING_STOCK_DETAILS (INVOICE_ID TEXT, ITEM_NAME text, TYPE text, QUANTITY INTEGER)";*/
			
	
	public static final String CREATE_OUTGOING_STOCK_DETAILS = "CREATE TABLE if not exists OUTGOING_STOCK_DETAILS (DATE_OF_DELIVERY DATETIME, ITEM_ID text, TYPE_ID text, QUANTITY INTEGER)";
			
	
	public static final String INDEX_OUTGOING_STOCK_DETAILS = "CREATE INDEX IF NOT EXISTS INDEX_OUTGOING_STOCK_DETAILS ON OUTGOING_STOCK_DETAILS (DATE_OF_DELIVERY)";
	
	public static final String INSERT_CATEGORY_1 = "INSERT OR IGNORE INTO CATEGORY_TABLE (CATEGORY_ID, CATEGORY_NAME)";
	
	public static final String INSERT_ITEM = "INSERT INTO ITEMS_TABLE values (?,?,?)";
	
	public static final String INSERT_ITEMS_TYPES = "INSERT INTO ITEMS_TYPES_TABLE values (?,?,?,?,?,?)";
	
	public static final String CHECK_ITEMS_TYPES = "SELECT * FROM ITEMS_TYPES_TABLE WHERE ITEM_ID=? AND TYPE_ID=? ";
	
	public static final String UPDATE_ITEMS_TYPES = "UPDATE ITEMS_TYPES_TABLE SET D_PRICE = ?, MRP = ?, H_PRICE = ? WHERE ITEM_ID=? AND TYPE_ID=? ";

	public static final String INSERT_INCOMING_STOCK = "INSERT INTO INCOMING_STOCK values (?,?,?)";
	
	public static final String INSERT_CATEGORY_TYPES = "INSERT INTO CATEGORY_TYPES values (?,?,?)";
	
	public static final String FETCH_LATEST_CATEGORY = "SELECT max(rowid) as row from CATEGORY_TABLE";
	
	public static final String FETCH_LATEST_ITEM = "SELECT max(rowid) as row from ITEMS_TABLE";
	
	public static final String FETCH_LATEST_CATEGORY_TYPE = "SELECT max(rowid) as row from CATEGORY_TYPES";
	
	public static final String FETCH_ITEM_FROM_CATEGORY = "SELECT ITEM_ID, ITEM_NAME from ITEMS_TABLE where CATEGORY_ID =? ";
	
	public static final String FETCH_CATEGORY = "SELECT CATEGORY_ID,CATEGORY_NAME FROM CATEGORY_TABLE";
	
	public static final String FETCH_ITEMS = "SELECT ITEM_ID, ITEM_NAME,CATEGORY_ID FROM ITEMS_TABLE where category_id=?";
	
	public static final String FETCH_ITEMS_TYPES = "SELECT TYPE_ID,QUANTITY,D_PRICE,MRP,H_PRICE FROM ITEMS_TYPES_TABLE WHERE ITEM_ID =" ;
	
	public static final String FETCH_ITEMS_TYPES_2 = "AND TYPE_ID IN ";
	
	public static final String FETCH_TYPE_FROM_CATEGORY = "SELECT TYPE_ID, TYPE FROM CATEGORY_TYPES WHERE CATEGORY_ID = ?";
	
	public static final String DELETE_CATEGORY_TYPE = "DELETE FROM CATEGORY_TYPES WHERE TYPE_ID = ?";
	
	public static final String EDIT_CATEGORY_TYPE = "UPDATE CATEGORY_TYPES SET TYPE = ? WHERE TYPE_ID = ?";
	
	public static final String DELETE_ITEMS = "DELETE FROM ITEMS_TABLE where ITEM_ID = ?";
	
	public static final String DELETE_ITEM_TYPE_TABLE = "DELETE FROM ITEMS_TYPES_TABLE where ITEM_ID = ?";
	
	public static final String EDIT_ITEMS = "UPDATE ITEMS_TABLE SET ITEM_NAME = ? where ITEM_ID = ?";
	
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
	
	
	public static final String INSERT_INCOMING_STOCK_1 = "INSERT OR IGNORE INTO INCOMING_STOCK values ('";
	public static final String INSERT_INCOMING_STOCK_2 = "','";
	public static final String INSERT_INCOMING_STOCK_3 = "');";
	
	public static final String INSERT_INCOMING_STOCK_DETAILS_1 = "INSERT INTO INCOMING_STOCK_DETAILS values ('";
	public static final String INSERT_INCOMING_STOCK_DETAILS_2 = "','";
	public static final String INSERT_INCOMING_STOCK_DETAILS_3 = "','";
	public static final String INSERT_INCOMING_STOCK_DETAILS_4 = "',";
	public static final String INSERT_INCOMING_STOCK_DETAILS_5 = ");";
	
	public static final String INSERT_OUTGOING_STOCK_DETAILS_1 = "INSERT INTO OUTGOING_STOCK_DETAILS values ('";
	public static final String INSERT_OUTGOING_STOCK_DETAILS_2 = "','";
	public static final String INSERT_OUTGOING_STOCK_DETAILS_3 = "',";
	public static final String INSERT_OUTGOING_STOCK_DETAILS_4 = ");";
	
	public static final String FETCH_INCOMING_DETAILS_BY_DATE = "SELECT ID.INVOICE_ID, ID.DATE_OF_DELIVERY, IT.ITEM_ID, IT.ITEM_NAME, CT.TYPE_ID, CT.TYPE, ID.QUANTITY FROM "+
					" INCOMING_STOCK_DETAILS ID, ITEMS_TABLE IT, CATEGORY_TYPES CT WHERE IT.ITEM_ID = ID.ITEM_ID AND CT.TYPE_ID = ID.TYPE_ID AND ID.DATE_OF_DELIVERY >= ? and ID.DATE_OF_DELIVERY <= ?";
			
	
	public static final String FETCH_INCOMING_DETAILS_BY_INVOICE = "SELECT ID.INVOICE_ID, ID.DATE_OF_DELIVERY, IT.ITEM_NAME, CT.TYPE, ID.QUANTITY FROM "+
					" INCOMING_STOCK_DETAILS ID, ITEMS_TABLE IT, CATEGORY_TYPES CT WHERE IT.ITEM_ID = ID.ITEM_ID AND CT.TYPE_ID = ID.TYPE_ID AND  ID.INVOICE_ID=?";
			
	public static final String FETCH_OUTGOING_DETAILS_BY_DATE = "SELECT I.DATE_OF_DELIVERY, IT.ITEM_NAME, CT.TYPE, I.QUANTITY FROM OUTGOING_STOCK_DETAILS I,"+
			"ITEMS_TABLE IT, CATEGORY_TYPES CT WHERE I.DATE_OF_DELIVERY >= ? and I.DATE_OF_DELIVERY <= ?";
	
	public static final String FETCH_OUTGOING_DETAILS_BY_INVOICE = "SELECT I.DATE_OF_DELIVERY, IT.ITEM_NAME, CT.TYPE, I.QUANTITY FROM OUTGOING_STOCK_DETAILS I,"+
			"ITEMS_TABLE IT, CATEGORY_TYPES CT WHERE IT.ITEM_ID = I.ITEM_ID AND CT.TYPE_ID = I.TYPE_ID AND I.INVOICE_ID=?";
	
	public static final String FETCH_INVOICE_INCOMING = "SELECT DISTINCT I.INVOICE_ID FROM INCOMING_STOCK_DETAILS I";
	
	public static final String FETCH_INVOICE_OUTGOING = "SELECT DISTINCT I.INVOICE_ID FROM OUTGOING_STOCK_DETAILS I";
	
	
	public static final String DELETE_TRANSACTION_INCOMING_STOCK_1 = "DELETE FROM INCOMING_STOCK WHERE INVOICE_ID='";
	public static final String DELETE_TRANSACTION_INCOMING_STOCK_2 = "' AND DATE_OF_DELIVERY='";
	public static final String DELETE_TRANSACTION_INCOMING_STOCK_3 = "'";
	
	public static final String DELETE_TRANSACTION_INCOMING_STOCK_DETAILS_1 = "DELETE FROM INCOMING_STOCK_DETAILS WHERE INVOICE_ID='";
	public static final String DELETE_TRANSACTION_INCOMING_STOCK_DETAILS_2 = "' AND DATE_OF_DELIVERY='";
	public static final String DELETE_TRANSACTION_INCOMING_STOCK_DETAILS_3 = "' AND ITEM_ID='";
	public static final String DELETE_TRANSACTION_INCOMING_STOCK_DETAILS_4 = "' AND TYPE_ID='";
	public static final String DELETE_TRANSACTION_INCOMING_STOCK_DETAILS_5 = "'";
	
	public static final String DELETE_TRANSACTION_UPDATE_ITEM_TYPE_1 = "UPDATE ITEMS_TYPES_TABLE SET QUANTITY = QUANTITY - ";
	public static final String DELETE_TRANSACTION_UPDATE_ITEM_TYPE_2 = " WHERE ITEM_ID ='";
	public static final String DELETE_TRANSACTION_UPDATE_ITEM_TYPE_3 = "' AND TYPE_ID ='";
	public static final String DELETE_TRANSACTION_UPDATE_ITEM_TYPE_4 = "';";
			
	
}
