package com.eaglegor.devpad.dao.androidsqlite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.SparseArray;

import com.eaglegor.devpad.concepts.Task;
import com.eaglegor.devpad.concepts.TaskPriority;
import com.eaglegor.devpad.concepts.TaskStatus;
import com.eaglegor.devpad.concepts.TaskType;
import com.eaglegor.devpad.dao.DAOManager;
import com.eaglegor.devpad.dao.TaskDAO;

public class AndroidSqliteTaskDAO extends AndroidSqliteDAO<Task> implements TaskDAO {

	final private String TABLE_NAME = "Tasks";
	final private String[] DATA_COLUMNS = {"_id", "title", "type", "status", "priority", "parent", "deadline", "assignee", "estimate", "spent_time", "description"};
	final private String DEFAULT_ORDER_BY_COLUMN = "_id";

	private List<Task> hierarchicalTasksCache;
	private List<Task> plainTasksCache;
	
	private Boolean needsTasksCacheUpdate = true;
	
	public AndroidSqliteTaskDAO(SQLiteDatabase database, DAOManager daoManager) {
		super(database, daoManager);
	}

	@Override
	public void save(Task object) {
		
		ContentValues values = new ContentValues();
		values.put("title", object.getTitle());
		values.put("type", object.getType().getId());
		values.put("status", object.getStatus().getId());
		values.put("priority", object.getPriority().getId());
		values.put("parent", object.getParent() == null ? null : object.getParent().getId());
		values.put("deadline", object.getDeadline() == null ? null : object.getDeadline().getTime());
		values.put("assignee", object.getAssignee());
		values.put("estimate", object.getEstimate());
		values.put("spent_time", object.getSpentTime());
		values.put("description", object.getDescription());
		
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
	public void refresh(Task object) {
		
		Task referenceTask = load(object.getId());
		
		object.setTitle(referenceTask.getTitle());
		object.setAssignee(referenceTask.getAssignee());
		object.setChangeLog(referenceTask.getChangeLog());
		object.setChildren(referenceTask.getChildren());
		object.setDeadline(referenceTask.getDeadline());
		object.setEstimate(referenceTask.getEstimate());
		object.setParent(referenceTask.getParent());
		object.setPriority(referenceTask.getPriority());
		object.setResources(referenceTask.getResources());
		object.setSpentTime(referenceTask.getSpentTime());
		object.setStatus(referenceTask.getStatus());
		object.setType(referenceTask.getType());
		object.setDescription(referenceTask.getDescription());
		
	}

	@Override
	public Task load(int id) {
		synchronized(needsTasksCacheUpdate)
		{
			if(needsTasksCacheUpdate) updateTasksCache();
		}
		for (Task task : plainTasksCache) {
			if(task.getId() == id) return task;
		}
		return null;
	}
	
	@Override
	public List<Task> loadAll() {
		synchronized(needsTasksCacheUpdate)
		{
			if(needsTasksCacheUpdate) updateTasksCache();
		}
		return hierarchicalTasksCache;
	}

	@Override
	public void remove(int id) {
		database.delete(TABLE_NAME, "_id = ?", new String[]{Integer.toString(id)});
	}

	private void updateTasksCache()
	{
		hierarchicalTasksCache = new ArrayList<Task>();
		
		plainTasksCache = new ArrayList<Task>();
		
		SparseArray<List<Task>> parentChildren = new SparseArray<List<Task>>();
		
		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, null, null, null, null, DEFAULT_ORDER_BY_COLUMN, null);
		while(cursor.moveToNext())
		{
			TaskType type = daoManager.getTaskTypeDAO().load(cursor.getInt(2));
			TaskStatus status = daoManager.getTaskStatusDAO().load(cursor.getInt(3));
			TaskPriority priority = daoManager.getTaskPriorityDAO().load(cursor.getInt(4));
			
			Task task = new Task(cursor.getInt(0), cursor.getString(1), type, status, priority );
			
			//deadline
			if(!cursor.isNull(6))
			{
				task.setDeadline(new Date(cursor.getInt(6)));
			}
		
			//assignee
			if(!cursor.isNull(7))
			{
				task.setAssignee(cursor.getString(7));
			}
			
			//estimate
			if(!cursor.isNull(8))
			{
				task.setEstimate(cursor.getLong(8));
			}
			
			//spentTime
			if(!cursor.isNull(9))
			{
				task.setSpentTime(cursor.getLong(9));
			}
			
			//spentTime
			if(!cursor.isNull(10))
			{
				task.setDescription(cursor.getString(10));
			}
			
			task.setResources(daoManager.getResourceHandleDAO().findAllForTask(task));
			task.setChangeLog(daoManager.getChangeLogEntryDAO().findAllForTask(task));
			
			if(!cursor.isNull(5))
			{
				
				int parentId = cursor.getInt(5);
				
				boolean parentExists = false;;
				for (Task parentTask : plainTasksCache) {
					if(parentTask.getId() == parentId){
						parentTask.appendChild(task);
						parentExists = true;
						break;
					}
				}
				
				if (!parentExists) {
					if(parentChildren.indexOfKey(parentId) > 0)
					{
						parentChildren.get(parentId).add(task);
					}
					else
					{
						List<Task> deferredtasks = new ArrayList<Task>();
						deferredtasks.add(task);
						parentChildren.put(parentId, deferredtasks);
					}
				}
			
			}
			
			plainTasksCache.add(task);
		}
		
		for (int i = 0; i < parentChildren.size(); i++) {
			
			int parentId = parentChildren.keyAt(i);
			List<Task> children = parentChildren.valueAt(i);
			
			Task parent = null;
			for(Task task : plainTasksCache)
			{
				if(task.getId() == parentId)
				{
					parent = task;
					break;
				}
			}
			
			assert parent != null;
			
			for(Task child : children) {
				parent.appendChild(child);
			}
			
		}

		for (Task task : plainTasksCache) {
			if(task.getParent()==null)
			{
				hierarchicalTasksCache.add(task);
			}
		}
		
		needsTasksCacheUpdate = false;
		
	}
	
}
