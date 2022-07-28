package ClientService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Model.PageList;
import Rmi.DTO.PhieuThueDTO;
import Rmi.DTO.PhieuThuePhongInfoDTO;
import Rmi.Interface.IPhieuThueService;

public class PhieuThueService {
	private IPhieuThueService phieuThueService;
	private static PhieuThueService instance = null;

	private PhieuThueService() throws MalformedURLException, RemoteException, NotBoundException {
		SecurityManager manager = System.getSecurityManager();
		if (manager == null) {
			System.setProperty("java.security.policy", "rmi/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		phieuThueService = (IPhieuThueService) Naming
				.lookup(RmiConstant.ServerPath + ":" + RmiConstant.ServerPort + "/phieuThueService");
	}

	public synchronized static PhieuThueService getInstance() {
		if (instance == null) {
			try {
				instance = new PhieuThueService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public boolean addPhieuThue(PhieuThueDTO addObj) {
		try {
			return phieuThueService.addObject(addObj);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public PhieuThueDTO getPhieuThueByCMT(String cmt) {
		try {
			return phieuThueService.getPhieuThueByCMT(cmt);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updatePhieuThue(PhieuThueDTO updateObj) {
		try {
			return phieuThueService.updateObject(updateObj);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean traPhong(int maPhong) {
		try {
			return phieuThueService.traPhong(maPhong);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public PageList<PhieuThuePhongInfoDTO> getListPhieuThueByPage(int pageNumb, int maxRow) {
		try {
			return phieuThueService.getListPhieuThueByPage(pageNumb, maxRow);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
}
