package com.eaglegor.devpad.dao.androidsqlite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
	final private String[] DATA_COLUMNS = {"_id", "title", "type", "status", "priority", "parent", "deadline", "assignee", "estimate", "spent_time"};
	final private String DEFAULT_ORDER_BY_COLUMN = "_id";

	public AndroidSqliteTaskDAO(SQLiteDatabase database, DAOManager daoManager) {
		super(database, daoManager);
	}

	@Override
	public void save(Task object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void refresh(Task object) {
		// TODO Auto-generated method stub

	}

	@Override
	public Task load(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> loadAll() {
		List<Task> tasks = new ArrayList<Task>();
		
		SparseArray<List<Task>> parentChildren = new SparseArray<List<Task>>();
		
		Cursor cursor = database.query(TABLE_NAME, DATA_COLUMNS, null, null, null, null, DEFAULT_ORDER_BY_COLUMN, null);
		while(cursor.moveToNext())
		{
			TaskType type = daoManager.getTaskTypeDAO().load(cursor.getInt(2));
			TaskStatus status = daoManager.getTaskStatusDAO().load(cursor.getInt(3));
			TaskPriority priority = daoManager.getTaskPriorityDAO().load(cursor.getInt(4));
			
			Task task = new Task(cursor.getInt(0), cursor.getString(1), type, status, priority );
			
			//deadline
			if(cursor.isNull(6))
			{
				task.setDeadline(new Date(cursor.getInt(6)));
			}
		
			//assignee
			if(cursor.isNull(7))
			{
				task.setAssignee(cursor.getString(7));
			}
			
			//estimate
			if(cursor.isNull(8))
			{
				task.setEstimate(cursor.getInt(8));
			}
			
			//spentTime
			if(cursor.isNull(9))
			{
				task.setSpentTime(cursor.getInt(9));
			}
			
			int parentId = cursor.getInt(5);
			
			boolean parentExists = false;;
			for (Task parentTask : tasks) {
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
			
			tasks.add(task);
		}
		
		for (int i = 0; i < parentChildren.size(); i++) {
			
			int parentId = parentChildren.keyAt(i);
			List<Task> children = parentChildren.valueAt(i);
			
			Task parent = null;
			for(Task task : tasks)
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
		
		return tasks;
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub
		
	}

}
