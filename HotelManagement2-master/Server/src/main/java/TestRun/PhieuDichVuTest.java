package TestRun;

import java.util.Date;
import java.util.List;

import Dao.Impliment.DichVuDao;
import Dao.Impliment.KhachHangDao;
import Dao.Impliment.LoaiPhongDao;
import Dao.Impliment.NhanVienDao;
import Dao.Impliment.PhieuDichVuDao;
import Dao.Impliment.PhieuThueDao;
import Dao.Impliment.PhongDao;
import Dao.Impliment.TinhTrangPhongDao;
import Dao.Interface.IDichVuDao;
import Dao.Interface.IKhachHangDao;
import Dao.Interface.ILoaiPhongDao;
import Dao.Interface.INhanVien;
import Dao.Interface.IPhieuDichVu;
import Dao.Interface.IPhieuThue;
import Dao.Interface.IPhongDao;
import Dao.Interface.ITinhTrangPhongDao;
import Entity.DichVu;
import Entity.KhachHang;
import Entity.LoaiPhong;
import Entity.NhanVien;
import Entity.PhieuDichVu;
import Entity.PhieuThue;
import Entity.Phong;
import Entity.TaiKhoan;
import Entity.TinhTrangPhong;
import Rmi.DTO.PhieuDichVuInfoDTO;

public class PhieuDichVuTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// can 1 so Dao de tao du lieu mau sau do xoa du lieu mau di

//		IPhieuThue ptDao = new PhieuThueDao();
//		IKhachHangDao khDao = new KhachHangDao();
//		INhanVien nvDao = new NhanVienDao();
//		IPhongDao phongDao = new PhongDao();
//
//		ITinhTrangPhongDao DaoTTP = new TinhTrangPhongDao();
//		ILoaiPhongDao LPDao = new LoaiPhongDao();
//
//		TinhTrangPhong tTTP = new TinhTrangPhong("Trống2");
//		LoaiPhong newLP = new LoaiPhong("Standard", 100000.0);
//		DaoTTP.add(tTTP);
//		LPDao.add(newLP);
//
//		Phong samplePhong = new Phong("P2809");
//		samplePhong.setMaTTP(tTTP);
//		samplePhong.setMaLP(newLP);
//
//		// da co Sample Phong
//		phongDao.add(samplePhong);
//
////		ChucVu sampleCV = new ChucVu("SampleChucVu", 1.0);
////		cvDao.add(sampleCV);
//
//		TaiKhoan tkNV = new TaiKhoan("hinh99", "123123", true);
//		NhanVien tempNV = new NhanVien("Phan Trong Hinh", "abc@gmail.com", "male", "123123");
//		//tempNV.setChucVu(sampleCV);
//		tempNV.setTaiKhoan(tkNV);
//
//		// da co SampleNhanVien
//		nvDao.add(tempNV);
//
//		KhachHang tKhachHang = new KhachHang("Phan Trong HInh", "Samplemail@gmail.com", "0981986242", "Tp HCM",
//				"123123");
//
//		// da co khach hang sample
//		khDao.add(tKhachHang);
//
//		@SuppressWarnings("deprecation")
//		Date ngayDat = new Date(2021, 12, 10);
//		@SuppressWarnings("deprecation")
//		Date ngayKetThuc = new Date(2022, 1, 10);
//		PhieuThue samplePT = new PhieuThue(ngayDat, ngayKetThuc, samplePhong, tempNV, tKhachHang);
//
//		// đã có phiếu thuê Sample
//		ptDao.add(samplePT);
//
//		PhieuThue tempPt = new PhieuThue(samplePT.getMaPT());
//
//		// ============= =========================================================== //
//		IPhieuDichVu pdvDao = new PhieuDichVuDao();
//		IDichVuDao dvDao = new DichVuDao();
//
//		DichVu tDichVu = new DichVu("Coca-cola", 10000.0,"Chai");
//		dvDao.add(tDichVu);
//
//		DichVu tempDv = new DichVu(tDichVu.getMaDv());
//
//		PhieuDichVu pdvSample = new PhieuDichVu();
//		pdvSample.setNgayLap(new Date());
//		pdvSample.setDichVu(tempDv);
//		pdvSample.setPhieuThue(tempPt);
//		pdvSample.setSoLuong(1);
//		pdvSample.setDaThanhToan(false);
//
//		if (pdvDao.add(pdvSample))
//			System.out.println("Add success");
//		else
//			System.out.println("add fail");

//		PhieuDichVu temp = null;
//
//		try {
//			temp = pdvDao.get(pdvSample.getMaPDV());
//			System.out.println(temp.toString());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		temp.setDaThanhToan(true);
//		if (pdvDao.update(temp))
//			System.out.println("update success");
//		else
//			System.out.println("update fail");
//
//		if (pdvDao.delete(temp.getMaPDV()))
//			System.out.println("delete success");
//		else
//			System.out.println("delete fail");
//
//		// ==== Delete All Sample Object (Khach Hang, Nhan Vien, Phong, .....) ==== //
//		ptDao.delete(samplePT.getMaPT());
//		DaoTTP.delete(tTTP.getMaTTP());
//		LPDao.delete(newLP.getMaLP());
//		khDao.delete(tKhachHang.getMaKH());
//
//		phongDao.delete(samplePhong.getMaP());
//		nvDao.delete(tempNV.getMaNV());
		// cvDao.delete(sampleCV.getMaCV());
		IPhieuDichVu pdvDao = new PhieuDichVuDao();
//		pdvDao.addPhieuDichVuByMaPhong(18, 1,1);
		List<PhieuDichVuInfoDTO> lst = pdvDao.getListPhieuDichVuByMaPT(15);
		for (PhieuDichVuInfoDTO phieuDichVuInfoDTO : lst) {
			System.out.println(phieuDichVuInfoDTO.getDichVu().getTenDv() + ":" + phieuDichVuInfoDTO.getSoLuong());
		}
	}

}
