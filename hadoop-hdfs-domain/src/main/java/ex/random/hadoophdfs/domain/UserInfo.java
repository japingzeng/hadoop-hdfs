package ex.random.hadoophdfs.domain;

import ex.random.hadoophdfs.domain.base.BaseEntity;

public class UserInfo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;
	private String passWord;
	private Integer level;
	private String token;

	public String getUserName() {
		return userName;
	}

	public UserInfo setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getPassWord() {
		return passWord;
	}

	public UserInfo setPassWord(String passWord) {
		this.passWord = passWord;
		return this;
	}

	public Integer getLevel() {
		return level;
	}

	public UserInfo setLevel(Integer level) {
		this.level = level;
		return this;
	}

	public String getToken() {
		return token;
	}

	public UserInfo setToken(String token) {
		this.token = token;
		return this;
	}

}
