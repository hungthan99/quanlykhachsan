package Dao.Interface;

import Entity.LoaiPhong;
import Model.PageList;
import Rmi.DTO.LoaiPhongDTO;

public interface ILoaiPhongDao extends IBaseDao<LoaiPhong>{
	PageList<LoaiPhongDTO> getListLoaiPhongByPage(int pageNumb, int maxRow, String key);
	
	boolean isDeleteAble(int maLP);
}
