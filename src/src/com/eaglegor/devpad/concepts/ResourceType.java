package com.eaglegor.devpad.concepts;

public final class ResourceType {

	private int id;
	private String classname;
	private String title;
	
	public void setClassname(String classname) {
		this.classname = classname;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ResourceType(int id, String title, String classname)
	{
		this.id = id;
		this.title = title;
		this.classname = classname;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getClassname() {
		return classname;
	}
	
}
