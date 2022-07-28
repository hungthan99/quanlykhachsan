package Dao.Interface;

import java.util.List;

import Entity.PhieuDichVu;
import Rmi.DTO.PhieuDichVuInfoDTO;

public interface IPhieuDichVu extends IBaseDao<PhieuDichVu> {

	boolean addPhieuDichVuByMaPhong(int maPhong, int maDichVu, int soluong);

	List<PhieuDichVuInfoDTO> getListPhieuDichVuByMaPT(int maPT);

	boolean updateThanhToanDvByMaPT(int maPT);
}
