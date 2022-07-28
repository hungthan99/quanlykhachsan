package Rmi.Interface;

import java.rmi.RemoteException;
import java.util.List;

import Rmi.DTO.PhieuDichVuDTO;
import Rmi.DTO.PhieuDichVuInfoDTO;

public interface IPhieuDichVuService extends IBaseService<PhieuDichVuDTO> {
	boolean addPhieuDichVuByMaPhong(int maPhong, int maDichVu, int soluong) throws RemoteException;

	List<PhieuDichVuInfoDTO> getListPhieuDichVuByMaPT(int maPT) throws RemoteException;

	boolean updateThanhToanDvByMaPT(int maPT) throws RemoteException;
}
