package com.eaglegor.devpad.concepts;

import java.util.Date;

public final class ChangeLogEntry {

	private int id;
	private ResourceHandle relatedResource;
	private String text;
	private Date creationDate;
	private Task relatedTask;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		if (this.id < 0) this.id = id;
	}

	public ResourceHandle getRelatedResource() {
		return relatedResource;
	}

	public String getText() {
		return text;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setRelatedResource(ResourceHandle relatedResource) {
		this.relatedResource = relatedResource;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	
	
	public ChangeLogEntry(int id, ResourceHandle relatedResource, String text, Date creationDate, Task relatedTask) {
		this.id = id;
		this.relatedResource = relatedResource;
		this.text = text;
		this.creationDate = creationDate;
		this.relatedTask = relatedTask;
	}

	public ChangeLogEntry(ResourceHandle relatedResource, String text, Date creationDate, Task relatedTask) {
		this(-1, relatedResource, text, creationDate, relatedTask);
	}

	public ChangeLogEntry(ResourceHandle relatedResource, String text, Date creationDate) {
		this(-1, relatedResource, text, creationDate, null);
	}
	
	public Task getRelatedTask() {
		return relatedTask;
	}

	public void setRelatedTask(Task relatedTask) {
		this.relatedTask = relatedTask;
	}
	
}
