package com.eaglegor.devpad.dao.androidsqlite;

import com.eaglegor.devpad.dao.ChangeLogEntryDAO;
import com.eaglegor.devpad.dao.DAOManager;
import com.eaglegor.devpad.dao.ResourceHandleDAO;
import com.eaglegor.devpad.dao.ResourceTypeDAO;
import com.eaglegor.devpad.dao.TaskDAO;
import com.eaglegor.devpad.dao.TaskPriorityDAO;
import com.eaglegor.devpad.dao.TaskStatusDAO;
import com.eaglegor.devpad.dao.TaskTypeDAO;

import android.database.sqlite.SQLiteDatabase;

public class AndroidSqliteDAOManager implements DAOManager {

	private SQLiteDatabase database = null;
	
	private TaskDAO taskDAO = null;
	private TaskPriorityDAO taskPriorityDAO = null;
	private TaskTypeDAO taskTypeDAO = null;
	private TaskStatusDAO taskStatusDAO = null;
	private ResourceTypeDAO resourceTypeDAO = null;
	private ChangeLogEntryDAO changeLogEntryDAO = null;
	private ResourceHandleDAO resourceHandleDAO = null;
	
	public AndroidSqliteDAOManager(SQLiteDatabase database) {
		this.database = database;
	}

	@Override
	public TaskDAO getTaskDAO() {
		if (taskDAO == null) {
			taskDAO = new AndroidSqliteTaskDAO(database, this);
		}
		return taskDAO;
	}

	@Override
	public TaskPriorityDAO getTaskPriorityDAO() {
		if (taskPriorityDAO == null) {
			taskPriorityDAO = new AndroidSqliteTaskPriorityDAO(database, this);
		}
		return taskPriorityDAO;
	}

	@Override
	public TaskTypeDAO getTaskTypeDAO() {
		if (taskTypeDAO == null) {
			taskTypeDAO = new AndroidSqliteTaskTypeDAO(database, this);
		}
		return taskTypeDAO;
	}

	@Override
	public TaskStatusDAO getTaskStatusDAO() {
		if (taskStatusDAO == null) {
			taskStatusDAO = new AndroidSqliteTaskStatusDAO(database, this);
		}
		return taskStatusDAO;
	}

	@Override
	public ResourceTypeDAO getResourceTypeDAO() {
		if (resourceTypeDAO == null) {
			resourceTypeDAO = new AndroidSqliteResourceTypeDAO(database, this);
		}
		return resourceTypeDAO;
	}

	@Override
	public ChangeLogEntryDAO getChangeLogEntryDAO() {
		if (changeLogEntryDAO == null) {
			changeLogEntryDAO = new AndroidSqliteChangeLogEntryDAO(database, this);
		}
		return changeLogEntryDAO;
	}

	@Override
	public ResourceHandleDAO getResourceHandleDAO() {
		if (resourceHandleDAO == null) {
			resourceHandleDAO = new AndroidSqliteResourceHandleDAO(database, this);
		}
		return resourceHandleDAO;
	}

}
