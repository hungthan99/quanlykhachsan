package Rmi.Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IBaseService<T> extends Remote {
	boolean addObject(T objectDTO) throws RemoteException;
	T getObjectById(int id)throws RemoteException;
	boolean updateObject(T objectDTO)throws RemoteException;
	boolean deleteObjectById(int objectId)throws RemoteException;
}
