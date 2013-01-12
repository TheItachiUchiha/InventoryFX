package com.fnz.VO;



import javafx.collections.ObservableList;

public class ItemVO 
{
	private String itemId;
	private String itemName;
	private String categoryId;
	private ObservableList<ItemTypeVO> listType;
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public ObservableList<ItemTypeVO> getListType() {
		return listType;
	}
	public void setListType(ObservableList<ItemTypeVO> listType) {
		this.listType = listType;
	}
	
	
}
