package ex.random.hadoophdfs.service.task.select;

import java.util.List;

import ex.random.hadoophdfs.service.task.template.TaskEntry;

/**
 * 
 * @ClassName: ISelectTask 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年8月12日 下午8:33:36 
 *
 */
public interface ISelectTask<TaskEntry> {
	public List<TaskEntry> selectTasks(String ownSign, int taskQueueNum, List<String> taskQueueList,
			int eachFetchDataNum, String taskType) throws Exception;
}
