package com.eaglegor.devpad.concepts;

import android.view.View;

public abstract class Resource {

	private ResourceHandle handle = null;
	
	public final ResourceHandle getHandle() {
		return handle;
	}
	
	public void load(ResourceHandle handle)
	{
		this.handle = handle;
	}
	
	public abstract View getSummaryView();
	public abstract void drawSummaryToView(View view);
	public abstract View getView();
	public abstract void drawToView(View view);
	
}
