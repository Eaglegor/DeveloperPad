package com.eaglegor.devpad.concepts;

import java.io.IOException;

public class ResourceManager {

	@SuppressWarnings("serial")
	public static class ResourceNotFoundException extends IOException {};
	
	private static ResourceManager instance = null;
	
	private ResourceManager()
	{
		
	}
	
	public static void initInstance()
	{
		if(instance == null)
		{
			instance = new ResourceManager();
		}
	}
	
	public static ResourceManager getInstance()
	{
		return instance;
	}
	
	public Resource getResource(ResourceHandle handle) throws ResourceNotFoundException
	{
		return null; // @todo
	}
	
}
