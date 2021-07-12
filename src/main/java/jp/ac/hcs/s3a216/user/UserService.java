package jp.ac.hcs.s3a216.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Transactional
@Service
public class UserService {
	
	
	@Autowired
	UserRepository userRepository;
	
	/*
	 *指定したユーザIDのタスク情報を全権取得する。 
	 */
	
	public UserEntity selectAll() {
		UserEntity userEntity;
		try {
			userEntity = userRepository.selectAll();
		}catch(DataAccessException e) {
			e.printStackTrace();
			userEntity = null;
		}
		return userEntity;
	}
	
	/*
	 *指定したユーザIDのタスク情報から消去する。 
	 */
	
	public boolean deleteOne(String user_id) {
		
		int rowNumber;
		try {
			rowNumber = userRepository.deleteOne(user_id);
		}catch(DataAccessException e) {
			e.printStackTrace();
			rowNumber = 0;
		}
		return rowNumber > 0;
	}
	
	
	
	
	/*
	 *指定した1つのユーザIDを取得する。 
	 */
	public UserData selectOne(String user_id) {
		UserData data;
		try {
			data = userRepository.selectOne(user_id);
		}catch(DataAccessException e) {
			e.printStackTrace();
			data = null;
		}
		return data;
	}
	
/**
 * ユーザ情報を1件追加する
 * @param userData 追加するユーザ情報（パスワードは平文）
 * @return　処理結果（成功:true,失敗:false）
 */
	public boolean insertOne(UserForm userForm) {
		
		UserData userData = refillData(userForm);
		
		int rowNumber;
		try {
			rowNumber = userRepository.insertOne(userData);
		}catch(DataAccessException e) {
			e.printStackTrace();
			rowNumber = 0;
		}
		return rowNumber > 0;
	}
	
	/**
	 * 入力項目をUserDataへ変換する
	 * (このメソッドは入力チェックを実地したうえで呼び出すこと)
	 * @param form 入力データ
	 * @pram UserData
	 */
	
	UserData refillData(UserForm form) {
		UserData data = new UserData();
		data.setUser_id(form.getUser_id());
		data.setPassword(form.getPassword());
		data.setUser_name(form.getUser_name());
		data.setDarkmode(form.isDarkmode());
		data.setRole(form.getRole());
		//初期値は有効とする
		data.setEnabled(true);
		return data;
	}

}
