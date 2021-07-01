package jp.ac.hcs.s3a216.task;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping("/task")
	public String getTask(Principal principal,Model model) {
		
		return "task/task";
		
	}

}