package Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "phieudichvus")
public class PhieuDichVu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int maPDV;
	private int soLuong;
	private Date ngayLap;
	private boolean daThanhToan;

	@ManyToOne
	@JoinColumn(name = "maPT")
	private PhieuThue phieuThue;
	@ManyToOne
	@JoinColumn(name = "maDV")
	private DichVu dichVu;

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

	public PhieuThue getPhieuThue() {
		return phieuThue;
	}

	public void setPhieuThue(PhieuThue phieuThue) {
		this.phieuThue = phieuThue;
	}

	public DichVu getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVu dichVu) {
		this.dichVu = dichVu;
	}

	public PhieuDichVu() {
		super();
		ngayLap = new Date();
		daThanhToan = false;
	}

	@Override
	public String toString() {
		return "PhieuDichVu [maPDV=" + maPDV + ", soLuong=" + soLuong + ", ngayLap=" + ngayLap + ", daThanhToan="
				+ daThanhToan + ", phieuThue=" + phieuThue.getTrangThai() + ", dichVu=" + dichVu.getTenDv() + "]";
	}
	
	

}
