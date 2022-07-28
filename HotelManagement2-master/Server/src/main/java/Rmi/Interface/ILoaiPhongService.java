package Rmi.Interface;

import java.rmi.RemoteException;
import java.util.List;

import Model.PageList;
import Rmi.DTO.LoaiPhongDTO;

public interface ILoaiPhongService extends IBaseService<LoaiPhongDTO> {

	List<LoaiPhongDTO> getAllLoaiPhong() throws RemoteException;

	PageList<LoaiPhongDTO> getListLoaiPhongByPage(int pageNumb, int maxRow, String key) throws RemoteException;

	boolean isDeleteAble(int maLP) throws RemoteException;
}
