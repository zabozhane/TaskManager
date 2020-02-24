package com.codejava;

import com.codejava.domain.Task;
import com.codejava.repos.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TaskService {
	@Autowired
	private TaskRepository repo;
	
	public List<Task> listAll(){
		
		return repo.findAll();
	}
	
	public void save(Task task) {
		repo.save(task);
	}
	
	public Task get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
