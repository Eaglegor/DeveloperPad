package com.eaglegor.devpad.dao.androidsqlite;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

import com.eaglegor.devpad.concepts.TaskPriority;
import com.eaglegor.devpad.dao.TaskPriorityDAO;

public class AndroidSqliteTaskPriorityDAO implements TaskPriorityDAO {

	private SQLiteDatabase database = null;
	
	@Override
	public void save(TaskPriority object) {
		// TODO Auto-generated method stub

	}

	public AndroidSqliteTaskPriorityDAO(SQLiteDatabase database) {
		this.database = database;
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
		// TODO Auto-generated method stub
		return null;
	}

}
