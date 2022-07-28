package Rmi.DTO;

import java.io.Serializable;
import java.util.Date;

public class PhieuThuePhongInfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int maPT;
	private Date ngayDat;
	private Date ngayKetThuc;
	private Date ngayLap;
	private Date ngayNhan;
	private Date ngayTra;
	private String trangThai;

	private PhongDTO phongDTO;
	private KhachHangDTO khachHangDTO;

	public PhieuThuePhongInfoDTO() {
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

	public PhongDTO getPhongDTO() {
		return phongDTO;
	}

	public void setPhongDTO(PhongDTO phongDTO) {
		this.phongDTO = phongDTO;
	}

	public KhachHangDTO getKhachHangDTO() {
		return khachHangDTO;
	}

	public void setKhachHangDTO(KhachHangDTO khachHangDTO) {
		this.khachHangDTO = khachHangDTO;
	}

}
