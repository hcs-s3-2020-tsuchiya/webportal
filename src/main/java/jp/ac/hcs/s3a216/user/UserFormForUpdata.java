package jp.ac.hcs.s3a216.user;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.NotBlank;

/**
 * アップデート用にパスワード、ダークモード、権限のチェックを外したUserForm
 * その他の内容はUserFormに準じる
 * @author s20192004
 *
 */
public class UserFormForUpdata {
	
	/**ユーザID（メールアドレス）*/
	@NotBlank(message="{require_check}")
	@Email(message="{email_check}")
	private String user_id;
	
	
	/**パスワード*/
	private String password;
	
	/**ユーザ名*/
	@NotBlank(message="{require_check}")
	private String user_name;
	
	/**ダークモードフラグ*/
	private boolean darkmode;
	
	/**権限チェック*/
	private String role;

}
