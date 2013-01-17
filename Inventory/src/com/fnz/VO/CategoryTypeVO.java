package com.fnz.VO;

public class CategoryTypeVO 
{
	private String typeId;
	private String typeName;
	private String categoryId;
	
	
	@Override
    public String toString() {
        return typeName;
    }

	
	
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
