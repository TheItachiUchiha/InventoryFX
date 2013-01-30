package com.fnz.VO;

import javafx.collections.ObservableMap;





public class ItemVO 
{
	private String itemId;
	private String itemName;
	private String categoryId;
	private ObservableMap<String,ItemTypeVO> listType;
	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the listType
	 */
	public ObservableMap<String, ItemTypeVO> getListType() {
		return listType;
	}
	/**
	 * @param listType the listType to set
	 */
	public void setListType(ObservableMap<String, ItemTypeVO> listType) {
		this.listType = listType;
	}
	
	
	@Override
    public String toString() {
        return itemName;
    }
	
	
}
