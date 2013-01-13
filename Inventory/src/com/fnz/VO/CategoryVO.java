package com.fnz.VO;

public class CategoryVO 
{
	private String categotyId;
	private String categoryName;
	
	
	
	@Override
    public String toString() {
        return categoryName;
    }

    
	
	/**
	 * @return the categotyId
	 */
	public String getCategotyId() {
		return categotyId;
	}
	/**
	 * @param categotyId the categotyId to set
	 */
	public void setCategotyId(String categotyId) {
		this.categotyId = categotyId;
	}
	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
