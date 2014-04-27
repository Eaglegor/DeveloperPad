package com.eaglegor.devpad.dao.androidsqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
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
		
		ContentValues values = new ContentValues();
		values.put("title", object.getTitle());
		values.put("color_code", object.getColorCode());
		
		if(object.getId() > 0)
		{
			database.update(TABLE_NAME, values, "_id = ?", new String[]{Integer.toString(object.getId())});
		}
		else
		{
			object.setId((int) database.insert(TABLE_NAME, null, values));
		}
		
	}

	@Override
	public void refresh(TaskType object) {

		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, "_id = ?", new String[]{Integer.toString(object.getId())}, null, null, DEFAULT_ORDER_BY_COLUMN, null);

		if(cursor.moveToNext()) {
			object.setTitle(cursor.getString(1));
			object.setColorCode(cursor.getString(2));
		}

	}

	@Override
	public TaskType load(int id) {
		TaskType taskType = null;

		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, "_id = ?", new String[]{Integer.toString(id)}, null, null, DEFAULT_ORDER_BY_COLUMN, null);

		if(cursor.moveToNext()) {
			taskType = new TaskType(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
		}

		return taskType;
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
		database.delete(TABLE_NAME, "_id = ?", new String[]{Integer.toString(id)});
	}

}
