package com.eaglegor.devpad.concepts;

import java.util.Date;

public final class ChangeLogEntry {

	private int id;
	private ResourceHandle relatedResource;
	private String text;
	private Date creationDate;
	
	public ChangeLogEntry(int id, ResourceHandle relatedResource, String text, Date creationDate) {
		this.id = id;
		this.relatedResource = relatedResource;
		this.text = text;
		this.creationDate = creationDate;
	}

	public ChangeLogEntry(ResourceHandle relatedResource, String text, Date creationDate) {
		this(-1, relatedResource, text, creationDate);
	}
	
}
