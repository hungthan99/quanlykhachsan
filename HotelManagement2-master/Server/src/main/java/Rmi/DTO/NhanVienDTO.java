package Rmi.DTO;

import java.io.Serializable;

public class NhanVienDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int maNV;
	private String ten;
	private String email;
	private String gioiTinh;
	private String sdt;

	private TaiKhoanDTO taiKhoan;

	public NhanVienDTO() {
		super();
	}

	public NhanVienDTO(int maNV) {
		super();
		this.maNV = maNV;
	}

	public NhanVienDTO(String ten, String email, String gioiTinh, String sdt) {
		super();
		this.ten = ten;
		this.email = email;
		this.gioiTinh = gioiTinh;
		this.sdt = sdt;
	}

	public int getMaNV() {
		return maNV;
	}

	public void setMaNV(int maNV) {
		this.maNV = maNV;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public TaiKhoanDTO getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoanDTO taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

}
