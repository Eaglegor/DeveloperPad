package com.eaglegor.devpad.dao;

import java.util.List;

import com.eaglegor.devpad.concepts.ChangeLogEntry;
import com.eaglegor.devpad.concepts.Task;

public interface ChangeLogEntryDAO extends DAO<ChangeLogEntry> {

	public List<ChangeLogEntry> findAllForTask(Task task);
	
}
