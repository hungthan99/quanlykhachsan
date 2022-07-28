package Dao.Interface;

import Entity.PhieuThue;
import Model.PageList;
import Rmi.DTO.PhieuThuePhongInfoDTO;

public interface IPhieuThue extends IBaseDao<PhieuThue>{
	PhieuThue getPhieuThueByCMT(String cmt);
	
	boolean traPhong(int maPhong);
	
	PageList<PhieuThuePhongInfoDTO> getListPhieuThueByPage(int pageNumb,int maxRow);
}
