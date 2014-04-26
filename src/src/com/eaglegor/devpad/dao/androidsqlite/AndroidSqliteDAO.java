package com.eaglegor.devpad.dao.androidsqlite;

import android.database.sqlite.SQLiteDatabase;

import com.eaglegor.devpad.dao.DAO;
import com.eaglegor.devpad.dao.DAOManager;

public abstract class AndroidSqliteDAO<T> implements DAO<T> {
	
	protected SQLiteDatabase database = null;
	protected DAOManager daoManager = null;
	
	protected AndroidSqliteDAO(SQLiteDatabase database, DAOManager daoManager) {
		this.database = database;
		this.daoManager = daoManager;
	}
	
}
