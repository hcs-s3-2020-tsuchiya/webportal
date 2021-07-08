package jp.ac.hcs.s3a216.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user/list")
	public String getZipCode(Principal principal,Model model) {
		
		UserEntity userEntity = userService.selectAll();
		if(userEntity == null) {
			log.info("タスク検索:エラーが発生しました。");
		}
		
		model.addAttribute("userEntity", userEntity);
		
		
		return "user/userList";
	}
}
	
	/**
	 * ユーザ登録画面（管理者用）を表示する。
	 * @param form 登録時の入力チェック用UserForm
	 * @param model
	 * @return ユーザ登録画面（管理者用）
	 */
//	@PostMapping("/user/insert")
//	public String getUserInsert(@ModelAttribute @Validated UserForm form,
//			BindingResult bindingResult,
//			Principal principal,
//			Model model) {
//		
//		//入力チェックに引っかかった場合、前の画面に戻る
//		if(bindingResult.hasErrors()) {
//			return getUserInsert(form,model);
//		}
//		
//		return getUserList(model);
//	}
//	
//	
//
//}
