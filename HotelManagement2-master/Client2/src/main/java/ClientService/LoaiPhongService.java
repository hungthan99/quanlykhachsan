package ClientService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import Model.PageList;
import Rmi.DTO.LoaiPhongDTO;
import Rmi.Interface.ILoaiPhongService;

public class LoaiPhongService {

	private ILoaiPhongService loaiPhongService;
	private static LoaiPhongService instance = null;

	private LoaiPhongService() throws MalformedURLException, RemoteException, NotBoundException {
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

	public synchronized static LoaiPhongService getInstance() {
		if (instance == null) {
			try {
				instance = new LoaiPhongService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public PageList<LoaiPhongDTO> getListLoaiPhongByPage(int pageNumb, int maxRow, String key) {
		try {
			return loaiPhongService.getListLoaiPhongByPage(pageNumb, maxRow, key);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean addLoaiPhong(LoaiPhongDTO addObj) {
		try {
			return loaiPhongService.addObject(addObj);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateLoaiPhong(LoaiPhongDTO updateObj) {
		try {
			return loaiPhongService.updateObject(updateObj);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isDeleteAble(int maLP) {
		try {
			return loaiPhongService.isDeleteAble(maLP);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteLoaiPhongById(int maLP) {
		try {
			return loaiPhongService.deleteObjectById(maLP);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public LoaiPhongDTO getLoaiPhongById(int id) {
		try {
			return loaiPhongService.getObjectById(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

}
