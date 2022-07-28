package TestRun;

import Dao.Impliment.NhanVienDao;
import Dao.Interface.INhanVien;
import Entity.NhanVien;
import Entity.TaiKhoan;

public class NhanVIenDaoTest {
	public static void main(String[] args) {
		INhanVien nvDao = new NhanVienDao();
//		IChucVu cvDao = new ChucVuDao();

//		ChucVu sampleCV = new ChucVu("Ăn Hại2", 1.0);
//		cvDao.add(sampleCV);

		TaiKhoan tkNV = new TaiKhoan("hinh123", "123123", true);
		NhanVien nv = new NhanVien("Phan Trong Hinh", "abc@gmail.com", "male", "123123");
		//nv.setChucVu(sampleCV);
		nv.setTaiKhoan(tkNV);

		if (nvDao.add(nv))
			System.out.println("Add success");
		else
			System.out.println("Add fail");

//		NhanVien temp = null;
//		try {
//			temp = nvDao.get(2);
//			System.out.println(temp.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		TaiKhoan temp2 = new TaiKhoan("Hinhhinh123", "123123", true);
//		temp.setTaiKhoan(temp2);
//		if (nvDao.update(temp)) System.out.println("update success");
//		else System.out.println("fail success");
//		
//		if(nvDao.delete(temp.getMaNV())) System.out.println("delete success");
//		else System.out.println("delete fail");
//		ITaiKhoan nvDao = new TaiKhoanDao();
//		System.out.println(nvDao.get("hinh123123").toString());
//		nvDao.delete("hinh123123");
		
//		nvDao.addTkToNhanVien(2, temp);

	}
}
