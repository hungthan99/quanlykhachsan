package RmiInterface;

import java.rmi.Remote;

import Entity.ChucVu;
import RmiDto.ChucVuDTO;

public interface IChucVuService extends Remote {
	boolean addChucVu(ChucVuDTO object);
	ChucVu getChucVuById(int id);
	boolean updateChucVu(ChucVuDTO object);
	boolean deleteChucVu(ChucVuDTO object);
}
