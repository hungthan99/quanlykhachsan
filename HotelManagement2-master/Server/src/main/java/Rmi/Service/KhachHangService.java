package Rmi.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Dao.Impliment.KhachHangDao;
import Dao.Interface.IKhachHangDao;
import Entity.KhachHang;
import Model.PageList;
import Rmi.DTO.KhachHangDTO;
import Rmi.Interface.IKhachHangService;
import Utilities.MappingDtoFacade;

public class KhachHangService extends UnicastRemoteObject implements IKhachHangService {

	private static final long serialVersionUID = 1L;
	private IKhachHangDao khachHangDao;

	public KhachHangService() throws RemoteException {
		super();
		khachHangDao = new KhachHangDao();
	}

	@Override
	public boolean addObject(KhachHangDTO objectDTO) throws RemoteException {
		KhachHang addObject = MappingDtoFacade.mapToKhachHang(objectDTO);
		return khachHangDao.add(addObject);
	}

	@Override
	public KhachHangDTO getObjectById(int id) throws RemoteException {

		try {
			KhachHangDTO rv = MappingDtoFacade.mapToKhachHangDTO(khachHangDao.get(id));
			return rv;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateObject(KhachHangDTO objectDTO) throws RemoteException {
		KhachHang updateObj = MappingDtoFacade.mapToKhachHang(objectDTO);
		updateObj.setMaKH(objectDTO.getMaKH());
		return khachHangDao.update(updateObj);
	}

	@Override
	public boolean deleteObjectById(int objectId) throws RemoteException {
		// TODO Auto-generated method stub
		return khachHangDao.delete(objectId);
	}

	@Override
	public PageList<KhachHangDTO> getListKhachHangByPage(int pageNumb, int maxRow, String customerName)
			throws RemoteException {
		try {
			PageList<KhachHangDTO> lstDTO = khachHangDao.getListKhachHangByPage(pageNumb, maxRow, customerName);
			return lstDTO;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isDeleteAble(int maKH) throws RemoteException {
		return khachHangDao.isDeleteAble(maKH);
	}

}
