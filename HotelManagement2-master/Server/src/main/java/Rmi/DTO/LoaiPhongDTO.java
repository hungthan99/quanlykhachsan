package Rmi.DTO;

import java.io.Serializable;
import java.util.Objects;

public class LoaiPhongDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int maLP;
	private String tenLP;
	private Double donGia;

	public LoaiPhongDTO() {
		super();
	}

	public LoaiPhongDTO(int maLP) {
		super();
		this.maLP = maLP;
	}

	public LoaiPhongDTO(String tenLP, Double donGia) {
		super();
		this.tenLP = tenLP;
		this.donGia = donGia;
	}

	public int getMaLP() {
		return maLP;
	}

	public void setMaLP(int maLP) {
		this.maLP = maLP;
	}

	public String getTenLP() {
		return tenLP;
	}

	public void setTenLP(String tenLP) {
		this.tenLP = tenLP;
	}

	public Double getDonGia() {
		return donGia;
	}

	public void setDonGia(Double donGia) {
		this.donGia = donGia;
	}

	@Override
	public String toString() {
		return "LoaiPhongDTO [maLP=" + maLP + ", tenLP=" + tenLP + ", donGia=" + donGia + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLP);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoaiPhongDTO other = (LoaiPhongDTO) obj;
		return maLP == other.maLP;
	}

}
