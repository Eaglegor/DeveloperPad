package com.eaglegor.devpad.dao.androidsqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eaglegor.devpad.concepts.TaskStatus;
import com.eaglegor.devpad.dao.DAOManager;
import com.eaglegor.devpad.dao.TaskStatusDAO;

public class AndroidSqliteTaskStatusDAO extends AndroidSqliteDAO<TaskStatus> implements TaskStatusDAO {

	final private String TABLE_NAME = "TaskTypes";
	final private String[] DATA_COLUMNS = {"_id", "title", "color_code"};
	final private String DEFAULT_ORDER_BY_COLUMN = "_id";
	
	public AndroidSqliteTaskStatusDAO(SQLiteDatabase database, DAOManager daoManager) {
		super(database, daoManager);
	}

	@Override
	public void save(TaskStatus object) {
		
		ContentValues values = new ContentValues();
		values.put("title", object.getTitle());
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
	public void refresh(TaskStatus object) {

		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, "_id = ?", new String[]{Integer.toString(object.getId())}, null, null, DEFAULT_ORDER_BY_COLUMN, null);

		if(cursor.moveToNext()) {
			object.setTitle(cursor.getString(1));
			object.setColorCode(cursor.getString(2));
		}

	}

	@Override
	public TaskStatus load(int id) {
		TaskStatus taskStatus = null;

		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, "_id = ?", new String[]{Integer.toString(id)}, null, null, DEFAULT_ORDER_BY_COLUMN, null);

		if(cursor.moveToNext()) {
			taskStatus = new TaskStatus(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
		}

		return taskStatus;
	}

	@Override
	public List<TaskStatus> loadAll() {
		List<TaskStatus> taskStatuses = new ArrayList<TaskStatus>();
		
		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, null, null, null, null, DEFAULT_ORDER_BY_COLUMN, null);
		while(cursor.moveToNext())
		{
			TaskStatus taskStatus = new TaskStatus(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
			taskStatuses.add(taskStatus);
		}
		
		return taskStatuses;
	}

	@Override
	public void remove(int id) {
		database.delete(TABLE_NAME, "_id = ?", new String[]{Integer.toString(id)});	
	}

}
