package com.eaglegor.devpad.view.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.eaglegor.developerpad.R;
import com.eaglegor.devpad.concepts.Task;
import com.eaglegor.devpad.view.adapters.TaskListAdapter;
import com.eaglegor.devpad.view.loaders.TaskListLoader;


public class TaskListFragment extends Fragment implements LoaderCallbacks<List<Task>> {

	private static final int TASK_LIST_LOADER_ID = 1;
	private TaskListAdapter taskListAdapter = null;
	
	public TaskListFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_task_list,
				container, false);
		
		taskListAdapter = new TaskListAdapter(getActivity(), R.layout.item_task_list);
		
		ListView list = (ListView) rootView.findViewById(R.id.taskListView);
		list.setAdapter(taskListAdapter);
		
		getLoaderManager().initLoader(TASK_LIST_LOADER_ID, null, this);
		
		return rootView;
	}

	@Override
	public Loader<List<Task>> onCreateLoader(int id, Bundle args) {
		TaskListLoader loader = new TaskListLoader(getActivity());
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<List<Task>> loader, List<Task> newTasks) {
		taskListAdapter.setData(newTasks);
	}

	@Override
	public void onLoaderReset(Loader<List<Task>> arg0) {
		taskListAdapter.clear();
	}

}