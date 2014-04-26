package com.eaglegor.devpad.dao.androidsqlite;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eaglegor.devpad.concepts.TaskPriority;
import com.eaglegor.devpad.dao.DAOManager;
import com.eaglegor.devpad.dao.TaskPriorityDAO;

public class AndroidSqliteTaskPriorityDAO extends AndroidSqliteDAO<TaskPriority> implements TaskPriorityDAO {

	final private String TABLE_NAME = "Tasks";
	final private String[] DATA_COLUMNS = {"_id", "title", "value", "color_code"};
	final private String DEFAULT_ORDER_BY_COLUMN = "value";

	public AndroidSqliteTaskPriorityDAO(SQLiteDatabase database, DAOManager daoManager) {
		super(database, daoManager);
	}
	
	@Override
	public void save(TaskPriority object) {
		// TODO Auto-generated method stub
	}

	

	@Override
	public void refresh(TaskPriority object) {
		// TODO Auto-generated method stub

	}

	@Override
	public TaskPriority load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaskPriority> loadAll() {
		List<TaskPriority> taskPriorities = new ArrayList<TaskPriority>();
		
		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, null, null, null, null, DEFAULT_ORDER_BY_COLUMN, null);
		while(cursor.moveToNext())
		{
			TaskPriority taskPriority = new TaskPriority(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3));
			taskPriorities.add(taskPriority);
		}
		
		return taskPriorities;
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		
	}

}
