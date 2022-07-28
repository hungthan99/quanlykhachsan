package Entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "loaiphongs")
public class LoaiPhong implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int maLP;
	private String tenLP;
	private Double donGia;

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

	public LoaiPhong(int maLP) {
		super();
		this.maLP = maLP;
	}

	public LoaiPhong(String tenLP, Double donGia) {
		super();
		this.tenLP = tenLP;
		this.donGia = donGia;
	}

	public LoaiPhong() {
		super();
	}

}
