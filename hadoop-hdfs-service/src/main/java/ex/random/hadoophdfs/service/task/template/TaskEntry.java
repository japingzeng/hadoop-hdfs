package ex.random.hadoophdfs.service.task.template;

public class TaskEntry {

	private String taskType;
	private String queueId;
	private String json;
	private Object task;
	private String queryKey;

	public TaskEntry(String json, String queueId, String taskType, Object task) {
		this.json = json;
		this.task = task;
		this.taskType = taskType;
		this.queueId = queueId;
	}

	public TaskEntry(String json, String queueId, String taskType, Object task, String queryKey) {
		this(json, queueId, taskType, task);
		this.queryKey = queryKey;
	}

	public String getQueueId() {
		return queueId;
	}

	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public Object getTask() {
		return task;
	}

	public void setTask(Object task) {
		this.task = task;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getQueryKey() {
		return queryKey;
	}

	public void setQueryKey(String queryKey) {
		this.queryKey = queryKey;
	}

}
