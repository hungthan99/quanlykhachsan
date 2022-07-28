package ClientService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Model.PageList;
import Rmi.DTO.KhachHangDTO;
import Rmi.Interface.IKhachHangService;

public class KhachHangService {
	private IKhachHangService khachHangService;
	private static KhachHangService instance = null;

	private KhachHangService() throws MalformedURLException, RemoteException, NotBoundException {
		SecurityManager manager = System.getSecurityManager();
		if (manager == null) {
			System.setProperty("java.security.policy", "rmi/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		khachHangService = (IKhachHangService) Naming
				.lookup(RmiConstant.ServerPath + ":" + RmiConstant.ServerPort + "/khachHangService");
	}

	public PageList<KhachHangDTO> getListKhachHangByPage(int pageNumb, int maxRow, String customerName)
			throws RemoteException {
		return khachHangService.getListKhachHangByPage(pageNumb, maxRow, customerName);
	}

	public synchronized static KhachHangService getInstance() {
		if (instance == null) {
			try {
				instance = new KhachHangService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public boolean addKhachHang(KhachHangDTO addObj) {
		try {
			return khachHangService.addObject(addObj);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateKhachHang(KhachHangDTO updateObj) {
		try {
			return khachHangService.updateObject(updateObj);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isDeleteAble(int maKH) {
		try {
			return khachHangService.isDeleteAble(maKH);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteKhachHangById(int maKH) {
		try {
			return khachHangService.deleteObjectById(maKH);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public KhachHangDTO getKhachHangById(int id) {
		try {
			return khachHangService.getObjectById(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

}
