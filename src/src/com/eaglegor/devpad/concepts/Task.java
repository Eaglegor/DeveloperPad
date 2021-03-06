package com.eaglegor.devpad.concepts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class Task {

	private int id;
	private String title;
	private String description;
	private TaskType type;
	private TaskStatus status;
	private List<Task> children = null;
	private Task parent = null;
	private Date deadline = null;
	private TaskPriority priority;
	private String assignee = null;
	private Long estimate = null;
	private Long spentTime = null;
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

	public void setId(int id) {
		if(this.id < 0) this.id = id;
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

	public Long getEstimate() {
		return estimate;
	}

	public void setEstimate(Long estimate) {
		this.estimate = estimate;
	}

	public Long getSpentTime() {
		return spentTime;
	}

	public void setSpentTime(Long spentTime) {
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

	public void setChildren(List<Task> children) {
		this.children = children;
	}

	public void setResources(List<ResourceHandle> resources) {
		this.resources = resources;
	}

	public void setChangeLog(List<ChangeLogEntry> changeLog) {
		this.changeLog = changeLog;
	}

	
	public void appendChild(Task child)
	{
		if(children == null)
		{
			children = new ArrayList<Task>();
		}
		children.add(child);
		child.setParent(this);
	}
	
	public void appendResource(ResourceHandle resource)
	{
		if(resources == null)
		{
			resources = new ArrayList<ResourceHandle>();
		}
		resources.add(resource);
	}
	
	public void appendChangeLogEntry(ChangeLogEntry changeLogEntry)
	{
		if(changeLog == null)
		{
			changeLog = new ArrayList<ChangeLogEntry>();
		}
		changeLog.add(changeLogEntry);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
