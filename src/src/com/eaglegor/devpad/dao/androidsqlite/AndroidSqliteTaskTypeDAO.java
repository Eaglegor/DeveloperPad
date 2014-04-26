package com.eaglegor.devpad.dao.androidsqlite;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eaglegor.devpad.concepts.TaskType;
import com.eaglegor.devpad.dao.DAOManager;
import com.eaglegor.devpad.dao.TaskTypeDAO;

public class AndroidSqliteTaskTypeDAO extends AndroidSqliteDAO<TaskType> implements TaskTypeDAO {
	
	final private String TABLE_NAME = "TaskTypes";
	final private String[] DATA_COLUMNS = {"_id", "title", "color_code"};
	final private String DEFAULT_ORDER_BY_COLUMN = "_id";

	public AndroidSqliteTaskTypeDAO(SQLiteDatabase database, DAOManager daoManager) {
		super(database, daoManager);
	}

	@Override
	public void save(TaskType object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(TaskType object) {
		// TODO Auto-generated method stub

	}

	@Override
	public TaskType load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaskType> loadAll() {
		List<TaskType> taskTypes = new ArrayList<TaskType>();
		
		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, null, null, null, null, DEFAULT_ORDER_BY_COLUMN, null);
		while(cursor.moveToNext())
		{
			TaskType taskType = new TaskType(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
			taskTypes.add(taskType);
		}
		
		return taskTypes;
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		
	}

}
