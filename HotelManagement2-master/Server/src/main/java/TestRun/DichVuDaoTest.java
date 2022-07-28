package TestRun;

import java.util.List;

import Dao.Impliment.DichVuDao;
import Dao.Interface.IDichVuDao;
import Entity.DichVu;
import Model.PageList;
import Rmi.DTO.DichVuDTO;

public class DichVuDaoTest {
	public static void main(String[] args) {
		IDichVuDao Dao = new DichVuDao();
//
		DichVu tDichVu = new DichVu("Phof Capas Ba", 10000.0, "vnd");

		if (Dao.add(tDichVu)) {
			System.out.println("Add DV Complete");
		} else {
			System.out.println("Add DV Fail");
		}

//		DichVu dvInfo = null;
//		try {
//			dvInfo = Dao.get(tDichVu.getMaDv());
//			System.out.println(dvInfo.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		dvInfo.setTenDv("Pepsi");
//		if (Dao.update(dvInfo)) {
//			System.out.println("update DV Complete");
//		} else {
//			System.out.println("update DV Fail");
//		}
//
//		if (Dao.delete(dvInfo.getMaDv())) {
//			System.out.println("delete DV Complete");
//		} else {
//			System.out.println("delete DV Fail");
//		}
//		PageList<DichVuDTO> page = Dao.getListDichVuByPage(2, 2, "");
//		for (DichVuDTO string : page.getListData()) {
//			System.out.println(string.toString());
//		}
//		boolean i = Dao.isDeleteAble(0);
//		System.out.println(i);
		
//		List<DichVu> abc = Dao.getListDichVuDaDatByMaPT(34);
//		for (DichVu dichVu : abc) {
//			System.out.println(dichVu.toString());
//		}

	}
}
