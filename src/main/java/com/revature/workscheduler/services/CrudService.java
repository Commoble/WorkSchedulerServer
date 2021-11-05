package com.revature.workscheduler.services;

import com.revature.workscheduler.models.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface CrudService<T, KEY, REPO extends CrudRepository<T, KEY>>
{
	public REPO getRepo();

	default T add(T value)
	{
		return this.getRepo().save(value);
	}

	default T get(KEY id)
	{
		return this.getRepo().findById(id).orElse(null);
	}

	default List<T> getAll()
	{
		List<T> list = new ArrayList<>();
		this.getRepo().findAll().forEach(list::add);
		return list;
	}

	default T update(T newData)
	{
		return this.getRepo().save(newData);
	}

	default boolean delete(KEY id)
	{
		try
		{
			this.getRepo().deleteById(id);
			return true;
		}
		catch(IllegalArgumentException e)
		{
			return false;
		}
	}
}
