package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "khachhangs")
public class KhachHang {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int maKH;
	private String ten;
	private String email;
	private String sdt;
	private String diaChi;
	private String soCMND;

	public int getMaKH() {
		return maKH;
	}

	public void setMaKH(int maKH) {
		this.maKH = maKH;
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

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoCMND() {
		return soCMND;
	}

	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}

	public KhachHang() {
		super();
	}

	public KhachHang(String ten, String email, String sdt, String diaChi, String soCMND) {
		super();
		this.ten = ten;
		this.email = email;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.soCMND = soCMND;
	}

	public KhachHang(int maKH) {
		super();
		this.maKH = maKH;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", ten=" + ten + ", email=" + email + ", sdt=" + sdt + ", diaChi=" + diaChi
				+ ", soCMND=" + soCMND + "]";
	}

	
	
	
}
