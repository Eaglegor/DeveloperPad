package com.eaglegor.devpad.dao.androidsqlite;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

import com.eaglegor.devpad.concepts.ChangeLogEntry;
import com.eaglegor.devpad.concepts.Task;
import com.eaglegor.devpad.dao.ChangeLogEntryDAO;

public class AndroidSqliteChangeLogEntryDAO implements ChangeLogEntryDAO {

	private SQLiteDatabase database = null;
	
	public AndroidSqliteChangeLogEntryDAO(SQLiteDatabase database) {
		this.database = database;
	}

	@Override
	public void save(ChangeLogEntry object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(ChangeLogEntry object) {
		// TODO Auto-generated method stub

	}

	@Override
	public ChangeLogEntry load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChangeLogEntry> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChangeLogEntry> findAllForTask(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

}
