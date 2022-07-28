package ClientService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

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

	public PageList<NhanVienDTO> getListNhanVienByPage(int pageNumb, int maxRow, String employeeName)
			throws RemoteException {
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

	public boolean addNhanVien(NhanVienDTO addObj) {
		try {
			return nhanVienService.addObject(addObj);
		} catch (Exception e) {
			if (e instanceof javax.persistence.PersistenceException) {
				JOptionPane.showMessageDialog(null, "Tên tài khoản đã tồn tại, vui lòng chọn tài khoản khác!");
			} else {
				e.printStackTrace();
			}

		}
		return false;
	}

	public boolean isDeleteAble(int maNV) {
		try {
			return nhanVienService.isDeleteAble(maNV);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteNhanVienById(int maNV) {
		try {
			return nhanVienService.deleteObjectById(maNV);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateNhanVien(NhanVienDTO updateObj) {
		try {
			return nhanVienService.updateObject(updateObj);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public NhanVienDTO getNhanVienByTenTK(String tenTK) {
		try {
			return nhanVienService.getNhanVienByTenTK(tenTK);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
