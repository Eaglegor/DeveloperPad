package com.eaglegor.devpad.dao.androidsqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eaglegor.devpad.concepts.ResourceType;
import com.eaglegor.devpad.dao.DAOManager;
import com.eaglegor.devpad.dao.ResourceTypeDAO;

public class AndroidSqliteResourceTypeDAO extends AndroidSqliteDAO<ResourceType> implements ResourceTypeDAO {

	final private String TABLE_NAME = "ResourceTypes";
	final private String[] DATA_COLUMNS = {"_id", "title", "classname"};
	final private String DEFAULT_ORDER_BY_COLUMN = "_id";
	
	public AndroidSqliteResourceTypeDAO(SQLiteDatabase database, DAOManager daoManager) {
		super(database, daoManager);
	}

	@Override
	public void save(ResourceType object) {

		ContentValues values = new ContentValues();
		values.put("title", object.getTitle());
		values.put("classname", object.getClassname());
		
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
	public void refresh(ResourceType object) {

		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, "_id = ?", new String[]{Integer.toString(object.getId())}, null, null, DEFAULT_ORDER_BY_COLUMN, null);

		if(cursor.moveToNext()) {
			object.setTitle(cursor.getString(1));
			object.setClassname(cursor.getString(2));
		}

	}

	@Override
	public ResourceType load(int id) {
		ResourceType resourceType = null;

		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, "_id = ?", new String[]{Integer.toString(id)}, null, null, DEFAULT_ORDER_BY_COLUMN, null);

		if(cursor.moveToNext()) {
			resourceType = new ResourceType(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
		}

		return resourceType;
	}

	@Override
	public List<ResourceType> loadAll() {
		List<ResourceType> resourceTypes = new ArrayList<ResourceType>();
		
		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, null, null, null, null, DEFAULT_ORDER_BY_COLUMN, null);
		while(cursor.moveToNext())
		{
			ResourceType resourceType = new ResourceType(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
			resourceTypes.add(resourceType);
		}
		
		return resourceTypes;
	}

	@Override
	public void remove(int id) {
		database.delete(TABLE_NAME, "_id = ?", new String[]{Integer.toString(id)});
	}

}
