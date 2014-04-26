package com.eaglegor.devpad.concepts;

public final class ResourceHandle {

	private int id;
	private String title;
	private String uri;
	private ResourceType type;
	
	public ResourceHandle(String title, String uri, ResourceType type) {
		this(-1, title, uri, type);
	}
	
	public ResourceHandle(int id, String title, String uri, ResourceType type) {
		this.id = id;
		this.title = title;
		this.uri = uri;
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	public String getUri() {
		return uri;
	}

	public ResourceType getType() {
		return type;
	}

	
	
}
