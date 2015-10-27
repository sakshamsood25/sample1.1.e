package com.worksap.stm.sample.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.worksap.stm.sample.service.spec.TaskManagerService;
import com.worksap.stm.sample.dao.impl.Task;



	
@Controller
public class TaskManagerController {
	
	TaskManagerService taskmanagerservice=new TaskManagerService();
	
	
	// page
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/addtasks")
	public String officemanagement() {
		return "task";
	 

	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/addtasks/tasks",method = RequestMethod.GET,headers="Accept=application/json")
	@ResponseBody
	public List<Task> getAllTasks() {	 
	  List<Task> tasks=taskmanagerservice.getAllTasks();
	  return tasks;
	
	 }
	 
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseBody
	 @RequestMapping(value="addtasks/tasks/archive/{taskIds}",method = RequestMethod.POST,headers="Accept=application/json")
	 public List<Task> archiveAllTasks(@PathVariable int[] taskIds) {	
		 for(int i=0;i<taskIds.length;i++){
			 taskmanagerservice.archiveTask(taskIds[i]);	
			 
		 }
	  List<Task> tasks=taskmanagerservice.getAllTasks();
	  return tasks;
	
	 }
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseBody
	 @RequestMapping(value="addtasks/tasks/{taskId}/{taskStatus}",method = RequestMethod.POST,headers="Accept=application/json")
	 public List<Task> changeTaskStatus(@PathVariable int taskId,@PathVariable String taskStatus) throws ParseException {	
		 taskmanagerservice.changeTaskStatus(taskId,taskStatus);		 
		 return taskmanagerservice.getAllTasks();
		 
	 }
	@PreAuthorize("hasAuthority('ADMIN')")
	@ResponseBody
	 @RequestMapping(value="addtasks/tasks/insert/{taskName}/{taskDesc}/{taskPriority}/{taskStatus}",method = RequestMethod.POST,headers="Accept=application/json")
	 public List<Task> addTask(@PathVariable String taskName,@PathVariable String taskDesc,@PathVariable String taskPriority,@PathVariable String taskStatus) throws ParseException {	
		Task task = new Task();
		task.setTaskName(taskName);
		task.setTaskDescription(taskDesc);
		task.setTaskPriority(taskPriority);
		task.setTaskStatus(taskStatus);
		taskmanagerservice.addTask(task);
		return taskmanagerservice.getAllTasks();
		 
	 }	 	 	 	 
			
}