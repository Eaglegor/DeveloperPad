package com.eaglegor.devpad.view.loaders;

import java.util.List;

import com.eaglegor.devpad.concepts.Task;
import com.eaglegor.devpad.view.DevPadApplication;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class TaskListLoader extends AsyncTaskLoader< List<Task> > {

	public TaskListLoader(Context context) {
		super(context);
	}

	
	
	@Override
	protected void onReset() {
		super.onReset();
	}



	@Override
	protected void onStartLoading() {
		forceLoad();
	}


	@Override
	protected void onStopLoading() {
		super.onStopLoading();
	}



	@Override
	public List<Task> loadInBackground() {
		List<Task> tasks = ((DevPadApplication) getContext().getApplicationContext()).getDAOManager().getTaskDAO().loadAll();
		return tasks;
	}
	
}
