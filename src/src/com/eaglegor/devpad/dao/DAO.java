package com.eaglegor.devpad.dao;

import java.util.List;

public interface DAO<T> {
	
	public void save(T object);
	public void refresh(T object);
	public T load(int id);
	public List<T> loadAll();
	
}
