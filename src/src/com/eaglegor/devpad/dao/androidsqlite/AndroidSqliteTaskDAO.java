package com.eaglegor.devpad.dao.androidsqlite;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

import com.eaglegor.devpad.concepts.Task;
import com.eaglegor.devpad.dao.TaskDAO;

public class AndroidSqliteTaskDAO implements TaskDAO {

	private SQLiteDatabase database = null;
	
	public AndroidSqliteTaskDAO(SQLiteDatabase database) {
		this.database = database;
	}

	@Override
	public void save(Task object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Task object) {
		// TODO Auto-generated method stub

	}

	@Override
	public Task load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
