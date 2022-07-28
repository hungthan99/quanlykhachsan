package Utilities;

import java.util.ArrayList;
import java.util.List;

import Entity.NhanVien;
import Rmi.DTO.NhanVienDTO;

public class NhanVienUtil {

	public static NhanVien convertObjectToNhanVien(Object[] object) {
		int maNV = (int) object[0];
		NhanVien temp = new NhanVien((String) object[4], (String) object[2], (String) object[1], (String) object[3]);
		temp.setMaNV(maNV);
		return temp;
	}

	public static List<NhanVien> convertToListNhanVien(List<?> listObject) {
		List<NhanVien> returnList = new ArrayList<NhanVien>();

		for (Object objects : listObject) {
			Object[] o = (Object[]) objects;
			returnList.add(convertObjectToNhanVien(o));
		}

		return returnList;
	}

	public static List<NhanVienDTO> convertListDTO(List<NhanVien> lstNV) {
		List<NhanVienDTO> lstDTO = new ArrayList<NhanVienDTO>();
		for (NhanVien nhanVien : lstNV) {
			NhanVienDTO nvDTO = MappingDtoFacade.mapToNhanVienDTO(nhanVien);
			lstDTO.add(nvDTO);
		}
		return lstDTO;
	}
}
