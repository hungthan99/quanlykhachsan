package ClientService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Rmi.Interface.ITinhTrangPhongService;

public class TinhTrangPhongService {
	private ITinhTrangPhongService tinhTrangPhongService;
	private static TinhTrangPhongService instance = null;

	public TinhTrangPhongService() throws MalformedURLException, RemoteException, NotBoundException {
		SecurityManager manager = System.getSecurityManager();
		if (manager == null) {
			System.setProperty("java.security.policy", "rmi/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}

		tinhTrangPhongService = (ITinhTrangPhongService) Naming
				.lookup(RmiConstant.ServerPath + ":" + RmiConstant.ServerPort + "/tinhTrangPhongService");
	}

	public synchronized static TinhTrangPhongService getInstance() {
		if (instance == null) {
			try {
				instance = new TinhTrangPhongService();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public ITinhTrangPhongService getService() {
		return tinhTrangPhongService;
	}

}
