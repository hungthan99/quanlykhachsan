package Rmi.Interface;

import java.rmi.RemoteException;

import Model.PageList;
import Rmi.DTO.PhieuThueDTO;
import Rmi.DTO.PhieuThuePhongInfoDTO;

public interface IPhieuThueService extends IBaseService<PhieuThueDTO> {
	PhieuThueDTO getPhieuThueByCMT(String cmt) throws RemoteException;

	boolean traPhong(int maPhong) throws RemoteException;

	PageList<PhieuThuePhongInfoDTO> getListPhieuThueByPage(int pageNumb, int maxRow) throws RemoteException;
}
