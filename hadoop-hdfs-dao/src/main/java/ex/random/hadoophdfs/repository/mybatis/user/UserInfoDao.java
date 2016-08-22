package ex.random.hadoophdfs.repository.mybatis.user;

import ex.random.hadoophdfs.domain.UserInfo;
import ex.random.hadoophdfs.repository.mybatis.MyBatisRepository;
import ex.random.hadoophdfs.repository.mybatis.base.BaseDao;
@MyBatisRepository("userInfoDao")
public interface UserInfoDao extends BaseDao<UserInfo> {

}
