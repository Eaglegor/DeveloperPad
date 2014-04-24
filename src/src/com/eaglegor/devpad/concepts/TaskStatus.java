package com.eaglegor.devpad.concepts;

public final class TaskStatus {

	private int id;
	private String title;
	private String colorCode;

	public TaskStatus(String title, String colorCode)
	{
		this(-1, title, colorCode);
	}
	
	public TaskStatus(int id, String title, String colorCode)
	{
		this.id = id;
		this.title = title;
		this.colorCode = colorCode;
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
