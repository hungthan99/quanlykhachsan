import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Rmi.DTO.ChucVuDTO;
import Rmi.DTO.PhongDTO;
import Rmi.Interface.IPhongService;

public class SampleRun {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		// TODO Auto-generated method stub
		SecurityManager securityManager = System.getSecurityManager();
		if (securityManager == null) {
			System.setProperty("java.security.policy", "rmi/policy.policy");
			System.setSecurityManager(new SecurityManager());
		}

		// su dung service nao
		// IChucVuService chucVuService = (IChucVuService)
		// Naming.lookup("rmi://localhost:9091/chucVuService");
		IPhongService phongService = (IPhongService) Naming.lookup("rmi://localhost:3000/phongService");

//		if (chucVuService.addChucVu(Dto)) System.out.println("Add From Client success");
//		else System.out.println("Add from client fail");
//		
//		ChucVuDTO temp = null;
//		try {
//			temp = chucVuService.getChucVuById(120);
//			System.out.println(temp.getTenCV());
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("Fail because id maybe not found in database");
//		}
//		
//		
//		temp.setTenCV("Rmi update CV name");
//		if (chucVuService.updateChucVu(temp)) System.out.println("Update success");
//		else System.out.println("Update from client fail");
//		
//		if (chucVuService.deleteChucVu(temp.getMaCV())) System.out.println("delete success");
//		else System.out.println("delete from client fail");
	}

}
