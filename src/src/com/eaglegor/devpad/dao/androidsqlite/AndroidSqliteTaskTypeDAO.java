package com.eaglegor.devpad.dao.androidsqlite;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

import com.eaglegor.devpad.concepts.TaskType;
import com.eaglegor.devpad.dao.TaskTypeDAO;

public class AndroidSqliteTaskTypeDAO implements TaskTypeDAO {

	private SQLiteDatabase database = null;
	
	public AndroidSqliteTaskTypeDAO(SQLiteDatabase database) {
		this.database = database;
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
		// TODO Auto-generated method stub
		return null;
	}

}
