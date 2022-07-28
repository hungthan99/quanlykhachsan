package Dao.Interface;

import Entity.NhanVien;
import Model.PageList;
import Rmi.DTO.NhanVienDTO;

public interface INhanVien extends IBaseDao<NhanVien>{
	PageList<NhanVienDTO> searchListNhanVien(String tenNv, int pageNumb);
	
	PageList<NhanVienDTO> getListNhanVienByPage(int pageNumb, int maxRow, String employeeName);
	
	boolean isDeleteAble(int maNV);
	
	NhanVien getNhanVienByTenTK(String tenTK);
}
