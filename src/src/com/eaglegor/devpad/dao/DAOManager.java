package com.eaglegor.devpad.dao;

public interface DAOManager {
	
	public TaskDAO getTaskDAO();
	public TaskPriorityDAO getTaskPriorityDAO();
	public TaskTypeDAO getTaskTypeDAO();
	public TaskStatusDAO getTaskStatusDAO();
	public ResourceTypeDAO getResourceTypeDAO();
	public ChangeLogEntryDAO getChangeLogEntryDAO();
	public ResourceHandleDAO getResourceHandleDAO();
	
}
