package Rmi.Interface;

import java.rmi.RemoteException;
import java.util.List;

import Rmi.DTO.TinhTrangPhongDTO;

public interface ITinhTrangPhongService extends IBaseService<TinhTrangPhongDTO> {
	List<TinhTrangPhongDTO> getAll() throws RemoteException;
}
