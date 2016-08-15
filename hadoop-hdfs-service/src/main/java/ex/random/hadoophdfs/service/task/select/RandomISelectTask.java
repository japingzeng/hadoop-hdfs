package ex.random.hadoophdfs.service.task.select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import ex.random.hadoophdfs.domain.TempTest;
import ex.random.hadoophdfs.service.task.template.TaskEntry;

public class RandomISelectTask implements ISelectTask<TaskEntry> {
	
	private static final Logger logger = LoggerFactory.getLogger(RandomISelectTask.class);
	
	protected volatile Map<String, Integer> indexMap = new HashMap<String, Integer>();
	
	@Override
	public List<TaskEntry> selectTasks(String ownSign, int taskQueueNum,
			List<String> taskQueueList, int eachFetchDataNum, String taskType)
			throws Exception {
		
		List<TaskEntry> resultList = new ArrayList<TaskEntry>();
		String queueId = null;
		try {
			for (int i = 0; i < taskQueueList.size(); i++) {
				 queueId = this.getTaskQueue(taskQueueList);
				logger.info("***[获取到queueId:" + queueId + "]***");
				
				if (null != queueId) {
					TempTest t = new TempTest();
					t.setUserName(String.valueOf(System.currentTimeMillis()));
					t.setPassWord(String.valueOf(Math.round(10000L)));
					String json = JSON.toJSONString(t);
					TaskEntry teEntry = new TaskEntry(json, queueId, taskType, t);
					resultList.add(teEntry);
				} else {
					logger.error("***[" + taskType + "没有获取到对应的queueId:" + queueId + "]***");
				}
				
			}
		} catch (Exception e) {
			logger.error("***[获取queueId：" + queueId + "对应的数据异常]***");
		}
		return resultList;
	}
	
	private String getTaskQueue(List<String> taskQueueList) {
		String queue = taskQueueList.get(this.getIndex(taskQueueList));
		return queue;
	}
	
	
	
	private int getIndex(List<String> taskQueueList) {
		int rs = 0;
		String key = taskQueueList.toString();
		if (indexMap.containsKey(key)) {
			rs = indexMap.get(key);
		}
		
		if (rs >= taskQueueList.size()) {
			rs = 0;
		}
		indexMap.put(key, rs + 1);
		
		return rs;
	}

}
