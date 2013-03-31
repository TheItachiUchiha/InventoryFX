package com.fnz.VO;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class StockVO 
{
	private final StringProperty invoiceId;
	private final StringProperty date;
	private final StringProperty itemId;
	private final StringProperty itemName;
	private final StringProperty typeId;
	private final StringProperty typeName;
	private final IntegerProperty quantity;
	private final BooleanProperty check;
	
	public StockVO()
	{
		this.invoiceId = new SimpleStringProperty();
		this.date = new SimpleStringProperty();
		this.itemId = new SimpleStringProperty();
		this.itemName = new SimpleStringProperty();
		this.typeId = new SimpleStringProperty();
		this.typeName = new SimpleStringProperty();
		this.quantity = new SimpleIntegerProperty();
		this.check = new SimpleBooleanProperty();
	}
	StockVO(String invoiceId, String date,String itemId,String itemName,String typeId,String typeName,Integer quantity,  boolean check) {
		this.invoiceId = new SimpleStringProperty(invoiceId);
		this.date = new SimpleStringProperty(date);
		this.itemId = new SimpleStringProperty(itemId);
		this.itemName = new SimpleStringProperty(itemName);
		this.typeId = new SimpleStringProperty(typeId);
		this.typeName = new SimpleStringProperty(typeName);
		this.quantity = new SimpleIntegerProperty(quantity);
		this.check = new SimpleBooleanProperty(check);
	}
	
	
	public String getInvoiceId() {
		return invoiceId.get();
	}
	public String getDate() {
		return date.get();
	}
	public String getItemId() {
		return itemId.get();
	}
	public String getItemName() {
		return itemName.get();
	}
	public String getTypeId() {
		return typeId.get();
	}
	public String getTypeName() {
		return typeName.get();
	}
	public Integer getQuantity() {
		return quantity.get();
	}
	public Boolean getCheck() {
		return check.get();
	}
	
	public void setInvoiceId(String invoiceId) {
		this.invoiceId.set(invoiceId);
	}
	
	public void setDate(String date) {
		this.date.set(date);
	}

	public void setItemId(String itemId) {
		this.itemId.set(itemId);
	}
	
	public void setItemName(String itemName) {
		this.itemName.set(itemName);
	}
	public void setTypeId(String typeId) {
		this.typeId.set(typeId);
	}
	public void setTypeName(String typeName) {
		this.typeName.set(typeName);
	}
	public void setQuantity(Integer quantity) {
		this.quantity.set(quantity);
	}
	public void setCheck(boolean check) {
		this.check.set(check);
	}

	public BooleanProperty checkProperty() {
		return check;
	}
	
}
