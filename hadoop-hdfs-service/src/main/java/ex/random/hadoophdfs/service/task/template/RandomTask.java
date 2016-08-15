package ex.random.hadoophdfs.service.task.template;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import ex.random.hadoophdfs.domain.TempTest;
import ex.random.hadoophdfs.repository.mybatis.TempTestDao;

public class RandomTask extends AbstractIntervalTask {
	private static Logger logger = LoggerFactory.getLogger(RandomTask.class);
	private static final long Interval = 60; 
	
	@Resource
	private TempTestDao tempTestDao;
	
	@Override
	public long getInterval() throws Exception {
		return Interval * 60 * 1000;
	}

	@Override
	public boolean excuteTask(Object[] tasks, String ownSign) {
		List<TaskEntry> handingList = asList(tasks);
		logger.info("[***共有：" + handingList.size()+ "条任务待处理***]");
		for (TaskEntry task : handingList) {
			handleSingleTask(task, ownSign);
		}
		logger.info("[***执行：" + handingList.size()+ "条任务完毕***]");
		return true;
	}
	
	private List<TaskEntry> asList(Object[] tasks) {
		List<TaskEntry> list = new ArrayList<TaskEntry>();
		for (int i = 0; i < tasks.length; i++) {
			list.add((TaskEntry)tasks[i]);
		}
		return list;
	}
	
	protected void handleSingleTask(TaskEntry task, String ownSign) {
		if (null == task) {
			logger.error("任务为空");
			return;
		}
		//任务的处理
		try {
			String json = task.getJson();
			if (null != json) {
				TempTest tempTest = JSON.parseObject(json, TempTest.class);
				tempTestDao.save(tempTest);
			}
		} catch (Exception e) {
			// 处理任务异常
			logger.equals("插入数据异常：" + ownSign + "[" + JSON.toJSONString(task) + "]");
		}
	}
	
	
	public TempTestDao getTempTestDao() {
		return tempTestDao;
	}

	public void setTempTestDao(TempTestDao tempTestDao) {
		this.tempTestDao = tempTestDao;
	}
	
}
