package ClientService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Model.PageList;
import Rmi.DTO.PhongDTO;
import Rmi.Interface.IPhongService;

public class PhongService {
	private IPhongService phongService;
	private static PhongService instance = null;

	private PhongService() throws MalformedURLException, RemoteException, NotBoundException {
		SecurityManager manager = System.getSecurityManager();
		if (manager == null) {
			System.setProperty("java.security.policy", "rmi/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}

		phongService = (IPhongService) Naming
				.lookup(RmiConstant.ServerPath + ":" + RmiConstant.ServerPort + "/phongService");
	}

	public PhongDTO getPhongTrong(int maLP) {
		try {
			PhongDTO rvPhong = phongService.getPhongTrongByLoaiPhongId(maLP);
			return rvPhong;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;

	}

	public PageList<PhongDTO> getListPhongPaged(int pageNumb, int maxRow, String name) throws RemoteException {

		return phongService.getListPhongByPage(pageNumb, maxRow, name);

	}

	public boolean DeletePhong(int phongId) {
		try {
			return phongService.deleteObjectById(phongId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean UpdatePhong(PhongDTO updateObj) {
		try {
			return phongService.updateObject(updateObj);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public synchronized static PhongService getInstance() {
		if (instance == null) {
			try {
				instance = new PhongService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public PhongDTO getPhongById(int phongId) {
		try {
			return phongService.getObjectById(phongId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public PageList<PhongDTO> getListPhongDaThue(int pageNumb, int maxRow, String roomName) {
		try {
			return phongService.getListPhongDaThue(pageNumb, maxRow, roomName);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public IPhongService getService() {
		return phongService;
	}
}
