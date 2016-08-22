package ex.random.hadoophdfs.service.user.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ex.random.hadoophdfs.domain.UserInfo;
import ex.random.hadoophdfs.repository.mybatis.user.UserInfoDao;
import ex.random.hadoophdfs.service.user.UserInfoService;
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {
	private final static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
	@Resource
	private UserInfoDao userInfoDao;
	
	@Override
	public void save(UserInfo entity) {
		if (null == entity) {
			logger.error("[***UserInfoService.save执行时，参数为空***]");
		}
		userInfoDao.save(entity);
	}

	@Override
	public UserInfo selectOne(UserInfo entity) throws Exception {
		if (null == entity) {
			logger.info("[***UserInfoService.selectOne执行时，查询参数为空***]");
			throw new Exception("参数不能为空");	
		}
		return userInfoDao.selectOne(entity);
	}

	public UserInfoDao getUserInfoDao() {
		return userInfoDao;
	}

	public void setUserInfoDao(UserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}
}
