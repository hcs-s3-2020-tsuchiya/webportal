package jp.ac.hcs.s3a216.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Service
public class TaskService {
	@Autowired
	TaskRepository taskRepository;
	
	
	/**
	 * 指定した天気に紐づく天気予報情報を取得する。
	 * @param  userId ユーザID
	 * @return TaskEntity
	 */
	public TaskEntity selectAll(String userId) {
		TaskEntity taskEntity;
		try {
				taskEntity = taskRepository.selectAll(userId);
		}catch(DataAccessException e){
			e.printStackTrace();
			taskEntity = null;
		}
		return taskEntity;
	}
}