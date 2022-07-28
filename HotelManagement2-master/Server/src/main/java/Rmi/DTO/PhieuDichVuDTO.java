package Rmi.DTO;

import java.io.Serializable;
import java.util.Date;

public class PhieuDichVuDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int maPDV;
	private int soLuong;
	private Date ngayLap;
	private boolean daThanhToan;

	private int phieuThue_id;
	private int dichVu_id;

	public PhieuDichVuDTO() {
		super();
	}

	public PhieuDichVuDTO(int soLuong, Date ngayLap, int maPhieuThue, int maDichVu) {
		super();
		this.soLuong = soLuong;
		this.ngayLap = ngayLap;
		this.phieuThue_id = maPhieuThue;
		this.dichVu_id = maDichVu;
	}

	public PhieuDichVuDTO(int maPDV) {
		super();
		this.maPDV = maPDV;
	}

	public int getMaPDV() {
		return maPDV;
	}

	public void setMaPDV(int maPDV) {
		this.maPDV = maPDV;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public Date getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(Date ngayLap) {
		this.ngayLap = ngayLap;
	}

	public boolean isDaThanhToan() {
		return daThanhToan;
	}

	public void setDaThanhToan(boolean daThanhToan) {
		this.daThanhToan = daThanhToan;
	}

	public int getPhieuThue_id() {
		return phieuThue_id;
	}

	public void setPhieuThue_id(int phieuThue_id) {
		this.phieuThue_id = phieuThue_id;
	}

	public int getDichVu_id() {
		return dichVu_id;
	}

	public void setDichVu_id(int dichVu_id) {
		this.dichVu_id = dichVu_id;
	}

}
