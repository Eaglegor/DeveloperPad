package com.eaglegor.devpad.concepts;

public final class TaskPriority implements Comparable<TaskPriority> {

	private int id;
	private int value;
	private String title;
	private String colorCode;
	
	public TaskPriority(String title, int value, String colorCode)
	{
		this(-1, title, value, colorCode);
	}
	
	public TaskPriority(int id, String title, int value, String colorCode)
	{
		this.id = id;
		this.title = title;
		this.value = value;
		this.colorCode = colorCode;
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getColorCode() {
		return colorCode;
	}

	public void setColorCode(String colorCode) {
		this.colorCode = colorCode;
	}

	@Override
	public int compareTo(TaskPriority another) {
		if(this.getValue() > another.getValue())
		{
			return 1;
		}
		else if(this.getValue() < another.getValue())
		{
			return -1;
		}
		else 
		{
			return 0;
		}
	}
	
}
