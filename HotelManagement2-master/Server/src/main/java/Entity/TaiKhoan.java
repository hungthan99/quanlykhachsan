package Entity;

import javax.persistence.Embeddable;

@Embeddable
public class TaiKhoan {
	private String tenTK;
	private String matKhau;
	private boolean isAdmin;

	public String getTenTK() {
		return tenTK;
	}

	public void setTenTK(String tenTK) {
		this.tenTK = tenTK;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public TaiKhoan(String tenTK, String matKhau, boolean isAdmin) {
		super();
		this.tenTK = tenTK;
		this.matKhau = matKhau;
		this.isAdmin = isAdmin;
	}

	public TaiKhoan() {
		super();
	}

	@Override
	public String toString() {
		return "TaiKhoan [tenTK=" + tenTK + ", matKhau=" + matKhau + ", isAdmin=" + isAdmin + "]";
	}

}
