package Dao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Model.PageList;
import Rmi.DTO.NhanVienDTO;
import Rmi.Interface.INhanVienService;

public class NhanVienService {
	private INhanVienService nhanVienService;
	private static NhanVienService instance = null;

	private NhanVienService() throws MalformedURLException, RemoteException, NotBoundException {
		SecurityManager manager = System.getSecurityManager();
		if (manager == null) {
			System.setProperty("java.security.policy", "rmi/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		nhanVienService = (INhanVienService) Naming
				.lookup(RmiConstant.ServerPath + ":" + RmiConstant.ServerPort + "/nhanVienService");
	}
	
	public PageList<NhanVienDTO> getListNhanVienByPage(int pageNumb, int maxRow, String employeeName) throws RemoteException{
		return nhanVienService.getListNhanVienByPage(pageNumb, maxRow, employeeName);
	}

	public synchronized static NhanVienService getInstance() {
		if (instance == null) {
			try {
				instance = new NhanVienService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

}
