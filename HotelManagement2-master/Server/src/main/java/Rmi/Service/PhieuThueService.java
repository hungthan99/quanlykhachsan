package Rmi.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Dao.Impliment.PhieuThueDao;
import Dao.Interface.IPhieuThue;
import Entity.PhieuThue;
import Model.PageList;
import Rmi.DTO.PhieuThueDTO;
import Rmi.DTO.PhieuThuePhongInfoDTO;
import Rmi.Interface.IPhieuThueService;
import Utilities.MappingDtoFacade;

public class PhieuThueService extends UnicastRemoteObject implements IPhieuThueService {
	private static final long serialVersionUID = 1L;
	private IPhieuThue phieuThueDao;

	public PhieuThueService() throws RemoteException {
		phieuThueDao = new PhieuThueDao();
	}

	@Override
	public boolean addObject(PhieuThueDTO objectDTO) throws RemoteException {
		PhieuThue addObj = MappingDtoFacade.mapToPhieuThueAdd(objectDTO);
		return phieuThueDao.add(addObj);
	}

	@Override
	public PhieuThueDTO getObjectById(int id) throws RemoteException {

		try {
			PhieuThueDTO phieuThueDto = MappingDtoFacade.mapToPhieuThueDTO(phieuThueDao.get(id));
			return phieuThueDto;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateObject(PhieuThueDTO objectDTO) throws RemoteException {
		PhieuThue updateObj = MappingDtoFacade.mapToPhieuThueAdd(objectDTO);
		updateObj.setMaPT(objectDTO.getMaPT());
		updateObj.setTrangThai(objectDTO.getTrangThai());
		return phieuThueDao.update(updateObj);
	}

	@Override
	public boolean deleteObjectById(int objectId) throws RemoteException {
		return phieuThueDao.delete(objectId);
	}

	@Override
	public PhieuThueDTO getPhieuThueByCMT(String cmt) throws RemoteException {
		try {
			PhieuThueDTO dto = MappingDtoFacade.mapToPhieuThueDTO(phieuThueDao.getPhieuThueByCMT(cmt));
			return dto;
		} catch (Exception e) {
			if (e instanceof NullPointerException) {
				System.out.println("Không có phiếu thuê phòng với số cmt : " + cmt);
			}
		}
		return null;
	}

	@Override
	public boolean traPhong(int maPhong) throws RemoteException {
		return phieuThueDao.traPhong(maPhong);
	}

	@Override
	public PageList<PhieuThuePhongInfoDTO> getListPhieuThueByPage(int pageNumb, int maxRow) throws RemoteException {
		return phieuThueDao.getListPhieuThueByPage(pageNumb, maxRow);
	}

}
