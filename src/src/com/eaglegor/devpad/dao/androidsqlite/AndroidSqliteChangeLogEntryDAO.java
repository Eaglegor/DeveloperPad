package com.eaglegor.devpad.dao.androidsqlite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eaglegor.devpad.concepts.ChangeLogEntry;
import com.eaglegor.devpad.concepts.ResourceHandle;
import com.eaglegor.devpad.concepts.Task;
import com.eaglegor.devpad.dao.ChangeLogEntryDAO;
import com.eaglegor.devpad.dao.DAOManager;

public class AndroidSqliteChangeLogEntryDAO extends AndroidSqliteDAO<ChangeLogEntry> implements ChangeLogEntryDAO {
	
	final private String TABLE_NAME = "ChangeLog";
	final private String[] DATA_COLUMNS = {"_id", "text", "creation_date", "related_resource"};
	final private String DEFAULT_ORDER_BY_COLUMN = "creation_date";

	public AndroidSqliteChangeLogEntryDAO(SQLiteDatabase database, DAOManager daoManager) {
		super(database, daoManager);
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
		List<ChangeLogEntry> changeLogEntries = new ArrayList<ChangeLogEntry>();
		
		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, null, null, null, null, DEFAULT_ORDER_BY_COLUMN, null);
		while(cursor.moveToNext())
		{
			ResourceHandle resHandle = daoManager.getResourceHandleDAO().load(cursor.getInt(3));
		
			ChangeLogEntry changeLogEntry = new ChangeLogEntry(cursor.getInt(0), resHandle, cursor.getString(1), new Date(cursor.getLong(2)));
			changeLogEntries.add(changeLogEntry);
		}
		
		return changeLogEntries;
	}

	@Override
	public List<ChangeLogEntry> findAllForTask(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		
	}

}
