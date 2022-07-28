package Rmi.DTO;

import java.io.Serializable;

public class PhongDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int maP;
	private String ten;

	private int loaiPhong_id;

	private int tinhTrangPhong_id;

	public PhongDTO() {
		super();
	}

	public PhongDTO(int maP) {
		super();
		this.maP = maP;
	}

	public PhongDTO(String ten, int maLP, int maTTP) {
		super();
		this.ten = ten;
		this.loaiPhong_id = maLP;
		this.tinhTrangPhong_id = maTTP;
	}

	public int getMaP() {
		return maP;
	}

	public void setMaP(int maP) {
		this.maP = maP;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public int getLoaiPhong_id() {
		return loaiPhong_id;
	}

	public void setLoaiPhong_id(int loaiPhong_id) {
		this.loaiPhong_id = loaiPhong_id;
	}

	public int getTinhTrangPhong_id() {
		return tinhTrangPhong_id;
	}

	public void setTinhTrangPhong_id(int tinhTrangPhong_id) {
		this.tinhTrangPhong_id = tinhTrangPhong_id;
	}

	@Override
	public String toString() {
		return "PhongDTO [maP=" + maP + ", ten=" + ten + ", loaiPhong_id=" + loaiPhong_id + ", tinhTrangPhong_id="
				+ tinhTrangPhong_id + "]";
	}

}
