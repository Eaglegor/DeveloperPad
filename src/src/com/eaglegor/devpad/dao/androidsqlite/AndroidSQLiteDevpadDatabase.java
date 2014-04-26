package com.eaglegor.devpad.dao.androidsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AndroidSQLiteDevpadDatabase extends SQLiteOpenHelper {

	public AndroidSQLiteDevpadDatabase(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		createTables(db);
		
		fillFactoryData(db);
	
	}

	private void createTables(SQLiteDatabase db) {
		createTableTasks(db);
		createTableChangeLog(db);
		createTableTaskResources(db);
		createTableResourceTypes(db);
		createTableResourceHandles(db);
		createTableTaskPriorities(db);
		createTableTaskStatuses(db);
		createTableTaskTypes(db);
	}

	private void createTableTaskTypes(SQLiteDatabase db) {
		
		db.execSQL(
				"CREATE TABLE IF NOT EXISTS `TaskTypes` (\r\n" + 
				"  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\r\n" + 
				"  `title` TEXT NOT NULL,\r\n" + 
				"  `color_code` TEXT DEFAULT NULL)"
				);
		
		db.execSQL("CREATE UNIQUE INDEX `TaskTypes_title_UNIQUE` ON `TaskTypes` (`title` ASC)");
	}

	private void createTableTaskStatuses(SQLiteDatabase db) {
		db.execSQL(
				"CREATE TABLE IF NOT EXISTS `TaskStatuses` (\r\n" + 
				"  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\r\n" + 
				"  `title` TEXT NOT NULL,\r\n" + 
				"  `color_code` TEXT DEFAULT NULL)"
				);
		
		db.execSQL("CREATE UNIQUE INDEX `TaskStatuses_title_UNIQUE` ON `TaskStatuses` (`title` ASC)");
	}

	private void createTableTaskPriorities(SQLiteDatabase db) {
		db.execSQL(
				"CREATE TABLE IF NOT EXISTS `TaskPriorities` (\r\n" + 
				"  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\r\n" + 
				"  `value` INTEGER NOT NULL,\r\n" + 
				"  `title` TEXT NOT NULL,\r\n" + 
				"  `color_code` TEXT DEFAULT NULL)"
				);
		
		db.execSQL("CREATE UNIQUE INDEX `TaskPriorities_value_UNIQUE` ON `TaskPriorities` (`value` ASC, `title` ASC)");
	}

	private void createTableResourceHandles(SQLiteDatabase db) {

		db.execSQL(
				"CREATE TABLE IF NOT EXISTS `ResourceHandles` (\r\n" + 
				"  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\r\n" + 
				"  `type` INTEGER NOT NULL,\r\n" + 
				"  `title` TEXT NOT NULL,\r\n" + 
				"  `uri` TEXT NOT NULL,\r\n" + 
				"  CONSTRAINT `Resource_Type`\r\n" + 
				"    FOREIGN KEY (`type`)\r\n" + 
				"    REFERENCES `ResourceTypes` (`_id`)\r\n" + 
				"    ON DELETE NO ACTION\r\n" + 
				"    ON UPDATE NO ACTION)"
				);
		
		db.execSQL("CREATE UNIQUE INDEX `title_UNIQUE` ON `ResourceHandles` (`title` ASC)");
		db.execSQL("CREATE UNIQUE INDEX `uri_UNIQUE` ON `ResourceHandles` (`uri` ASC)");
		db.execSQL("CREATE INDEX `Resource_Type_idx` ON `ResourceHandles` (`type` ASC)");
		
	}

	private void createTableResourceTypes(SQLiteDatabase db) {
		db.execSQL(
				"CREATE TABLE IF NOT EXISTS `ResourceTypes` (\r\n" + 
				"  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\r\n" + 
				"  `title` INTEGER NOT NULL,\r\n" + 
				"  `classname` TEXT NOT NULL)"
				);
		
		db.execSQL("CREATE UNIQUE INDEX `ResourceTypes_classname_UNIQUE` ON `ResourceTypes` (`classname` ASC)");
		db.execSQL("CREATE UNIQUE INDEX `ResourceTypes_title_UNIQUE` ON `ResourceTypes` (`title` ASC)");
	}

	private void createTableTaskResources(SQLiteDatabase db) {
		db.execSQL(
				"CREATE TABLE IF NOT EXISTS `TaskResources` (\r\n" + 
				"  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\r\n" + 
				"  `task` INTEGER NOT NULL,\r\n" + 
				"  `resource` INTEGER NOT NULL,\r\n" + 
				"  CONSTRAINT `TaskResource_Task`\r\n" + 
				"    FOREIGN KEY (`task`)\r\n" + 
				"    REFERENCES `Tasks` (`_id`)\r\n" + 
				"    ON DELETE NO ACTION\r\n" + 
				"    ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `TaskResource_Resource`\r\n" + 
				"    FOREIGN KEY (`resource`)\r\n" + 
				"    REFERENCES `ResourceHandles` (`_id`)\r\n" + 
				"    ON DELETE NO ACTION\r\n" + 
				"    ON UPDATE NO ACTION)"
				);
		
		db.execSQL("CREATE INDEX `TaskResource_Task_idx` ON `TaskResources` (`task` ASC)");
		db.execSQL("CREATE INDEX `TaskResource_Resource_idx` ON `TaskResources` (`resource` ASC)");
		db.execSQL("CREATE UNIQUE INDEX `TaskResource_unique` ON `TaskResources` (`task` ASC, `resource` ASC)");
	}

	private void createTableChangeLog(SQLiteDatabase db) {
		db.execSQL(
				"CREATE TABLE IF NOT EXISTS `ChangeLog` (\r\n" + 
				"  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\r\n" + 
				"  `task` INTEGER NOT NULL,\r\n" + 
				"  `related_resource` INTEGER,\r\n" + 
				"  `text` TEXT NOT NULL,\r\n" + 
				"  `creation_date` INTEGER,\r\n" + 
				"  CONSTRAINT `ChangeLog_Task`\r\n" + 
				"    FOREIGN KEY (`task`)\r\n" + 
				"    REFERENCES `Tasks` (`_id`)\r\n" + 
				"    ON DELETE NO ACTION\r\n" + 
				"    ON UPDATE NO ACTION)"
				);
		
		db.execSQL("CREATE INDEX `ChangeLog_Task_idx` ON `ChangeLog` (`task` ASC)");
	}

	private void createTableTasks(SQLiteDatabase db) {
		db.execSQL(
				"CREATE TABLE IF NOT EXISTS `Tasks` (\r\n" + 
				"  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\r\n" + 
				"  `title` TEXT NOT NULL,\r\n" + 
				"  `type` INTEGER NOT NULL,\r\n" + 
				"  `status` INTEGER NOT NULL,\r\n" + 
				"  `parent` INTEGER DEFAULT NULL,\r\n" + 
				"  `deadline` INTEGER DEFAULT NULL,\r\n" + 
				"  `priority` INTEGER NOT NULL,\r\n" + 
				"  `assignee` TEXT DEFAULT NULL,\r\n" + 
				"  `estimate` INTEGER DEFAULT NULL,\r\n" + 
				"  `spent_time` INTEGER DEFAULT NULL,\r\n" + 
				"  CONSTRAINT `Task_Type`\r\n" + 
				"    FOREIGN KEY (`type`)\r\n" + 
				"    REFERENCES `TaskTypes` (`_id`)\r\n" + 
				"    ON DELETE NO ACTION\r\n" + 
				"    ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `Task_Status`\r\n" + 
				"    FOREIGN KEY (`status`)\r\n" + 
				"    REFERENCES `TaskStatuses` (`_id`)\r\n" + 
				"    ON DELETE NO ACTION\r\n" + 
				"    ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `Task_Parent`\r\n" + 
				"    FOREIGN KEY (`parent`)\r\n" + 
				"    REFERENCES `Tasks` (`_id`)\r\n" + 
				"    ON DELETE NO ACTION\r\n" + 
				"    ON UPDATE NO ACTION,\r\n" + 
				"  CONSTRAINT `Task_Priority`\r\n" + 
				"    FOREIGN KEY (`priority`)\r\n" + 
				"    REFERENCES `TaskPriorities` (`_id`)\r\n" + 
				"    ON DELETE NO ACTION\r\n" + 
				"    ON UPDATE NO ACTION)"
				);
		
		db.execSQL("CREATE INDEX `Tasks_Task_Type_idx` ON `Tasks` (`type` ASC)");
		db.execSQL("CREATE INDEX `Tasks_Task_Status_idx` ON `Tasks` (`status` ASC)");
		db.execSQL("CREATE INDEX `Tasks_Task_Parent_idx` ON `Tasks` (`parent` ASC)");
		db.execSQL("CREATE INDEX `Tasks_Task_Priority_idx` ON `Tasks` (`priority` ASC)");
	}

	private void fillDemoData(SQLiteDatabase db)
	{
		db.execSQL("INSERT INTO `Tasks` (`_id`, `title`, `type`, `status`, `parent`, `deadline`, `priority`, `assignee`, `estimate`, `spent_time`) VALUES (NULL, 'Main task', 1, 1, NULL, NULL, 1, NULL, NULL, NULL)");
		db.execSQL("INSERT INTO `Tasks` (`_id`, `title`, `type`, `status`, `parent`, `deadline`, `priority`, `assignee`, `estimate`, `spent_time`) VALUES (NULL, 'Subtask', 0, 1, 0, NULL, 1, 'Someone', 36000000, 18000000)");
		db.execSQL("INSERT INTO `ChangeLog` (`_id`, `task`, `related_resource`, `text`, `creation_date`) VALUES (NULL, 0, NULL, 'Task created', '1398544268632')");
		db.execSQL("INSERT INTO `ChangeLog` (`_id`, `task`, `related_resource`, `text`, `creation_date`) VALUES (NULL, 1, NULL, 'Task created', '1398544268632')");
	}
	
	private void fillFactoryData(SQLiteDatabase db) {
		fillTableResourceTypes(db);
		fillTableTaskStatuses(db);
		fillTableTaskPriorities(db);
		fillTableTaskTypes(db);
		
		fillDemoData(db); // TODO - kill this method in production version
		
	}

	private void fillTableTaskTypes(SQLiteDatabase db) {
		// Task
		ContentValues values = new ContentValues();
		values.put("title", "Task");
		values.put("color_code", (String)null);
		db.insert("TaskTypes", null, values);
		
		// Milestone
		values.clear();
		values.put("title", "Milestone");
		values.put("color_code", (String)null);
		db.insert("TaskTypes", null, values);
	}

	private void fillTableTaskPriorities(SQLiteDatabase db) {
		// HIGH
		ContentValues values = new ContentValues();
		values.put("title", "High");
		values.put("color_code", "#FF0000");
		values.put("value", 0);
		db.insert("TaskPriorities", null, values);
		
		// NORMAL
		values.clear();
		values.put("title", "Normal");
		values.put("color_code", "#0000FF");
		values.put("value", 1);
		db.insert("TaskPriorities", null, values);
		
		// LOW
		values.clear();
		values.put("title", "Low");
		values.put("color_code", "#00FF00");
		values.put("value", 2);
		db.insert("TaskPriorities", null, values);
	}

	private void fillTableTaskStatuses(SQLiteDatabase db) {
		// Completed
		ContentValues values = new ContentValues();
		values.put("title", "Completed");
		values.put("color_code", (String) null);
		db.insert("TaskStatuses", null, values);
		
		// Not completed
		values.clear();
		values.put("title", "Not completed");
		values.put("color_code", (String) null);
		db.insert("TaskStatuses", null, values);
	}

	private void fillTableResourceTypes(SQLiteDatabase db) {
		// Stub
		ContentValues values = new ContentValues();
		values.put("title", "StubResource");
		values.put("classname", "StubResourceAdapter");
		db.insert("ResourceTypes", null, values);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
