package Rmi.Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

import Rmi.DTO.TaiKhoanDTO;

public interface ITaiKhoanService extends Remote {
	TaiKhoanDTO get(String tenTk) throws RemoteException;

	boolean update(int maNV, TaiKhoanDTO updateTk) throws RemoteException;

	boolean delete(String tenTK) throws RemoteException;

	boolean addTkToNhanVien(int maNv, TaiKhoanDTO tk) throws RemoteException;
}
