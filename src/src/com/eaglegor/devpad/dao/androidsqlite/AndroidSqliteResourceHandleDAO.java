package com.eaglegor.devpad.dao.androidsqlite;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

import com.eaglegor.devpad.concepts.ResourceHandle;
import com.eaglegor.devpad.concepts.Task;
import com.eaglegor.devpad.dao.ResourceHandleDAO;

public class AndroidSqliteResourceHandleDAO implements ResourceHandleDAO {

	private SQLiteDatabase database = null;
	
	public AndroidSqliteResourceHandleDAO(SQLiteDatabase database) {
		this.database = database;
	}

	@Override
	public void save(ResourceHandle object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(ResourceHandle object) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResourceHandle load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourceHandle> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourceHandle> findAllForTask(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

}
