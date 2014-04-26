package com.eaglegor.devpad.dao.androidsqlite;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eaglegor.devpad.concepts.ResourceHandle;
import com.eaglegor.devpad.concepts.ResourceType;
import com.eaglegor.devpad.concepts.Task;
import com.eaglegor.devpad.dao.DAOManager;
import com.eaglegor.devpad.dao.ResourceHandleDAO;

public class AndroidSqliteResourceHandleDAO extends AndroidSqliteDAO<ResourceHandle> implements ResourceHandleDAO {

	final private String TABLE_NAME = "ResourceHandles";
	final private String[] DATA_COLUMNS = {"_id", "title", "uri", "type"};
	final private String DEFAULT_ORDER_BY_COLUMN = "_id";
	
	public AndroidSqliteResourceHandleDAO(SQLiteDatabase database,DAOManager daoManager) {
		super(database, daoManager);
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
		List<ResourceHandle> changeLogEntries = new ArrayList<ResourceHandle>();
		
		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, null, null, null, null, DEFAULT_ORDER_BY_COLUMN, null);
		while(cursor.moveToNext())
		{
			ResourceType resType = daoManager.getResourceTypeDAO().load(cursor.getInt(3));
		
			ResourceHandle changeLogEntry = new ResourceHandle(cursor.getInt(0), cursor.getString(1), cursor.getString(2), resType);
			changeLogEntries.add(changeLogEntry);
		}
		
		return changeLogEntries;
	}

	@Override
	public List<ResourceHandle> findAllForTask(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		
	}

}
