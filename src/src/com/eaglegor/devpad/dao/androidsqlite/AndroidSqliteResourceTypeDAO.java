package com.eaglegor.devpad.dao.androidsqlite;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;

import com.eaglegor.devpad.concepts.ResourceType;
import com.eaglegor.devpad.dao.ResourceTypeDAO;

public class AndroidSqliteResourceTypeDAO implements ResourceTypeDAO {

	private SQLiteDatabase database = null;
	
	public AndroidSqliteResourceTypeDAO(SQLiteDatabase database) {
		this.database = database;
	}

	@Override
	public void save(ResourceType object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(ResourceType object) {
		// TODO Auto-generated method stub

	}

	@Override
	public ResourceType load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResourceType> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
