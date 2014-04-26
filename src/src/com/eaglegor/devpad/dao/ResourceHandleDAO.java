package com.eaglegor.devpad.dao;

import java.util.List;

import com.eaglegor.devpad.concepts.ResourceHandle;
import com.eaglegor.devpad.concepts.Task;

public interface ResourceHandleDAO extends DAO<ResourceHandle> {

	public List<ResourceHandle> findAllForTask(Task task);
	
}
