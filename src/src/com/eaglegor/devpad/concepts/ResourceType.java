package com.eaglegor.devpad.concepts;

public final class ResourceType {

	private int id;
	private String classname;
	private String title;
	
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
