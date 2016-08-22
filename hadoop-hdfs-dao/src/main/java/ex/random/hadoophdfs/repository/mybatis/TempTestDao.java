package ex.random.hadoophdfs.repository.mybatis;

import ex.random.hadoophdfs.domain.TempTest;
import ex.random.hadoophdfs.repository.mybatis.base.BaseDao;

@MyBatisRepository("tempTestDao")
public interface TempTestDao extends BaseDao<TempTest> {

}
