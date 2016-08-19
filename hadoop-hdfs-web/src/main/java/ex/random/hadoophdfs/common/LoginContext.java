package ex.random.hadoophdfs.common;

public class LoginContext {
	private final static ThreadLocal<LoginContext> threadLocal = new ThreadLocal<LoginContext>();
	/**
	 * 用户ID
	 */
	private String pin;
	
	/**
	 * 创建时间
	 * 默认为当前时间
	 */
	private long createdTime = System.currentTimeMillis();
	
	private LoginContext() {
		threadLocal.set(this); //存入ThreadLocal
	}
	
	public static LoginContext getContext() {
		LoginContext context = threadLocal.get();
		if (null == context) {
			context = new LoginContext();
		}
		return context;
	}
	
	public static LoginContext getContext(String pin) {
		LoginContext context = threadLocal.get();
		if (null == context) {
			context = new LoginContext();
		}
		return context;
	}
	
	
	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
}
