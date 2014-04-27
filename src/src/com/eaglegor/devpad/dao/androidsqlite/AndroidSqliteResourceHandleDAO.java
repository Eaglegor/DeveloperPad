package com.eaglegor.devpad.dao.androidsqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
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

		ContentValues values = new ContentValues();
		values.put("title", object.getTitle());
		values.put("uri", object.getUri());
		values.put("type", object.getType().getId());
		
		if(object.getId() > 0)
		{
			database.update(TABLE_NAME, values, "_id = ?", new String[]{Integer.toString(object.getId())});
		}
		else
		{
			object.setId((int) database.insert(TABLE_NAME, null, values));
		}

	}

	@Override
	public void refresh(ResourceHandle object) {

		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, "_id = ?", new String[]{Integer.toString(object.getId())}, null, null, DEFAULT_ORDER_BY_COLUMN, null);

		if(cursor.moveToNext()) {
			object.setTitle(cursor.getString(1));
			object.setUri(cursor.getString(2));
			ResourceType resType = daoManager.getResourceTypeDAO().load(cursor.getInt(3));
			object.setType(resType);
		}

	}

	@Override
	public ResourceHandle load(int id) {
		ResourceHandle resourceHandle = null;

		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, "_id = ?", new String[]{Integer.toString(id)}, null, null, DEFAULT_ORDER_BY_COLUMN, null);
		
		if(cursor.moveToNext()) {
			ResourceType resType = daoManager.getResourceTypeDAO().load(cursor.getInt(3));
			
			resourceHandle = new ResourceHandle(cursor.getInt(0), cursor.getString(1), cursor.getString(2), resType);
		}
		
		return resourceHandle;
	}

	public List<ResourceHandle> internalLoad(String whereClause, String... params)
	{
		List<ResourceHandle> changeLogEntries = new ArrayList<ResourceHandle>();
		
		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, whereClause, params, null, null, DEFAULT_ORDER_BY_COLUMN, null);
		while(cursor.moveToNext())
		{
			ResourceType resType = daoManager.getResourceTypeDAO().load(cursor.getInt(3));
		
			ResourceHandle changeLogEntry = new ResourceHandle(cursor.getInt(0), cursor.getString(1), cursor.getString(2), resType);
			changeLogEntries.add(changeLogEntry);
		}
		
		return changeLogEntries;
	}
	
	@Override
	public List<ResourceHandle> loadAll() {
		return internalLoad(null, (String[])null);
	}

	@Override
	public List<ResourceHandle> findAllForTask(Task task) {
		List<ResourceHandle> changeLogEntries = new ArrayList<ResourceHandle>();
		
		Cursor cursor = database.rawQuery("SELECT rh._id, title, uri, type FROM ResourceHandles rh LEFT JOIN TaskResources tr ON rh._id = tr.resource WHERE tr.task = ?", new String[]{Integer.toString(task.getId())});
		while(cursor.moveToNext())
		{
			ResourceType resType = daoManager.getResourceTypeDAO().load(cursor.getInt(3));
		
			ResourceHandle changeLogEntry = new ResourceHandle(cursor.getInt(0), cursor.getString(1), cursor.getString(2), resType);
			changeLogEntries.add(changeLogEntry);
		}
		
		return changeLogEntries;
	}

	@Override
	public void remove(int id) {
		database.delete(TABLE_NAME, "_id = ?", new String[]{Integer.toString(id)});
	}

}
