package Rmi.DTO;

import java.io.Serializable;

public class TaiKhoanDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	public TaiKhoanDTO(String tenTK, String matKhau, boolean isAdmin) {
		super();
		this.tenTK = tenTK;
		this.matKhau = matKhau;
		this.isAdmin = isAdmin;
	}

	public TaiKhoanDTO() {
		super();
	}

}
