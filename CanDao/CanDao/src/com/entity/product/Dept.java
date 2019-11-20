package com.entity.product;

public class Dept {

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	String id;
	String title;
	SubDept subDept;
	
	public SubDept getSubDept() {
		return subDept;
	}
	public void setSubDept(SubDept subDept) {
		this.subDept = subDept;
	}
}
