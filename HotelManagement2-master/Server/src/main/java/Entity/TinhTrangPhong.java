package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "tinhtrangphongs")
public class TinhTrangPhong {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int maTTP;
	private String tenTTP;

	public int getMaTTP() {
		return maTTP;
	}

	public void setMaTTP(int maTTP) {
		this.maTTP = maTTP;
	}

	public String getTenTTP() {
		return tenTTP;
	}

	public void setTenTTP(String tenTTP) {
		this.tenTTP = tenTTP;
	}

	public TinhTrangPhong(String tenTTP) {
		super();
		this.tenTTP = tenTTP;
	}

	public TinhTrangPhong(int maTTP) {
		super();
		this.maTTP = maTTP;
	}

	public TinhTrangPhong() {
		super();
	}

	@Override
	public String toString() {
		return "TinhTrangPhong [maTTP=" + maTTP + ", tenTTP=" + tenTTP + "]";
	}
	

}
