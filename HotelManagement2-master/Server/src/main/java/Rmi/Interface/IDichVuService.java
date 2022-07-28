package Rmi.Interface;

import java.rmi.RemoteException;
import java.util.List;

import Entity.DichVu;
import Model.PageList;
import Rmi.DTO.DichVuDTO;

public interface IDichVuService extends IBaseService<DichVuDTO> {
	PageList<DichVuDTO> getListDichVuByPage(int pageNumb, int maxRow, String DichVuName) throws RemoteException;

	boolean isDeleteAble(int maDV) throws RemoteException;

	List<DichVu> getListDichVuDaDatByMaPT(int maPT) throws RemoteException;
}