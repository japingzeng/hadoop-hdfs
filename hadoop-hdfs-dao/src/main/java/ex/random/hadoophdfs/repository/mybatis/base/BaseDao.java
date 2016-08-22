package ex.random.hadoophdfs.repository.mybatis.base;

import java.util.List;

public interface BaseDao <T>{
	List<T> search(T entity);
	T selectById(Long id);
	T selectOne(T entity);
	void save(T entity);
	void delete(T entity);
	void deleteById(Long id);
	void update(T entity);

}
