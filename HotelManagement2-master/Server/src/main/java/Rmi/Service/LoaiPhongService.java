package Rmi.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import Dao.Impliment.LoaiPhongDao;
import Dao.Interface.ILoaiPhongDao;
import Entity.LoaiPhong;
import Model.PageList;
import Rmi.DTO.LoaiPhongDTO;
import Rmi.Interface.ILoaiPhongService;
import Utilities.MappingDtoFacade;

public class LoaiPhongService extends UnicastRemoteObject implements ILoaiPhongService {
	private static final long serialVersionUID = 1L;
	private ILoaiPhongDao loaiPhongDao;

	public LoaiPhongService() throws RemoteException {
		loaiPhongDao = new LoaiPhongDao();
	}

	@Override
	public boolean addObject(LoaiPhongDTO objectDTO) throws RemoteException {
		LoaiPhong addObj = MappingDtoFacade.mapToLoaiPhong(objectDTO);
		return loaiPhongDao.add(addObj);
	}

	@Override
	public LoaiPhongDTO getObjectById(int id) throws RemoteException {
		try {
			LoaiPhongDTO rv = MappingDtoFacade.mapToLoaiPhongDTO(loaiPhongDao.get(id));
			return rv;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateObject(LoaiPhongDTO objectDTO) throws RemoteException {
		LoaiPhong updateObj = MappingDtoFacade.mapToLoaiPhong(objectDTO);
		updateObj.setMaLP(objectDTO.getMaLP());
		return loaiPhongDao.update(updateObj);
	}

	@Override
	public boolean deleteObjectById(int objectId) throws RemoteException {
		return loaiPhongDao.delete(objectId);
	}

	@Override
	public List<LoaiPhongDTO> getAllLoaiPhong() throws RemoteException {
		List<LoaiPhong> lstLoaiPhong = loaiPhongDao.getAll();
		return MappingDtoFacade.mapToListLoaiPhongDTO(lstLoaiPhong);
	}

	@Override
	public PageList<LoaiPhongDTO> getListLoaiPhongByPage(int pageNumb, int maxRow, String key) throws RemoteException {
		return loaiPhongDao.getListLoaiPhongByPage(pageNumb, maxRow, key);
	}

	@Override
	public boolean isDeleteAble(int maLP) throws RemoteException {
		return loaiPhongDao.isDeleteAble(maLP);
	}

}
