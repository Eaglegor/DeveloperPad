package com.eaglegor.devpad.view.adapters;

import java.util.List;

import com.eaglegor.developerpad.R;
import com.eaglegor.devpad.concepts.Task;
import com.eaglegor.devpad.view.DevPadApplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

public class TaskListAdapter extends ArrayAdapter<Task> {

	public TaskListAdapter(Context context, int resource) {
		super(context, resource);
		
		//setData(((DevPadApplication)context.getApplicationContext()).getDAOManager().getTaskDAO().loadAll());
		
	}

	private static int recursionLevel = 0;
	public void setData(List<Task> newTasks)
	{
		
		this.clear();
		recursionLevel = 0;
		addData(newTasks);
	}
	
	public void addData(List<Task> newTasks)
	{
		for (Task task : newTasks) {
			for(int i = 0; i < recursionLevel; ++i) task.setTitle("*" + task.getTitle());
			add(task);
			if(task.getChildren()!=null && !task.getChildren().isEmpty())
			{
				++recursionLevel;
				addData(task.getChildren());
			}
		}
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = ((Activity)getContext()).getLayoutInflater();
		if(convertView == null)
		{
			convertView = inflater.inflate(R.layout.item_task_list, parent, false);
		}
		
		LinearLayout llview = (LinearLayout) convertView;
		CheckedTextView checkedTextView = (CheckedTextView) llview.findViewById(R.id.taskListItemText);
		Task task = getItem(position);
		checkedTextView.setChecked(task.getStatus().getTitle().equals("Completed"));
		checkedTextView.setText(task.getTitle() + "(" + task.getStatus().getTitle() + "," + task.getPriority().getTitle() + "," + task.getType().getTitle() + ")" );
		
		return convertView;
		
	}
	
	
	
}
