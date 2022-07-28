package Rmi.DTO;

import java.io.Serializable;
import java.util.Date;

public class PhieuDichVuInfoDTO implements Serializable{
	private int maPDV;
	private int soLuong;
	private Date ngayLap;
	private boolean daThanhToan;
	private int phieuThue_id;

	private DichVuDTO dichVu;

	public PhieuDichVuInfoDTO() {
		super();
	}

	public PhieuDichVuInfoDTO(int maPDV, int soLuong, Date ngayLap, boolean daThanhToan, int phieuThue_id) {
		super();
		this.maPDV = maPDV;
		this.soLuong = soLuong;
		this.ngayLap = ngayLap;
		this.daThanhToan = daThanhToan;
		this.phieuThue_id = phieuThue_id;
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

	public DichVuDTO getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVuDTO dichVu) {
		this.dichVu = dichVu;
	}
	
	
}
