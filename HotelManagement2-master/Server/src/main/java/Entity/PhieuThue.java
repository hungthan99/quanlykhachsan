package Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "phieuthues")
public class PhieuThue {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int maPT;
	private Date ngayDat;
	private Date ngayKetThuc;
	private Date ngayLap;
	private Date ngayNhan;
	private Date ngayTra;
	private String trangThai;

	@ManyToOne
	@JoinColumn(name = "maP")
	private Phong phong;
	
	@ManyToOne
	@JoinColumn(name = "maNV")
	private NhanVien nhanVien;
	
	@ManyToOne
	@JoinColumn(name = "maKH")
	private KhachHang khachHang;

	public Date getNgayNhan() {
		return ngayNhan;
	}

	public void setNgayNhan(Date ngayNhan) {
		this.ngayNhan = ngayNhan;
	}

	public int getMaPT() {
		return maPT;
	}

	public void setMaPT(int maPT) {
		this.maPT = maPT;
	}

	public Phong getPhong() {
		return phong;
	}

	public void setPhong(Phong phong) {
		this.phong = phong;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public PhieuThue(int maPT) {
		super();
		this.maPT = maPT;
	}

	public PhieuThue() {
		super();
	}

	public PhieuThue(Date ngayDat, Date ngayKetThuc, Phong phong, NhanVien nhanVien, KhachHang khachHang) {
		super();
		this.ngayDat = ngayDat;
		this.ngayKetThuc = ngayKetThuc;
		this.phong = phong;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ngayLap = new Date();
		this.trangThai = "NEW";
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

	@Override
	public String toString() {
		return "PhieuThue [ngayDat=" + ngayDat + ", ngayKetThuc=" + ngayKetThuc + ", ngayLap=" + ngayLap
				+ ", trangThai=" + trangThai + ", phong=" + phong.getMaP() + ", nhanVien=" + nhanVien.getMaNV() + ", khachHang="
				+ khachHang.getMaKH() + "]";
	}
	
	
	

}
