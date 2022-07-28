package Entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "nhanviens")
public class NhanVien {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int maNV;
	private String ten;
	private String email;
	private String gioiTinh;
	private String sdt;

	@Embedded
	private TaiKhoan taiKhoan;

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

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public NhanVien() {
		super();
	}

	public NhanVien(String ten, String email, String gioiTinh, String sdt) {
		super();
		this.ten = ten;
		this.email = email;
		this.gioiTinh = gioiTinh;
		this.sdt = sdt;
	}

	public NhanVien(int maNV) {
		super();
		this.maNV = maNV;
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", ten=" + ten + ", email=" + email + ", gioiTinh=" + gioiTinh + ", sdt="
				+ sdt + "]";
	}

}
