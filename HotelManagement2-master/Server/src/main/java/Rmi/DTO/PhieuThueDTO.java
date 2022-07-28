package Rmi.DTO;

import java.io.Serializable;
import java.util.Date;

public class PhieuThueDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int maPT;
	private Date ngayDat;
	private Date ngayKetThuc;
	private Date ngayLap;
	private Date ngayNhan;
	private Date ngayTra;
	private String trangThai;

	private int phong_id;

	private int nhanVien_id;

	private int khachHang_id;

	public PhieuThueDTO() {
		super();
	}

	public PhieuThueDTO(int maPT) {
		super();
		this.maPT = maPT;
	}

	public PhieuThueDTO(Date ngayDat, Date ngayKetThuc, int phong, int nhanVien, int khachHang) {
		super();
		this.ngayDat = ngayDat;
		this.ngayKetThuc = ngayKetThuc;
		this.ngayLap = new Date();
		this.phong_id = phong;
		this.nhanVien_id = nhanVien;
		this.khachHang_id = khachHang;
		this.trangThai = "NEW";
	}

	public int getMaPT() {
		return maPT;
	}

	public void setMaPT(int maPT) {
		this.maPT = maPT;
	}

	public Date getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public Date getNgayNhan() {
		return ngayNhan;
	}

	public void setNgayNhan(Date ngayNhan) {
		this.ngayNhan = ngayNhan;
	}

	public Date getNgayTra() {
		return ngayTra;
	}

	public void setNgayTra(Date ngayTra) {
		this.ngayTra = ngayTra;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public int getPhong_id() {
		return phong_id;
	}

	public void setPhong_id(int phong_id) {
		this.phong_id = phong_id;
	}

	public int getNhanVien_id() {
		return nhanVien_id;
	}

	public void setNhanVien_id(int nhanVien_id) {
		this.nhanVien_id = nhanVien_id;
	}

	public int getKhachHang_id() {
		return khachHang_id;
	}

	public void setKhachHang_id(int khachHang_id) {
		this.khachHang_id = khachHang_id;
	}

	@Override
	public String toString() {
		return "PhieuThueDTO [maPT=" + maPT + ", ngayDat=" + ngayDat + ", ngayKetThuc=" + ngayKetThuc + ", ngayLap="
				+ ngayLap + ", trangThai=" + trangThai + ", phong_id=" + phong_id + ", nhanVien_id=" + nhanVien_id
				+ ", khachHang_id=" + khachHang_id + "]";
	}

}
