package com.eaglegor.devpad.dao.androidsqlite;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

import com.eaglegor.devpad.concepts.TaskStatus;
import com.eaglegor.devpad.dao.TaskStatusDAO;

public class AndroidSqliteTaskStatusDAO implements TaskStatusDAO {

	private SQLiteDatabase database = null;
	
	public AndroidSqliteTaskStatusDAO(SQLiteDatabase database) {
		this.database = database;
	}

	@Override
	public void save(TaskStatus object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(TaskStatus object) {
		// TODO Auto-generated method stub

	}

	@Override
	public TaskStatus load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TaskStatus> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
