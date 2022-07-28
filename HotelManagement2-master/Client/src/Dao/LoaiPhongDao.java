package Dao;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import Rmi.DTO.LoaiPhongDTO;
import Rmi.Interface.ILoaiPhongService;

public class LoaiPhongDao {

	private ILoaiPhongService loaiPhongService;
	private static LoaiPhongDao instance = null;

	private LoaiPhongDao() throws MalformedURLException, RemoteException, NotBoundException {
		SecurityManager manager = System.getSecurityManager();
		if (manager == null) {
			System.setProperty("java.security.policy", "rmi/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}

		loaiPhongService = (ILoaiPhongService) Naming
				.lookup(RmiConstant.ServerPath + ":" + RmiConstant.ServerPort + "/loaiPhongService");
	}

	public List<LoaiPhongDTO> getAllLoaiPhong() {
		List<LoaiPhongDTO> rvLst = null;
		try {
			rvLst = loaiPhongService.getAllLoaiPhong();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return rvLst;
	}

	public synchronized static LoaiPhongDao getInstance() {
		if (instance == null) {
			try {
				instance = new LoaiPhongDao();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

}
