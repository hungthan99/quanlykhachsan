package Rmi.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Dao.Impliment.NhanVienDao;
import Dao.Interface.INhanVien;
import Entity.NhanVien;
import Model.PageList;
import Rmi.DTO.NhanVienDTO;
import Rmi.Interface.INhanVienService;
import Utilities.MappingDtoFacade;

public class NhanVienService extends UnicastRemoteObject implements INhanVienService {
	private static final long serialVersionUID = 1L;
	private INhanVien nhanVienDao;

	public NhanVienService() throws RemoteException {
		nhanVienDao = new NhanVienDao();
	}

	@Override
	public boolean addObject(NhanVienDTO objectDTO) throws RemoteException {
		NhanVien addObj = MappingDtoFacade.mapToNhanVien(objectDTO);
		return nhanVienDao.add(addObj);
	}

	@Override
	public NhanVienDTO getObjectById(int id) throws RemoteException {
		try {
			NhanVienDTO nv = MappingDtoFacade.mapToNhanVienDTO(nhanVienDao.get(id));
			return nv;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateObject(NhanVienDTO objectDTO) throws RemoteException {
		NhanVien updateObj = MappingDtoFacade.mapToNhanVien(objectDTO);
		updateObj.setMaNV(objectDTO.getMaNV());
		return nhanVienDao.update(updateObj);
	}

	@Override
	public boolean deleteObjectById(int objectId) throws RemoteException {
		return nhanVienDao.delete(objectId);
	}

	@Override
	public PageList<NhanVienDTO> getListNhanVienByPage(int pageNumb, int maxRow, String employeeName)
			throws RemoteException {
		try {
			PageList<NhanVienDTO> lstDTO = nhanVienDao.getListNhanVienByPage(pageNumb, maxRow, employeeName);
			return lstDTO;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isDeleteAble(int maNV) throws RemoteException {
		return nhanVienDao.isDeleteAble(maNV);
	}

	@Override
	public NhanVienDTO getNhanVienByTenTK(String tenTK) throws RemoteException {
		NhanVien nv = nhanVienDao.getNhanVienByTenTK(tenTK);
		NhanVienDTO rv = MappingDtoFacade.mapToNhanVienDTO(nv);
		return rv;
	}

}
