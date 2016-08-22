package ex.random.hadoophdfs.service.user;

import ex.random.hadoophdfs.domain.UserInfo;
/**
 * 
 * @ClassName: UserInfoService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author japing 
 * @date 2016年8月22日 下午9:30:36 
 *
 */
public interface UserInfoService {
	/**
	 * 
	 * @param entity
	 * @Description: TODO(这里用一句话描述这个类的作用) 
	 * @author japing 
	 * @date 2016年8月22日 下午9:30:45
	 */
	public void save(UserInfo entity);
	/**
	 * 
	 * @param entity
	 * @return
	 * @Description: TODO(这里用一句话描述这个类的作用) 
	 * @author japing 
	 * @throws Exception 
	 * @date 2016年8月22日 下午9:31:20
	 */
	public UserInfo selectOne(UserInfo entity) throws Exception;
}
