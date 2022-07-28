package Rmi.Interface;

import java.rmi.RemoteException;

import Model.PageList;
import Rmi.DTO.KhachHangDTO;

public interface IKhachHangService extends IBaseService<KhachHangDTO> {
	PageList<KhachHangDTO> getListKhachHangByPage(int pageNumb, int maxRow, String customerName) throws RemoteException;

	boolean isDeleteAble(int maKH) throws RemoteException;
}
