package com.eaglegor.devpad.dao.androidsqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
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

		ContentValues values = new ContentValues();
		values.put("title", object.getTitle());
		values.put("value", object.getValue());
		values.put("color_code", object.getColorCode());
		
		if(object.getId() > 0)
		{
			database.update(TABLE_NAME, values, "_id = ?", new String[]{Integer.toString(object.getId())});
		}
		else
		{
			database.insert(TABLE_NAME, null, values);
		}
		
	}

	

	@Override
	public void refresh(TaskPriority object) {

		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, "_id = ?", new String[]{Integer.toString(object.getId())}, null, null, DEFAULT_ORDER_BY_COLUMN, null);

		if(cursor.moveToNext()) {
			object.setTitle(cursor.getString(1));
			object.setColorCode(cursor.getString(3));
			object.setValue(cursor.getInt(2));
		}

	}

	@Override
	public TaskPriority load(int id) {
		TaskPriority taskPriority = null;

		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, "_id = ?", new String[]{Integer.toString(id)}, null, null, DEFAULT_ORDER_BY_COLUMN, null);

		if(cursor.moveToNext()) {
			taskPriority = new TaskPriority(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3));
		}

		return taskPriority;
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
		database.delete(TABLE_NAME, "_id = ?", new String[]{Integer.toString(id)});
	}

}
