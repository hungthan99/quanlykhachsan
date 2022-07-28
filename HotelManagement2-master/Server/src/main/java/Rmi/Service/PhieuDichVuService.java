package Rmi.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import Dao.Impliment.PhieuDichVuDao;
import Dao.Interface.IPhieuDichVu;
import Entity.PhieuDichVu;
import Rmi.DTO.PhieuDichVuDTO;
import Rmi.DTO.PhieuDichVuInfoDTO;
import Rmi.Interface.IPhieuDichVuService;
import Utilities.MappingDtoFacade;

public class PhieuDichVuService extends UnicastRemoteObject implements IPhieuDichVuService {
	private static final long serialVersionUID = 1L;
	private IPhieuDichVu phieuDichVuDao;

	public PhieuDichVuService() throws RemoteException {
		phieuDichVuDao = new PhieuDichVuDao();
	}

	@Override
	public boolean addObject(PhieuDichVuDTO objectDTO) throws RemoteException {
		PhieuDichVu pdv = MappingDtoFacade.mapToPhieuDichVu(objectDTO);
		return false;
	}

	@Override
	public PhieuDichVuDTO getObjectById(int id) throws RemoteException {
		return null;
	}

	@Override
	public boolean updateObject(PhieuDichVuDTO objectDTO) throws RemoteException {
		return false;
	}

	@Override
	public boolean deleteObjectById(int objectId) throws RemoteException {
		return false;
	}

	@Override
	public boolean addPhieuDichVuByMaPhong(int maPhong, int maDichVu, int soluong) throws RemoteException {

		return phieuDichVuDao.addPhieuDichVuByMaPhong(maPhong, maDichVu, soluong);
	}

	@Override
	public List<PhieuDichVuInfoDTO> getListPhieuDichVuByMaPT(int maPT) throws RemoteException {
		return phieuDichVuDao.getListPhieuDichVuByMaPT(maPT);
	}

	@Override
	public boolean updateThanhToanDvByMaPT(int maPT) throws RemoteException {
		return phieuDichVuDao.updateThanhToanDvByMaPT(maPT);
	}

}
