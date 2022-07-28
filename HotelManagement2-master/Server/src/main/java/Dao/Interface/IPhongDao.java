package Dao.Interface;

import Entity.Phong;
import Model.PageList;
import Rmi.DTO.PhongDTO;

public interface IPhongDao extends IBaseDao<Phong> {
	Phong getPhongTrongByLoaiPhongId(int loaiPhongId);

	PageList<PhongDTO> getListPhongByPage(int pageNumb, int maxRow, String roomName);

	PageList<PhongDTO> getListPhongDaThue(int pageNumb, int maxRow, String roomName);
}