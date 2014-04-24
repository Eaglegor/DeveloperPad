package com.eaglegor.devpad.concepts;

import java.util.Date;
import java.util.List;

public final class Task {

	private int id;
	private String title;
	private TaskType type;
	private TaskStatus status;
	private List<Task> children = null;
	private Task parent = null;
	private Date deadline = null;
	private TaskPriority priority;
	private String assignee = null;
	private long estimate = 0;
	private long spentTime = 0;
	private List<ResourceHandle> resources = null;
	private List<ChangeLogEntry> changeLog = null;

	public Task(String title, TaskType type, TaskStatus status,	TaskPriority priority) {
		this(-1, title, type, status, priority);
	}
	
	public Task(int id, String title, TaskType type, TaskStatus status,	TaskPriority priority) {
		this.id = id;
		this.title = title;
		this.type = type;
		this.status = status;
		this.priority = priority;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TaskType getType() {
		return type;
	}

	public void setType(TaskType type) {
		this.type = type;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Task getParent() {
		return parent;
	}

	public void setParent(Task parent) {
		this.parent = parent;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public long getEstimate() {
		return estimate;
	}

	public void setEstimate(long estimate) {
		this.estimate = estimate;
	}

	public long getSpentTime() {
		return spentTime;
	}

	public void setSpentTime(long spentTime) {
		this.spentTime = spentTime;
	}

	public int getId() {
		return id;
	}

	public List<Task> getChildren() {
		return children;
	}

	public List<ResourceHandle> getResources() {
		return resources;
	}

	public List<ChangeLogEntry> getChangeLog() {
		return changeLog;
	}
	
	public void appendChild(Task child)
	{
		children.add(child);
	}
	
	public void appendResource(ResourceHandle resource)
	{
		resources.add(resource);
	}
	
	public void appendChangeLogEntry(ChangeLogEntry changeLogEntry)
	{
		changeLog.add(changeLogEntry);
	}
	
}
