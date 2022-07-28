package Utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Entity.DichVu;
import Entity.KhachHang;
import Entity.LoaiPhong;
import Entity.NhanVien;
import Entity.PhieuDichVu;
import Entity.PhieuThue;
import Entity.Phong;
import Entity.TaiKhoan;
import Entity.TinhTrangPhong;
import Rmi.DTO.DichVuDTO;
import Rmi.DTO.KhachHangDTO;
import Rmi.DTO.LoaiPhongDTO;
import Rmi.DTO.NhanVienDTO;
import Rmi.DTO.PhieuDichVuDTO;
import Rmi.DTO.PhieuDichVuInfoDTO;
import Rmi.DTO.PhieuThueDTO;
import Rmi.DTO.PhieuThuePhongInfoDTO;
import Rmi.DTO.PhongDTO;
import Rmi.DTO.TaiKhoanDTO;
import Rmi.DTO.TinhTrangPhongDTO;

public class MappingDtoFacade {

	public static DichVu mapToDichVu(DichVuDTO mapObject) {
		DichVu returnVL = new DichVu();
		returnVL.setTenDv(mapObject.getTenDv());
		returnVL.setDonGia(mapObject.getDonGia());
		returnVL.setDonVi(mapObject.getDonVi());
		return returnVL;
	}

	public static DichVuDTO mapToDichVuDTO(DichVu mapObject) {
		DichVuDTO returnVL = new DichVuDTO(mapObject.getTenDv(), mapObject.getDonGia(), mapObject.getDonVi());
		returnVL.setMaDv(mapObject.getMaDv());
		return returnVL;
	}

	public static KhachHang mapToKhachHang(KhachHangDTO DTO) {
		return new KhachHang(DTO.getTen(), DTO.getEmail(), DTO.getSdt(), DTO.getDiaChi(), DTO.getSoCMND());
	}

	public static KhachHangDTO mapToKhachHangDTO(KhachHang khachHang) {
		KhachHangDTO rv = new KhachHangDTO(khachHang.getTen(), khachHang.getEmail(), khachHang.getSdt(),
				khachHang.getDiaChi(), khachHang.getSoCMND());
		rv.setMaKH(khachHang.getMaKH());
		return rv;
	}

	public static LoaiPhong mapToLoaiPhong(LoaiPhongDTO Dto) {
		return new LoaiPhong(Dto.getTenLP(), Dto.getDonGia());
	}

	public static LoaiPhongDTO mapToLoaiPhongDTO(LoaiPhong loaiPhong) {
		LoaiPhongDTO rv = new LoaiPhongDTO(loaiPhong.getTenLP(), loaiPhong.getDonGia());
		rv.setMaLP(loaiPhong.getMaLP());
		return rv;
	}

	public static NhanVien mapToNhanVien(NhanVienDTO objectDTO) {
		NhanVien rv = new Entity.NhanVien(objectDTO.getTen(), objectDTO.getEmail(), objectDTO.getGioiTinh(),
				objectDTO.getSdt());

		TaiKhoan tk = new TaiKhoan(objectDTO.getTaiKhoan().getTenTK(), objectDTO.getTaiKhoan().getMatKhau(),
				objectDTO.getTaiKhoan().isAdmin());

		rv.setTaiKhoan(tk);
		return rv;
	}

	public static NhanVienDTO mapToNhanVienDTO(NhanVien nhanVien) {
		NhanVienDTO rvDto = new NhanVienDTO(nhanVien.getTen(), nhanVien.getEmail(), nhanVien.getGioiTinh(),
				nhanVien.getSdt());

		TaiKhoanDTO tkDto = new TaiKhoanDTO(nhanVien.getTaiKhoan().getTenTK(), nhanVien.getTaiKhoan().getMatKhau(),
				nhanVien.getTaiKhoan().isAdmin());

		rvDto.setTaiKhoan(tkDto);
		rvDto.setMaNV(nhanVien.getMaNV());
		return rvDto;
	}

	public static PhieuThue mapToPhieuThueAdd(PhieuThueDTO objectDTO) {
		Phong phong = new Phong();
		phong.setMaP(objectDTO.getPhong_id());

		NhanVien nv = new NhanVien(objectDTO.getNhanVien_id());

		KhachHang kh = new KhachHang(objectDTO.getKhachHang_id());

		PhieuThue rv = new PhieuThue(objectDTO.getNgayDat(), objectDTO.getNgayKetThuc(), phong, nv, kh);

		rv.setNgayLap((objectDTO.getNgayLap() != null) ? objectDTO.getNgayLap() : null);
		rv.setNgayNhan((objectDTO.getNgayNhan() != null) ? objectDTO.getNgayNhan() : null);
		rv.setNgayTra((objectDTO.getNgayTra() != null) ? objectDTO.getNgayTra() : null);

		rv.setTrangThai(objectDTO.getTrangThai());
		return rv;
	}

	public static PhieuThueDTO mapToPhieuThueDTO(PhieuThue phieuThue) {
		// public PhieuThueDTO(Date ngayDat, Date ngayKetThuc, Date ngayLap, PhongDTO
		// phong, NhanVienDTO nhanVien,KhachHangDTO khachHang)
		PhieuThueDTO rvDto = new PhieuThueDTO();
		rvDto.setMaPT(phieuThue.getMaPT());
		rvDto.setNgayLap(phieuThue.getNgayLap());

		rvDto.setNgayDat(phieuThue.getNgayDat());
		rvDto.setNgayKetThuc(phieuThue.getNgayKetThuc());

		rvDto.setNgayNhan((phieuThue.getNgayNhan() != null) ? phieuThue.getNgayNhan() : null);
		rvDto.setNgayTra((phieuThue.getNgayTra() != null) ? phieuThue.getNgayTra() : null);

		rvDto.setPhong_id(phieuThue.getPhong().getMaP());
		rvDto.setNhanVien_id(phieuThue.getNhanVien().getMaNV());
		rvDto.setKhachHang_id(phieuThue.getKhachHang().getMaKH());

		rvDto.setTrangThai(phieuThue.getTrangThai());
		return rvDto;
	}

	public static Phong mapToPhong(PhongDTO objectDTO) {
		LoaiPhong lp = new LoaiPhong(objectDTO.getLoaiPhong_id());
		TinhTrangPhong ttp = new TinhTrangPhong(objectDTO.getTinhTrangPhong_id());

		Phong rv = new Phong(objectDTO.getTen());
		rv.setMaLP(lp);
		rv.setMaTTP(ttp);

		return rv;
	}

	public static List<PhongDTO> convertToListPhongDTO(List<Phong> lstPhong) {
		List<PhongDTO> lstDTO = new ArrayList<PhongDTO>();
		for (Phong phongEntity : lstPhong) {
			PhongDTO phongDTO = mapToPhongDTO(phongEntity);
			lstDTO.add(phongDTO);
		}
		return lstDTO;

	}

	public static PhongDTO mapToPhongDTO(Phong phong) {
		PhongDTO rvDto = new PhongDTO(phong.getTen(), phong.getMaLP().getMaLP(), phong.getMaTTP().getMaTTP());
		rvDto.setMaP(phong.getMaP());

		return rvDto;
	}

	public static PhieuDichVu mapToPhieuDichVu(PhieuDichVuDTO objectDTO) {
		DichVu dv = new DichVu(objectDTO.getDichVu_id());
		PhieuThue pt = new PhieuThue(objectDTO.getPhieuThue_id());

		PhieuDichVu rv = new PhieuDichVu();

		rv.setNgayLap(objectDTO.getNgayLap());
		rv.setDichVu(dv);
		rv.setPhieuThue(pt);
		rv.setSoLuong(objectDTO.getSoLuong());
		rv.setDaThanhToan(objectDTO.isDaThanhToan());
		return rv;
	}

	public static TinhTrangPhong mapToTinhTrangPhong(TinhTrangPhongDTO objectDTO) {
		TinhTrangPhong ttp = new TinhTrangPhong(objectDTO.getTenTTP());
		return ttp;
	}

	public static TinhTrangPhongDTO mapToTinhTrangPhongDto(TinhTrangPhong tinhTrangPhong) {
		TinhTrangPhongDTO rvDto = new TinhTrangPhongDTO(tinhTrangPhong.getMaTTP());
		rvDto.setTenTTP(tinhTrangPhong.getTenTTP());
		return rvDto;
	}

	public static TaiKhoanDTO mapToTaiKhoanDTO(TaiKhoan taiKhoan) {
		return new TaiKhoanDTO(taiKhoan.getTenTK(), taiKhoan.getMatKhau(), taiKhoan.isAdmin());
	}

	public static List<LoaiPhongDTO> mapToListLoaiPhongDTO(List<LoaiPhong> lstLoaiPhong) {
		List<LoaiPhongDTO> rvlst = new ArrayList<LoaiPhongDTO>();
		for (LoaiPhong loaiPhong : lstLoaiPhong) {
			rvlst.add(mapToLoaiPhongDTO(loaiPhong));
		}
		return rvlst;
	}

	public static List<TinhTrangPhongDTO> mapToListTtpDTO(List<TinhTrangPhong> lstTTP) {
		List<TinhTrangPhongDTO> rvList = new ArrayList<TinhTrangPhongDTO>();
		for (TinhTrangPhong tinhTrangPhong : lstTTP) {
			rvList.add(mapToTinhTrangPhongDto(tinhTrangPhong));
		}
		return rvList;
	}

	public static List<KhachHangDTO> convertToListKhachHangDTO(List<KhachHang> lstKhachHang) {
		List<KhachHangDTO> lstDTO = new ArrayList<KhachHangDTO>();
		for (KhachHang khachHangEntity : lstKhachHang) {
			KhachHangDTO khachHangDTO = mapToKhachHangDTO(khachHangEntity);
			lstDTO.add(khachHangDTO);
		}
		return lstDTO;

	}

	public static List<NhanVienDTO> convertToListNhanVienDTO(List<NhanVien> lstNhanVien) {
		List<NhanVienDTO> lstDTO = new ArrayList<NhanVienDTO>();
		for (NhanVien nhanVienEntity : lstNhanVien) {
			NhanVienDTO nhanVienDTO = mapToNhanVienDTO(nhanVienEntity);
			lstDTO.add(nhanVienDTO);
		}
		return lstDTO;

	}

	public static List<DichVuDTO> convertToListDichVuDTO(List<DichVu> dichVus_Paged) {
		List<DichVuDTO> lstDTO = new ArrayList<DichVuDTO>();
		for (DichVu dichvu : dichVus_Paged) {
			DichVuDTO DTO = mapToDichVuDTO(dichvu);
			lstDTO.add(DTO);
		}
		return lstDTO;
	}

	private static PhieuThuePhongInfoDTO convertToPhieuThuePhongInfoDTO(PhieuThue phieuThue) {
		PhieuThuePhongInfoDTO rvDto = new PhieuThuePhongInfoDTO();
		rvDto.setMaPT(phieuThue.getMaPT());
		rvDto.setNgayLap(phieuThue.getNgayLap());

		rvDto.setNgayNhan(phieuThue.getNgayNhan());
		rvDto.setNgayTra(phieuThue.getNgayTra());

		rvDto.setTrangThai(phieuThue.getTrangThai());
		rvDto.setKhachHangDTO(mapToKhachHangDTO(phieuThue.getKhachHang()));
		rvDto.setPhongDTO(mapToPhongDTO(phieuThue.getPhong()));
		return rvDto;
	}

	public static List<PhieuThuePhongInfoDTO> convertToListPhieuThueInfoDTO(List<PhieuThue> phieuThues_Paged) {
		List<PhieuThuePhongInfoDTO> lstDTO = new ArrayList<PhieuThuePhongInfoDTO>();
		for (PhieuThue phieuThue : phieuThues_Paged) {
			lstDTO.add(convertToPhieuThuePhongInfoDTO(phieuThue));
		}
		return lstDTO;
	}

	public static List<PhieuDichVuInfoDTO> mapToListPdvInfoDTO(List<PhieuDichVu> lst) {
		List<PhieuDichVuInfoDTO> lstDTO = new ArrayList<PhieuDichVuInfoDTO>();
		for (PhieuDichVu Pdv : lst) {
			lstDTO.add(mapToPhieuDichVuInfoDTO(Pdv));
		}
		return lstDTO;
	}

	public static PhieuDichVuInfoDTO mapToPhieuDichVuInfoDTO(PhieuDichVu pdv) {
		// hieuDichVuInfoDTO(int maPDV, int soLuong, Date ngayLap, boolean daThanhToan,
		// int phieuThue_id) {
		PhieuDichVuInfoDTO dto = new PhieuDichVuInfoDTO(pdv.getMaPDV(), pdv.getSoLuong(), pdv.getNgayLap(),
				pdv.isDaThanhToan(), pdv.getPhieuThue().getMaPT());
		DichVuDTO dvDTO = mapToDichVuDTO(pdv.getDichVu());
		dto.setDichVu(dvDTO);
		return dto;
	}
}
