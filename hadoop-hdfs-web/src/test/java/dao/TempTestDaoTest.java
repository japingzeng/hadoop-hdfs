package dao;
import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import ex.random.hadoophdfs.domain.TempTest;
import ex.random.hadoophdfs.repository.mybatis.TempTestDao;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml"})
public class TempTestDaoTest {
	@Resource
	private TempTestDao tempTestDao;
	private static Logger logger = LoggerFactory.getLogger(TempTestDao.class);
	@Test
	public void testSave() {
		try {
			TempTest aTest = new TempTest();
			aTest.setPassWord("hehehe");
			aTest.setUserName("heiheihei");
			tempTestDao.save(aTest);
			System.out.println("test1111111111111111111111111111");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
