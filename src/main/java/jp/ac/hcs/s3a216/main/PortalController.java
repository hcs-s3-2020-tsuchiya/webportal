package jp.ac.hcs.s3a216.main;



import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.ac.hcs.s3a216.user.UserEntity;
import jp.ac.hcs.s3a216.user.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PortalController {
	
	@Autowired
	private UserService userService;

	
	@RequestMapping("/")
	public String index(Principal principal,Model model) {
	
		UserEntity userEntity = userService.selectAll();
		if(userEntity == null) {
			log.info("タスク検索:エラーが発生しました。");
		}
		
		model.addAttribute("userEntity", userEntity);
		return "index";
	}

}
