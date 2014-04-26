package com.eaglegor.devpad.dao.androidsqlite;

import java.util.ArrayList;
import java.util.List;

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
		// TODO Auto-generated method stub
		
	}

}
