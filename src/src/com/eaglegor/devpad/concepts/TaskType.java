package com.eaglegor.devpad.concepts;

public final class TaskType {

	private int id;
	private String title;
	private String colorCode;
	
	public TaskType(String title, String colorCode)
	{
		this(-1, title, colorCode);
	}
	
	public TaskType(int id, String title, String colorCode)
	{
		this.id = id;
		this.title = title;
	}

	public void setId(int id) {
		if(this.id < 0) this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}
	
}
