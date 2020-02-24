package com.codejava.controller;

import java.util.List;

import com.codejava.TaskService;
import com.codejava.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@Autowired
	private TaskService service;
	
	@RequestMapping("/main")
	public String viewHomePage(Model model) {
		List<Task> listTasks = service.listAll();
		model.addAttribute("listTasks", listTasks);
		return "main";
	}
	
	@RequestMapping("/new")
	public String showNewTaskFrom(Model model) {
		Task task = new Task();
		model.addAttribute("task", task);
		
		return "new_task";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("task") Task task) {
		service.save(task);
		
		return"redirect:/main";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditTaskFrom(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_task");
		
		Task task = service.get(id);
		mav.addObject("task",task);
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteTask(@PathVariable(name = "id") Long id) {
		service.delete(id);
		return "redirect:/main";
	}
}
