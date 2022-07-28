package Rmi.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import Dao.Impliment.TinhTrangPhongDao;
import Dao.Interface.ITinhTrangPhongDao;
import Entity.TinhTrangPhong;
import Rmi.DTO.TinhTrangPhongDTO;
import Rmi.Interface.ITinhTrangPhongService;
import Utilities.MappingDtoFacade;

public class TinhTrangPhongService extends UnicastRemoteObject implements ITinhTrangPhongService {
	private static final long serialVersionUID = 1L;
	private ITinhTrangPhongDao ttpDao;

	public TinhTrangPhongService() throws RemoteException {
		ttpDao = new TinhTrangPhongDao();
	}

	@Override
	public boolean addObject(TinhTrangPhongDTO objectDTO) throws RemoteException {
		TinhTrangPhong addObj = MappingDtoFacade.mapToTinhTrangPhong(objectDTO);
		return ttpDao.add(addObj);
	}

	@Override
	public TinhTrangPhongDTO getObjectById(int id) throws RemoteException {
		try {
			TinhTrangPhongDTO ttpDto = MappingDtoFacade.mapToTinhTrangPhongDto(ttpDao.get(id));
			return ttpDto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateObject(TinhTrangPhongDTO objectDTO) throws RemoteException {
		TinhTrangPhong updateObj = MappingDtoFacade.mapToTinhTrangPhong(objectDTO);
		updateObj.setMaTTP(objectDTO.getMaTTP());
		return ttpDao.update(updateObj);
	}

	@Override
	public boolean deleteObjectById(int objectId) throws RemoteException {
		return ttpDao.delete(objectId);
	}

	@Override
	public List<TinhTrangPhongDTO> getAll() throws RemoteException {
		List<TinhTrangPhong> lstTTP = ttpDao.getAll();
		List<TinhTrangPhongDTO> lstDTO = MappingDtoFacade.mapToListTtpDTO(lstTTP);
		return lstDTO;
	}

}
