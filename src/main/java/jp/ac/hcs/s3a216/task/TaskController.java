package jp.ac.hcs.s3a216.task;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;
	
/*
	@PostMapping("/task/insert")
	public String insertTask(@RequestParam("comment") String comment,@RequestParam("limitday")String limitday,
			Principal principal,Model model) {
		
		log.info("[" + principal.getName()+"]タスク追加:"+ "コメント"+ comment + "期限日:" + limitday);
		boolean isSuccess = taskService.insert(principal.getName(),comment,limitday);
		
		if(isSuccess) {
			log.info("成功");
		}else {
			log.info("失敗");
		}
		
		return getTaskList(principal,model);
	}
	*/
	

	
	@PostMapping("/task/tasklist")
	public String getTaskList(Principal principal,Model model) {
		
		log.info("[" + principal.getName()+"]タスク検索:" + principal.getName());
		
		
		TaskEntity taskEntity = taskService.selectAll(principal.getName());
		if(taskEntity == null) {
			log.info("[" + principal.getName()+"]タスク検索:エラーが発生しました。");
		}
		model.addAttribute("taskEntity", taskEntity);
		
		
		return "task/task";
		
		
	
	}
}
	