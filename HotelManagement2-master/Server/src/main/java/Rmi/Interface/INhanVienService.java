package Rmi.Interface;

import java.rmi.RemoteException;

import Model.PageList;
import Rmi.DTO.NhanVienDTO;

public interface INhanVienService extends IBaseService<NhanVienDTO> {
	PageList<NhanVienDTO> getListNhanVienByPage(int pageNumb, int maxRow, String employeeName) throws RemoteException;

	boolean isDeleteAble(int maNV) throws RemoteException;

	NhanVienDTO getNhanVienByTenTK(String tenTK) throws RemoteException;
}
