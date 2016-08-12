package ex.random.hadoophdfs.service.task.template;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomTask extends AbstractIntervalTask {
	private static Logger logger = LoggerFactory.getLogger(RandomTask.class);
	private static final long Interval = 60; 
	
	
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
			
		} catch (Exception e) {
			// 处理任务异常
		}
	}
}
