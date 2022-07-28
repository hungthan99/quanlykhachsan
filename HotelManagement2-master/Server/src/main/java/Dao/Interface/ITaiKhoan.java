package Dao.Interface;

import Entity.TaiKhoan;

public interface ITaiKhoan {
	TaiKhoan get(String tenTk);

	boolean update(int maNV,TaiKhoan updateTk);

	boolean delete(String tenTK);
	
	boolean addTkToNhanVien(int maNv, TaiKhoan tk);
}
