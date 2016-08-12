package ex.random.hadoophdfs.service.task.template;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.taobao.pamirs.schedule.IScheduleTaskDealMulti;
import com.taobao.pamirs.schedule.TBScheduleManagerFactory;

import ex.random.hadoophdfs.service.task.select.ISelectTask;
/**
 * 
 * @ClassName: AbstractIntervalTask 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年8月12日 下午8:35:36 
 *
 */
public abstract class AbstractIntervalTask implements IScheduleTaskDealMulti<TaskEntry>, InitializingBean {
	private static Logger logger = LoggerFactory.getLogger(AbstractIntervalTask.class);
	protected ISelectTask<TaskEntry> selectTask;
	
	protected String taskType;
	protected String ownSign;
	protected TBScheduleManagerFactory scheduleManagerFactory;
	
	protected final boolean SUCCESS = true;
	protected final boolean FAULT = false;
	private volatile boolean  _init = false;
	
	private Date lastRunTime = null;
	private boolean isRun = false;

	
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if (!_init) {
			synchronized (this) {
				this.init();
				_init = true;
			}
		}
		
	}
	
	protected void init() {
		if (scheduleManagerFactory == null) {
			throw new RuntimeException("TBScheduleManagerFactory为空...");
		}
		if (taskType == null) {
			throw new RuntimeException("TaskType为空");
		}
		if (ownSign == null) {
			throw new RuntimeException("OwnSign为空");
		}

		try {
			if (this.getInterval() <= 1000L) {
				throw new RuntimeException("时间间隔太小");
			}
		} catch (Exception e1) {
			throw new RuntimeException("获取时间间隔异常");
		}

		try {
			scheduleManagerFactory.createTBScheduleManager(taskType, ownSign);
		} catch (Exception e) {
			logger.error("启动任务Worker失败..[taskType:" + taskType + ",ownSign: + " + ownSign + "]", e);
		}
	}

	@Override
	public boolean execute(Object[] tasks, String ownSign) throws Exception {
		try {
			if (null == tasks || tasks.length <= 0) {
				return true;
			}
			return excuteTask(tasks, ownSign);
		} catch (Exception e) {
		} finally {
			isRun = false;
		}
		
		return SUCCESS;
	}
	
	@Override
	public List<TaskEntry> selectTasks(String ownSign, int taskQueueNum,
			List<String> taskQueueList, int eachFetchDataNum) throws Exception {
		List<TaskEntry> resultList = null;
		if (isCanExcute()) {
			try {
				logger.info("[***执行任务查询[taskType:" + this.taskType + ",taskQueueNum: " + taskQueueNum + "***]");
				resultList = selectTask.selectTasks(ownSign, taskQueueNum, taskQueueList, eachFetchDataNum, ownSign);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			
			logger.info("[***获取到任务:" + resultList.size() + "***]");
		}
		
		
		return resultList;
	}

	/**
	 * 
	 * @return
	 * @Description: TODO(判断是否可以执行) 
	 * @author japing 
	 * @throws Exception 
	 * @date 2016年8月12日 下午5:43:02
	 */
	private boolean isCanExcute() throws Exception {
		synchronized (this) {
			if (isRun) {
				logger.info("[***有任务正在执行***]");
			}
			
			if (null == lastRunTime) {
				logger.info("[***可以执行***]");
				isRun = true;
				logger.info("[***设置上一次执行时间：" + new Date().toString() + "***]");
				
				//睡眠20秒
				Thread.sleep(20 * 1000);
				return true;
			}
			
			if (System.currentTimeMillis() - lastRunTime.getTime() >= getInterval()) {
				logger.info("[***可以执行***]");
				isRun = true;
				lastRunTime = new Date();
				logger.info("[***设置上一次执行时间：" + new Date().toString() + "***]");
				
				//睡眠20秒
				Thread.sleep(20 * 1000);
				return true;
			}
			
			logger.info("[***不能执行，上次执行时间为：" + lastRunTime.toString() + "***]");
			return false;
		}	
	}
	
	@Override
	public Comparator<TaskEntry> getComparator() {
		return new Comparator<TaskEntry>() {

			@Override
			public int compare(TaskEntry o1, TaskEntry o2) {
				if (o1 != null && o2 != null) {
					return 0;
				}
				return -1;
			}
		};
	}
	
	public abstract long getInterval() throws Exception;
	
	public abstract boolean excuteTask(Object[] tasks, String ownSign);

	public ISelectTask<TaskEntry> getSelectTask() {
		return selectTask;
	}

	public void setSelectTask(ISelectTask<TaskEntry> selectTask) {
		this.selectTask = selectTask;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getOwnSign() {
		return ownSign;
	}

	public void setOwnSign(String ownSign) {
		this.ownSign = ownSign;
	}

	public TBScheduleManagerFactory getScheduleManagerFactory() {
		return scheduleManagerFactory;
	}

	public void setScheduleManagerFactory(
			TBScheduleManagerFactory scheduleManagerFactory) {
		this.scheduleManagerFactory = scheduleManagerFactory;
	}
	
}
