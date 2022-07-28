package ClientService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Model.PageList;
import Rmi.DTO.DichVuDTO;
import Rmi.DTO.KhachHangDTO;
import Rmi.Interface.IDichVuService;
import Rmi.Interface.IKhachHangService;

public class DichVuService {
	private IDichVuService dichVuService;
	private static DichVuService instance = null;

	private DichVuService() throws MalformedURLException, RemoteException, NotBoundException {
		SecurityManager manager = System.getSecurityManager();
		if (manager == null) {
			System.setProperty("java.security.policy", "rmi/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}
		dichVuService = (IDichVuService) Naming
				.lookup(RmiConstant.ServerPath + ":" + RmiConstant.ServerPort + "/dichVuService");
	}

	public synchronized static DichVuService getInstance() {
		if (instance == null) {
			try {
				instance = new DichVuService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public PageList<DichVuDTO> getListDichVuByPage(int pageNumb, int maxRow, String dichVuName) {
		PageList<DichVuDTO> lst = new PageList<>();
		try {
			lst = dichVuService.getListDichVuByPage(pageNumb, maxRow, dichVuName);
			return lst;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public boolean AddDichVu(DichVuDTO addObj) {
		// TODO Auto-generated method stub
		try {
			return dichVuService.addObject(addObj);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isDeleteAble(int maDV) {
		try {
			return dichVuService.isDeleteAble(maDV);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteDichVuById(int maDv) {
		try {
			return dichVuService.deleteObjectById(maDv);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean UpdateDichVu(DichVuDTO updateObj) {
		try {
			return dichVuService.updateObject(updateObj);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;

	}
}
