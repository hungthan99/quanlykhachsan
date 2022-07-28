package Dao.Interface;

import java.util.List;

import Entity.DichVu;
import Model.PageList;
import Rmi.DTO.DichVuDTO;

public interface IDichVuDao extends IBaseDao<DichVu> {
	PageList<DichVuDTO> getListDichVuByPage(int pageNumb, int maxRow, String DichVuName);
	
	boolean isDeleteAble(int maDV);
	
	List<DichVu> getListDichVuDaDatByMaPT(int maPT);

}
