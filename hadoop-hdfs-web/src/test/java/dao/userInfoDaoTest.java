package dao;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ex.random.hadoophdfs.domain.TempTest;
import ex.random.hadoophdfs.domain.UserInfo;
import ex.random.hadoophdfs.repository.mybatis.TempTestDao;
import ex.random.hadoophdfs.repository.mybatis.user.UserInfoDao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
public class userInfoDaoTest {
	
	@Resource
	private UserInfoDao userInfoDao;
	private static Logger logger = LoggerFactory.getLogger(TempTestDao.class);
	@Test
	public void testSave() {
		try {
			UserInfo user = new UserInfo();
			user.setCreatedTime(new Date());
			user.setModifiedTime(new Date());
			user.setUserName("test1111");
			user.setPassWord("234874385787432423");
			userInfoDao.save(user);
			System.out.println("test1111111111111111111111111111");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
