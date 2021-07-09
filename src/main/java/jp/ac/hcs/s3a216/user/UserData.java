package jp.ac.hcs.s3a216.user;

import lombok.Data;

/**
 * 1件分のユーザ情報
 * 各項目のデータ使用も併せて管理する
 *
 */

@Data
public class UserData {
	
	/**
	 * ユーザID(メールアドレス)
	 * 主キー、必須項目、メールアドレス形式
	 */
	private String user_id;
	
	/**
	 * パスワード
	 * 必須入力、長さ4から１００桁まで、半角英数字のみ
	 */
	private String password;
	
	/**
	 * アカウント有効性
	 * -有効 : true
	 * -無効 : false
	 * ユーザ作成時はtrue固定
	 */
	private boolean enabled;
	
	/**
	 * ユーザ名
	 * 必須入力
	 */
	private String user_name;
	
	/**
	 * ダークモードフラグ
	 * - ON : true
	 * - OFF : false
	 * ユーザ作成時はfalse固定
	 */
	private boolean darkmode;
	
	/**
	 * 権限
	 * - 管理 : "ROLE_ADMIN"
	 * - 一般 : "ROLE_GENERAL"
	 * 必須入力
	 */
	private String role;
	

}
