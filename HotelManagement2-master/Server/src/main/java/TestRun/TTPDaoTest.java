package TestRun;

import java.util.List;

import Dao.Impliment.TinhTrangPhongDao;
import Dao.Interface.ITinhTrangPhongDao;
import Entity.TinhTrangPhong;

public class TTPDaoTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//<<<<<<< HEAD
//		ITinhTrangPhongDao Dao  = new TinhTrangPhongDao();
//		
//		TinhTrangPhong tTTP = new TinhTrangPhong("Trống");
//		
//		if (Dao.add(tTTP)) {
//			System.out.println("Add Complete");
//		} else {
//			System.out.println("Add fail");
//		}
//=======
		ITinhTrangPhongDao Dao  = new TinhTrangPhongDao();
		
		TinhTrangPhong tTTP = new TinhTrangPhong("Đã Đặt");
		
		if (Dao.add(tTTP)) {
			System.out.println("Add Complete");
		} else {
			System.out.println("Add fail");
		}
//>>>>>>> 63117e0bc7c42054a950b1090bc14425fce1f7aa
//		
//		TinhTrangPhong ttpInfo = null;
//		try {
//			ttpInfo = Dao.get(tTTP.getMaTTP());
//			System.out.println("TTP get : " +ttpInfo.getTenTTP());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		ttpInfo.setTenTTP("Pepsi");
//		if (Dao.update(ttpInfo)) {
//			System.out.println("update Complete");
//		} else {
//			System.out.println("update Fail");
//		}
//
//		if (Dao.delete(ttpInfo.getMaTTP())) {
//			System.out.println("delete Complete");
//		} else {
//			System.out.println("delete Fail");
//		}
//		List<TinhTrangPhong> lst = Dao.getAll();
//		for (TinhTrangPhong tinhTrangPhong : lst) {
//			System.out.println(tinhTrangPhong.toString());
//		}
	}

}
