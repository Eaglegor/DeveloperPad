package com.eaglegor.devpad.dao.androidsqlite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
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

		ContentValues values = new ContentValues();
		values.put("text", object.getText());
		values.put("creation_date", object.getCreationDate().getTime());
		values.put("related_resource", object.getRelatedResource() == null ? null : object.getRelatedResource().getId());
		
		if(object.getId() > 0)
		{
			database.update(TABLE_NAME, values, "_id = ?", new String[]{Integer.toString(object.getId())});
		}
		else
		{
			database.insert(TABLE_NAME, null, values);
		}
		
	}

	@Override
	public void refresh(ChangeLogEntry object) {

		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, "_id = ?", new String[]{Integer.toString(object.getId())}, null, null, DEFAULT_ORDER_BY_COLUMN, null);

		if(cursor.moveToNext()) {
			object.setText(cursor.getString(1));
			object.setCreationDate(new Date(cursor.getLong(2)));
			
			ResourceHandle resHandle = daoManager.getResourceHandleDAO().load(cursor.getInt(3));
			object.setRelatedResource(resHandle);
		}
		
	}

	@Override
	public ChangeLogEntry load(int id) {
		ChangeLogEntry changeLogEntry = null;
		
		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, "_id = ?", new String[]{Integer.toString(id)}, null, null, DEFAULT_ORDER_BY_COLUMN, null);
		
		if(cursor.moveToNext()) {
			ResourceHandle resHandle = daoManager.getResourceHandleDAO().load(cursor.getInt(3));
			
			changeLogEntry = new ChangeLogEntry(cursor.getInt(0), resHandle, cursor.getString(1), new Date(cursor.getLong(2)));	
		}
		
		return changeLogEntry;
	}

	private List<ChangeLogEntry> internalLoad(String whereClause, String...params)
	{
		List<ChangeLogEntry> changeLogEntries = new ArrayList<ChangeLogEntry>();
		
		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, whereClause, params, null, null, DEFAULT_ORDER_BY_COLUMN, null);
		while(cursor.moveToNext())
		{
			ResourceHandle resHandle = daoManager.getResourceHandleDAO().load(cursor.getInt(3));
		
			ChangeLogEntry changeLogEntry = new ChangeLogEntry(cursor.getInt(0), resHandle, cursor.getString(1), new Date(cursor.getLong(2)));
			changeLogEntries.add(changeLogEntry);
		}
		
		return changeLogEntries;
	}
	
	@Override
	public List<ChangeLogEntry> loadAll() {
		return internalLoad(null, (String[]) null);
	}

	@Override
	public List<ChangeLogEntry> findAllForTask(Task task) {
		return internalLoad("task = ?", Integer.toString(task.getId()));
	}

	@Override
	public void remove(int id) {
		database.delete(TABLE_NAME, "_id = ?", new String[]{Integer.toString(id)});
	}

}
