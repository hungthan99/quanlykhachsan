package Dao.Interface;

import java.util.List;

public interface IBaseDao<T> {

	T get(int id) throws Exception;
    
    List<T> getAll();
    
    boolean add(T addObject);
    
    boolean update(T updateObject);
    
    boolean delete(int objectId);
}
