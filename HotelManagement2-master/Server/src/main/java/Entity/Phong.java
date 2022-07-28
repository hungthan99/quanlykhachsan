package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "phongs")
public class Phong {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int maP;
	private String ten;

	@ManyToOne
	@JoinColumn(name = "maLP")
	private LoaiPhong maLP;

	@ManyToOne
	@JoinColumn(name = "maTTP")
	private TinhTrangPhong maTTP;

	public Phong() {
		super();
	}

	public Phong(String ten) {
		super();
		this.ten = ten;
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

	public LoaiPhong getMaLP() {
		return maLP;
	}

	public void setMaLP(LoaiPhong maLP) {
		this.maLP = maLP;
	}

	public TinhTrangPhong getMaTTP() {
		return maTTP;
	}

	public void setMaTTP(TinhTrangPhong maTTP) {
		this.maTTP = maTTP;
	}

	@Override
	public String toString() {
		return "Phong [maP=" + maP + ", ten=" + ten + ", maLP=" + maLP + ", maTTP=" + maTTP + "]";
	}

}
