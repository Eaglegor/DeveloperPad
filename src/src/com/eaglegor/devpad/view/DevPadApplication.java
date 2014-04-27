package com.eaglegor.devpad.view;

import com.eaglegor.devpad.dao.DAOManager;
import com.eaglegor.devpad.dao.androidsqlite.AndroidSQLiteDevpadDatabase;
import com.eaglegor.devpad.dao.androidsqlite.AndroidSqliteDAOManager;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

public class DevPadApplication extends Application {

	private DAOManager daoManager = null;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		AndroidSQLiteDevpadDatabase dbHelper = new AndroidSQLiteDevpadDatabase(getApplicationContext(), "DevPadMainDatabase", null, 1);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		daoManager = new AndroidSqliteDAOManager(db);
		dbHelper.fillDemoData(db, daoManager);
	}

	public DAOManager getDAOManager() {
		return daoManager;
	};
	
}
