package jp.ac.hcs.s3a216.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/user/list")
	public String getUserList(Principal principal,Model model) {
		
		UserEntity userEntity = userService.selectAll();
		if(userEntity == null) {
			log.info("タスク検索:エラーが発生しました。");
		}
		
		model.addAttribute("userEntity", userEntity);
		
		
		return "user/userList";
	}
	/**
	 * ユーザ登録画面（管理者用）を表示する。
	 * @param form 登録時の入力チェック用UserForm
	 * @param model
	 * @return ユーザ登録画面（管理者用）
	 */
	@GetMapping("/user/insert")
	public String getUserInsert(UserForm form,Model model) {
		return "user/insert";
	}
	/**
	 * 1件分のユーザ情報をデータベースに登録する
	 * @param form 登録するユーザ情報（パスワードは平文）
	 * @param bindingResult　データバインド実地結果
	 * @param principal　ログイン情報
	 * @param model
	 * @return　ユーザ一覧画面
	 */

	@PostMapping("/user/insert")
	public String getUserInsert(@ModelAttribute @Validated UserForm form,
			BindingResult bindingResult,
			Principal principal,
			Model model) {
		
		
		
		
		//入力チェックに引っかかった場合、前の画面に戻る
		if(bindingResult.hasErrors()) {
			return getUserInsert(form,model);
		}
		
		userService.insertOne(form);	
		return getUserList(principal,model);
	}

	/**
	 * ユーザ詳細画面を表示する。
	 * @param user_id　検索するユーザID
	 * @param principal　ログイン情報
	 * @param model
	 * @return　ユーザ詳細画面
	 */
	@GetMapping("/user/detail/{id}")
	public String getUserDetail(@PathVariable("id") String user_id,
			Principal principal,
			Model model) {
		System.out.println(user_id);
		UserData data = userService.selectOne(user_id);
		
		
		
		
		 model.addAttribute("userData",data);
		 return "user/detail";
	}
	
	
	
	@PostMapping("/user/delete")
	public String deleteTask(@RequestParam(name="user_id") String user_id, Principal principal,Model model) {
		
		userService.deleteOne(user_id);
		
		return getUserList(principal,model);
	}
	
	
	
	

}
