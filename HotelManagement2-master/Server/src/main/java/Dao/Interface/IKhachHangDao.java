package Dao.Interface;

import Entity.KhachHang;
import Model.PageList;
import Rmi.DTO.KhachHangDTO;

public interface IKhachHangDao extends IBaseDao<KhachHang> {

	PageList<KhachHangDTO> searchListKhachhang(String tenKh, int pageNumb);

	PageList<KhachHangDTO> getListKhachHangByPage(int pageNumb, int maxRow, String customerName);
	boolean isDeleteAble(int maKH);
}
